package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanbian.common.Result;
import com.yanbian.entity.Carousel;
import com.yanbian.service.CarouselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carousel")
@RequiredArgsConstructor
public class CarouselController {
    
    private final CarouselService carouselService;
    
    @GetMapping("/list")
    public Result<List<Carousel>> list() {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByAsc("sort_order");
        return Result.success(carouselService.list(wrapper));
    }
    
    @GetMapping("/{id}")
    public Result<Carousel> getById(@PathVariable Long id) {
        return Result.success(carouselService.getById(id));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Carousel carousel) {
        return Result.success(carouselService.save(carousel));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody Carousel carousel) {
        carousel.setId(id);
        return Result.success(carouselService.updateById(carousel));
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(carouselService.removeById(id));
    }
    
    @PutMapping("/sort")
    public Result<Boolean> updateSort(@RequestBody List<Carousel> carousels) {
        return Result.success(carouselService.updateBatchById(carousels));
    }
}
