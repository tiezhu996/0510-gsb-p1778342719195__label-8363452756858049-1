package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.Introduction;
import com.yanbian.mapper.IntroductionMapper;
import com.yanbian.service.IntroductionService;
import org.springframework.stereotype.Service;
@Service
public class IntroductionServiceImpl extends ServiceImpl<IntroductionMapper, Introduction> implements IntroductionService {}
