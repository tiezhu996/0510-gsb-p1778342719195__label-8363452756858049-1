package com.yanbian.controller;

import com.yanbian.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    
    @Value("${file.upload-dir:/app/uploads}")
    private String uploadDir;
    
    @PostMapping("/upload")
    public Result<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        
        try {
            // 创建上传目录
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;
            
            // 保存文件
            Path filePath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), filePath);
            
            Map<String, String> result = new HashMap<>();
            result.put("filename", filename);
            result.put("url", "/uploads/" + filename);
            result.put("originalName", originalFilename);
            
            return Result.success(result);
        } catch (IOException e) {
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
    
    @PostMapping("/upload/multiple")
    public Result<List<Map<String, String>>> uploadMultiple(@RequestParam("files") MultipartFile[] files) {
        List<Map<String, String>> results = new ArrayList<>();
        
        for (MultipartFile file : files) {
            Result<Map<String, String>> result = upload(file);
            if (result.getCode() == 200) {
                results.add(result.getData());
            }
        }
        
        return Result.success(results);
    }
    
    @GetMapping("/{id}")
    public Result<Map<String, Object>> getFileInfo(@PathVariable Long id) {
        // 简化实现，实际应查询数据库
        Map<String, Object> info = new HashMap<>();
        info.put("id", id);
        info.put("message", "文件信息查询功能");
        return Result.success(info);
    }
}
