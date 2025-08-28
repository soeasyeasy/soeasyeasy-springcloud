package com.soeasyeasy.ai.controller;

import com.soeasyeasy.ai.service.FileStoreServiceBak;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author hc
 */
@RequestMapping("/file")
@RestController
public class FileStoreController {
    private final FileStoreServiceBak fileStoreService;

    public FileStoreController(FileStoreServiceBak fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping("/upload")
    public Object uploadFile(MultipartFile file) {
        return fileStoreService.saveFile(file);
    }
}