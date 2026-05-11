package com.yanbian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.Carousel;
import com.yanbian.mapper.CarouselMapper;
import com.yanbian.service.CarouselService;
import org.springframework.stereotype.Service;

@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
}
