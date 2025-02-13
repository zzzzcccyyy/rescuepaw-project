package com.rescuepaw.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageUploader {

    private static final String UPLOAD_DIR = "uploads/";

    // 上传图片并返回图片URL
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        // 创建上传目录
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // 保存文件
        Files.copy(file.getInputStream(), filePath);

        return UPLOAD_DIR + fileName;
    }

    // 删除图片
    public void deleteImage(String imageUrl) throws IOException {
        Path filePath = Paths.get(imageUrl);
        Files.deleteIfExists(filePath);
    }
}