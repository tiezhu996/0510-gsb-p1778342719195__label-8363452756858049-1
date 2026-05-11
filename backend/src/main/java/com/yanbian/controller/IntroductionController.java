package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanbian.common.Result;
import com.yanbian.entity.Introduction;
import com.yanbian.service.IntroductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/introduction")
@RequiredArgsConstructor
public class IntroductionController {
    
    private final IntroductionService introductionService;
    
    @GetMapping("/list")
    public Result<List<Introduction>> list() {
        QueryWrapper<Introduction> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort_order");
        return Result.success(introductionService.list(wrapper));
    }
    
    @GetMapping("/catalog")
    public Result<List<Map<String, Object>>> getCatalog() {
        QueryWrapper<Introduction> wrapper = new QueryWrapper<>();
        wrapper.select("id", "section_name", "sort_order").orderByAsc("sort_order");
        List<Map<String, Object>> catalog = introductionService.listMaps(wrapper);
        return Result.success(catalog);
    }
    
    @GetMapping("/{id}")
    public Result<Introduction> getById(@PathVariable Long id) {
        return Result.success(introductionService.getById(id));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Introduction introduction) {
        return Result.success(introductionService.save(introduction));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody Introduction introduction) {
        introduction.setId(id);
        return Result.success(introductionService.updateById(introduction));
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(introductionService.removeById(id));
    }
}
