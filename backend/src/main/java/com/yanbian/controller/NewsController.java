package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanbian.common.Result;
import com.yanbian.entity.News;
import com.yanbian.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    
    private final NewsService newsService;
    
    @GetMapping("/list")
    public Result<Page<News>> list(@RequestParam(defaultValue = "1") Long current,
                                    @RequestParam(defaultValue = "10") Long size) {
        return Result.success(newsService.page(new Page<>(current, size)));
    }
    
    @GetMapping("/hot")
    public Result<List<News>> getHotNews() {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.eq("is_hot", 1).orderByDesc("view_count").last("LIMIT 5");
        return Result.success(newsService.list(wrapper));
    }
    
    @GetMapping("/latest")
    public Result<List<News>> getLatestNews() {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time").last("LIMIT 3");
        return Result.success(newsService.list(wrapper));
    }
    
    @GetMapping("/{id}")
    public Result<News> getById(@PathVariable Long id) {
        return Result.success(newsService.getById(id));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody News news) {
        return Result.success(newsService.save(news));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody News news) {
        news.setId(id);
        return Result.success(newsService.updateById(news));
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(newsService.removeById(id));
    }
    
    @PutMapping("/{id}/view")
    public Result<Boolean> increaseViewCount(@PathVariable Long id) {
        News news = newsService.getById(id);
        if (news != null) {
            news.setViewCount(news.getViewCount() + 1);
            return Result.success(newsService.updateById(news));
        }
        return Result.error("新闻不存在");
    }
}
