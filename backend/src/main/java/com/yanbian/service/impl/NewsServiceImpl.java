package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.News;
import com.yanbian.mapper.NewsMapper;
import com.yanbian.service.NewsService;
import org.springframework.stereotype.Service;
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {}
