package com.yanbian.controller;

import com.yanbian.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @GetMapping
    public Result<Map<String, Object>> getVersion() {
        Map<String, Object> versionInfo = new HashMap<>();
        versionInfo.put("version", appVersion);
        versionInfo.put("buildTime", System.currentTimeMillis());
        return Result.success(versionInfo);
    }
}
