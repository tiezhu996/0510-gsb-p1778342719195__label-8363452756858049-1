package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.Video;
import com.yanbian.mapper.VideoMapper;
import com.yanbian.service.VideoService;
import org.springframework.stereotype.Service;
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {}
