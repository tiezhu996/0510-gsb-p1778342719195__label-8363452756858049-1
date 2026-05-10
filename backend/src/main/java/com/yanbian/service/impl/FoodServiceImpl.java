package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.Food;
import com.yanbian.mapper.FoodMapper;
import com.yanbian.service.FoodService;
import org.springframework.stereotype.Service;
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {}
