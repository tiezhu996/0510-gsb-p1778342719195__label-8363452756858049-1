package com.yanbian.controller;

import com.yanbian.common.Result;
import com.yanbian.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    
    private final NewsService newsService;
    private final ScenicSpotService scenicSpotService;
    private final FoodService foodService;
    private final SurveyService surveyService;
    
    @GetMapping("/overview")
    public Result<Map<String, Object>> getOverview() {
        Map<String, Object> overview = new HashMap<>();
        overview.put("newsCount", newsService.count());
        overview.put("scenicCount", scenicSpotService.count());
        overview.put("foodCount", foodService.count());
        overview.put("surveyCount", surveyService.count());
        return Result.success(overview);
    }
    
    @GetMapping("/popular")
    public Result<Map<String, Object>> getPopular() {
        Map<String, Object> popular = new HashMap<>();
        popular.put("message", "热门内容统计");
        return Result.success(popular);
    }
    
    @GetMapping("/trend")
    public Result<Map<String, Object>> getTrend() {
        Map<String, Object> trend = new HashMap<>();
        trend.put("message", "访问趋势统计");
        return Result.success(trend);
    }
}
