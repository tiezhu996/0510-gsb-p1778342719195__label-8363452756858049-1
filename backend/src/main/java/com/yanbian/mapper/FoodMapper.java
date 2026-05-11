package com.yanbian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanbian.entity.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
}
