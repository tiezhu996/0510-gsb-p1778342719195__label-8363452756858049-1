package com.yanbian.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanbian.entity.Webmaster;
import com.yanbian.mapper.WebmasterMapper;
import com.yanbian.service.WebmasterService;
import org.springframework.stereotype.Service;
@Service
public class WebmasterServiceImpl extends ServiceImpl<WebmasterMapper, Webmaster> implements WebmasterService {}
