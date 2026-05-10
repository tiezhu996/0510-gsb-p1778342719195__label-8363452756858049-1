package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanbian.common.Result;
import com.yanbian.entity.Survey;
import com.yanbian.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {
    
    private final SurveyService surveyService;
    
    @PostMapping("/submit")
    public Result<Boolean> submit(@RequestBody Survey survey) {
        return Result.success(surveyService.save(survey));
    }
    
    @GetMapping("/list")
    public Result<Page<Survey>> list(@RequestParam(defaultValue = "1") Long current,
                                      @RequestParam(defaultValue = "10") Long size) {
        return Result.success(surveyService.page(new Page<>(current, size)));
    }
    
    @GetMapping("/{id}")
    public Result<Survey> getById(@PathVariable Long id) {
        return Result.success(surveyService.getById(id));
    }
    
    @GetMapping("/statistics")
    public Result<Map<String, Object>> getStatistics() {
        long total = surveyService.count();
        QueryWrapper<Survey> wrapper = new QueryWrapper<>();
        wrapper.select("AVG(satisfaction) as avgSatisfaction");
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("avgSatisfaction", surveyService.getMap(wrapper).get("avgSatisfaction"));
        return Result.success(stats);
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(surveyService.removeById(id));
    }
}
