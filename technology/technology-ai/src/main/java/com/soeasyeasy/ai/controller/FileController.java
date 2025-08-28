package com.soeasyeasy.ai.controller;

import com.soeasyeasy.ai.model.DocumentVersion;
import com.soeasyeasy.ai.model.FileMetadata;
import com.soeasyeasy.ai.service.FileStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件控制器
 *
 * @author hc
 * @date 2025/08/25
 */
@RestController
@RequestMapping("/api/files")
public class FileController {

    /**
     * 文件存储服务
     */
    private final FileStoreService fileStoreService;

    /**
     * 文件控制器
     *
     * @param fileStoreService 文件存储服务
     */
    public FileController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    /**
     * 上传
     *
     * @param file     文件
     * @param operator 算子
     * @return {@link ResponseEntity }<{@link Map }<{@link String }, {@link Object }>>
     */
    @PostMapping("/upload")
    public Map<String, Object> upload(
            @RequestParam MultipartFile file,
            @RequestHeader(name = "X-Operator", required = false, defaultValue = "anonymous") String operator) {
        return fileStoreService.saveFile(file, operator);
    }

    /**
     * 更新
     *
     * @param fileId   文件 ID
     * @param file     文件
     * @param operator 算子
     * @return {@link ResponseEntity }<{@link Map }<{@link String }, {@link Object }>>
     */
    @PutMapping("/{fileId}")
    public Map<String, Object> update(
            @PathVariable String fileId,
            @RequestParam MultipartFile file,
            @RequestHeader("X-Operator") String operator) {
        return fileStoreService.updateFile(fileId, file, operator);
    }

    /**
     * 删除
     *
     * @param fileId   文件 ID
     * @param operator 算子
     * @return {@link ResponseEntity }<{@link Map }<{@link String }, {@link Object }>>
     */
    @DeleteMapping("/{fileId}")
    public Map<String, Object> delete(
            @PathVariable String fileId,
            @RequestHeader("X-Operator") String operator) {
        return fileStoreService.deleteFile(fileId, operator);
    }

    /**
     * 列表
     *
     * @return {@link ResponseEntity }<{@link List }<{@link FileMetadata }>>
     */
    @PostMapping("/page")
    public List<FileMetadata> list() {
        return fileStoreService.listAllFiles();
    }

    /**
     * 版本
     *
     * @param fileId 文件 ID
     * @return {@link ResponseEntity }<{@link List }<{@link DocumentVersion }>>
     */
    @GetMapping("/{fileId}/versions")
    public List<DocumentVersion> versions(@PathVariable String fileId) {
        return fileStoreService.getVersionHistory(fileId);
    }
}