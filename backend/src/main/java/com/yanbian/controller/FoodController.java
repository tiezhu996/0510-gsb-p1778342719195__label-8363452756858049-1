package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanbian.common.Result;
import com.yanbian.entity.Food;
import com.yanbian.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    
    private final FoodService foodService;
    
    @GetMapping("/list")
    public Result<Page<Food>> list(@RequestParam(defaultValue = "1") Long current,
                                    @RequestParam(defaultValue = "10") Long size) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("popularity");
        return Result.success(foodService.page(new Page<>(current, size), wrapper));
    }
    
    @GetMapping("/ranking")
    public Result<List<Food>> getRanking() {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("popularity").last("LIMIT 10");
        return Result.success(foodService.list(wrapper));
    }
    
    @GetMapping("/featured")
    public Result<List<Food>> getFeatured() {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("is_featured", 1).eq("status", 1).orderByDesc("rating").last("LIMIT 4");
        return Result.success(foodService.list(wrapper));
    }
    
    @GetMapping("/{id}")
    public Result<Food> getById(@PathVariable Long id) {
        return Result.success(foodService.getById(id));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Food food) {
        return Result.success(foodService.save(food));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody Food food) {
        food.setId(id);
        return Result.success(foodService.updateById(food));
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(foodService.removeById(id));
    }
    
    @GetMapping("/category/{category}")
    public Result<List<Food>> getByCategory(@PathVariable String category) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("category", category).eq("status", 1);
        return Result.success(foodService.list(wrapper));
    }
    
    @PutMapping("/{id}/popularity")
    public Result<Boolean> increasePopularity(@PathVariable Long id) {
        Food food = foodService.getById(id);
        if (food != null) {
            food.setPopularity(food.getPopularity() + 1);
            return Result.success(foodService.updateById(food));
        }
        return Result.error("美食不存在");
    }
    
    @GetMapping("/search")
    public Result<List<Food>> search(@RequestParam String keyword) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.like("name", keyword).or().like("description", keyword);
        return Result.success(foodService.list(wrapper));
    }
}
