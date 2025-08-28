// FileStoreService.java
package com.soeasyeasy.ai.service;

import com.soeasyeasy.ai.mapper.DocumentVersionMapper;
import com.soeasyeasy.ai.mapper.FileMetadataMapper;
import com.soeasyeasy.ai.model.DocumentVersion;
import com.soeasyeasy.ai.model.FileMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class FileStoreService {

    private final VectorStore vectorStore;
    private final DocumentTransformer documentTransformer;
    private final FileMetadataMapper fileMetadataMapper;
    private final DocumentVersionMapper documentVersionMapper;

    @Value("${filestore.upload-dir:uploads}")
    private String uploadDir;

    public FileStoreService(VectorStore vectorStore,
                            DocumentTransformer documentTransformer,
                            FileMetadataMapper fileMetadataMapper,
                            DocumentVersionMapper documentVersionMapper) {
        this.vectorStore = vectorStore;
        this.documentTransformer = documentTransformer;
        this.fileMetadataMapper = fileMetadataMapper;
        this.documentVersionMapper = documentVersionMapper;
    }

    // 1. ✅ 上传文件
    public Map<String, Object> saveFile(MultipartFile file, String operator) {
        Map<String, Object> result = new HashMap<>();
        String fileId = UUID.randomUUID().toString();

        try {
            Resource resource = file.getResource();
            TikaDocumentReader reader = new TikaDocumentReader(resource);
            List<Document> rawDocuments = reader.get();

            // 添加元数据
            rawDocuments.forEach(doc -> {
                doc.getMetadata().put("fileId", fileId);
                doc.getMetadata().put("filename", file.getOriginalFilename());
                doc.getMetadata().put("uploadTime", Instant.now().toString());
            });

            // 分块
            List<Document> documents = documentTransformer.apply(rawDocuments);

            // 生成 document IDs
            List<String> docIds = documents.stream()
                    .map(Document::getId)
                    .collect(Collectors.toList());

            // 存向量
            vectorStore.accept(documents);

            // 保存元数据
            FileMetadata meta = FileMetadata.builder()
                    .id(fileId)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .uploadTime(Instant.now())
                    .updateTime(Instant.now())
                    .operator(operator)
                    .documentIds(docIds)
                    .build();

            fileMetadataMapper.insert(meta);

            // 记录版本
            saveVersion(meta, operator, "UPLOAD", Collections.emptyList(), docIds, "文件上传");

            result.put("success", true);
            result.put("message", "上传成功");
            result.put("fileId", fileId);
            result.put("documentCount", documents.size());

        } catch (Exception e) {
            log.error("上传失败: " + e.getMessage(), e);
            result.put("success", false);
            result.put("message", "上传失败: " + e.getMessage());
        }

        return result;
    }

    // 2. ✏️ 更新文件
    public Map<String, Object> updateFile(String fileId, MultipartFile newFile, String operator) {
        Map<String, Object> result = new HashMap<>();

        FileMetadata oldMeta = fileMetadataMapper.selectById(fileId);
        if (oldMeta == null) {
            result.put("success", false);
            result.put("message", "文件不存在");
            return result;
        }

        try {
            // 删除旧向量
            if (oldMeta.getDocumentIds() != null && !oldMeta.getDocumentIds().isEmpty()) {
                vectorStore.delete(oldMeta.getDocumentIds());
            }

            // 解析新文件
            Resource resource = newFile.getResource();
            TikaDocumentReader reader = new TikaDocumentReader(resource);
            List<Document> rawDocuments = reader.get();
            rawDocuments.forEach(doc -> {
                doc.getMetadata().put("fileId", fileId);
                doc.getMetadata().put("filename", newFile.getOriginalFilename());
                doc.getMetadata().put("uploadTime", Instant.now().toString());
            });

            List<Document> newDocuments = documentTransformer.apply(rawDocuments);
            List<String> newDocIds = newDocuments.stream()
                    .map(Document::getId)
                    .collect(Collectors.toList());

            vectorStore.accept(newDocuments);

            // 更新元数据
            oldMeta.setFileName(newFile.getOriginalFilename());
            oldMeta.setFileSize(newFile.getSize());
            oldMeta.setUpdateTime(Instant.now());
            oldMeta.setDocumentIds(newDocIds);
            fileMetadataMapper.updateById(oldMeta);

            // 记录版本
            saveVersion(oldMeta, operator, "UPDATE",
                    oldMeta.getDocumentIds(), newDocIds,
                    "文件更新");

            result.put("success", true);
            result.put("message", "更新成功");

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
        }

        return result;
    }

    // 3. 🗑️ 删除文件
    public Map<String, Object> deleteFile(String fileId, String operator) {
        Map<String, Object> result = new HashMap<>();

        FileMetadata meta = fileMetadataMapper.selectById(fileId);
        if (meta == null) {
            result.put("success", false);
            result.put("message", "文件不存在");
            return result;
        }

        try {
            // 删除向量
            if (meta.getDocumentIds() != null && !meta.getDocumentIds().isEmpty()) {
                vectorStore.delete(meta.getDocumentIds());
            }

            // 记录版本
            saveVersion(meta, operator, "DELETE",
                    meta.getDocumentIds(), Collections.emptyList(),
                    "文件已删除");

            // 删除元数据
            fileMetadataMapper.deleteById(fileId);

            result.put("success", true);
            result.put("message", "删除成功");

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
        }

        return result;
    }

    // 4. 📋 查询所有文件
    public List<FileMetadata> listAllFiles() {
        return fileMetadataMapper.selectList(null);
    }

    // 5. 🔍 查询单个文件
    public FileMetadata getFileById(String fileId) {
        return fileMetadataMapper.selectById(fileId);
    }

    // 6. 📜 查询版本历史
    public List<DocumentVersion> getVersionHistory(String fileId) {
        return documentVersionMapper.selectByFileId(fileId);
    }

    // === 私有方法：保存版本记录 ===
    private void saveVersion(FileMetadata meta, String operator, String changeType,
                             List<String> oldIds, List<String> newIds, String desc) {
        DocumentVersion version = DocumentVersion.builder()
                .fileId(meta.getId())
                .fileName(meta.getFileName())
                .operator(operator)
                .operatedAt(Instant.now())
                .changeType(changeType)
                .oldDocumentIds(new ArrayList<>(oldIds))
                .newDocumentIds(new ArrayList<>(newIds))
                .description(desc)
                .build();
        documentVersionMapper.insert(version);
    }
}