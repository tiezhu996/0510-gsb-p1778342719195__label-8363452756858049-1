package com.yanbian.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanbian.common.Result;
import com.yanbian.entity.Webmaster;
import com.yanbian.service.WebmasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/webmaster")
@RequiredArgsConstructor
public class WebmasterController {
    
    private final WebmasterService webmasterService;
    
    @GetMapping("/info")
    public Result<Webmaster> getInfo() {
        QueryWrapper<Webmaster> wrapper = new QueryWrapper<>();
        wrapper.last("LIMIT 1");
        return Result.success(webmasterService.getOne(wrapper));
    }
    
    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Webmaster webmaster) {
        return Result.success(webmasterService.save(webmaster));
    }
    
    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody Webmaster webmaster) {
        webmaster.setId(id);
        return Result.success(webmasterService.updateById(webmaster));
    }
    
    @GetMapping("/contact")
    public Result<Map<String, String>> getContact() {
        Webmaster webmaster = webmasterService.getOne(new QueryWrapper<Webmaster>().last("LIMIT 1"));
        if (webmaster != null) {
            Map<String, String> contact = new HashMap<>();
            contact.put("email", webmaster.getEmail());
            contact.put("phone", webmaster.getPhone());
            contact.put("wechat", webmaster.getWechat());
            contact.put("qq", webmaster.getQq());
            return Result.success(contact);
        }
        return Result.error("站长信息不存在");
    }
}
