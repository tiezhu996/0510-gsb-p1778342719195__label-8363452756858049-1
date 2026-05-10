package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.ScenicSpot;
import com.yanbian.mapper.ScenicSpotMapper;
import com.yanbian.service.ScenicSpotService;
import org.springframework.stereotype.Service;
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements ScenicSpotService {}
