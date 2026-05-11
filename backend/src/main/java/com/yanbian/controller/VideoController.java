package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanbian.common.Result;
import com.yanbian.entity.Video;
import com.yanbian.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {
    
    private final VideoService videoService;
    
    @GetMapping("/list")
    public Result<List<Video>> list() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByAsc("sort_order");
        return Result.success(videoService.list(wrapper));
    }
    
    @GetMapping("/{id}")
    public Result<Video> getById(@PathVariable Long id) {
        return Result.success(videoService.getById(id));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Video video) {
        return Result.success(videoService.save(video));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody Video video) {
        video.setId(id);
        return Result.success(videoService.updateById(video));
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(videoService.removeById(id));
    }
}
