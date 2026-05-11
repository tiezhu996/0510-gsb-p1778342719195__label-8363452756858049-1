package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanbian.common.Result;
import com.yanbian.entity.ScenicSpot;
import com.yanbian.service.ScenicSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scenic")
@RequiredArgsConstructor
public class ScenicSpotController {
    
    private final ScenicSpotService scenicSpotService;
    
    @GetMapping("/list")
    public Result<Page<ScenicSpot>> list(@RequestParam(defaultValue = "1") Long current,
                                          @RequestParam(defaultValue = "10") Long size) {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("rating");
        return Result.success(scenicSpotService.page(new Page<>(current, size), wrapper));
    }
    
    @GetMapping("/top")
    public Result<List<ScenicSpot>> getTopScenic() {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("view_count").last("LIMIT 6");
        return Result.success(scenicSpotService.list(wrapper));
    }
    
    @GetMapping("/{id}")
    public Result<ScenicSpot> getById(@PathVariable Long id) {
        return Result.success(scenicSpotService.getById(id));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody ScenicSpot scenicSpot) {
        return Result.success(scenicSpotService.save(scenicSpot));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody ScenicSpot scenicSpot) {
        scenicSpot.setId(id);
        return Result.success(scenicSpotService.updateById(scenicSpot));
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(scenicSpotService.removeById(id));
    }
    
    @PutMapping("/{id}/view")
    public Result<Boolean> increaseViewCount(@PathVariable Long id) {
        ScenicSpot spot = scenicSpotService.getById(id);
        if (spot != null) {
            spot.setViewCount(spot.getViewCount() + 1);
            return Result.success(scenicSpotService.updateById(spot));
        }
        return Result.error("景点不存在");
    }
    
    @GetMapping("/search")
    public Result<List<ScenicSpot>> search(@RequestParam String keyword) {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.like("name", keyword).or().like("description", keyword);
        return Result.success(scenicSpotService.list(wrapper));
    }
    
    @GetMapping("/recommend")
    public Result<List<ScenicSpot>> getRecommend() {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("rating").last("LIMIT 4");
        return Result.success(scenicSpotService.list(wrapper));
    }
}
