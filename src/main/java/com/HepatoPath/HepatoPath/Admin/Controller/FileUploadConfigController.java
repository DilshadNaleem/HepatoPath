package com.HepatoPath.HepatoPath.Admin.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadConfigController
{
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxRequestSize;

    @GetMapping("/upload-limits")
    public String getUploadLimits() {
        return "Max File Size: " + maxFileSize + ", Max Request Size: " + maxRequestSize;
    }
}
