package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.Survey;
import com.yanbian.mapper.SurveyMapper;
import com.yanbian.service.SurveyService;
import org.springframework.stereotype.Service;
@Service
public class SurveyServiceImpl extends ServiceImpl<SurveyMapper, Survey> implements SurveyService {}
