package com.yanbian.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("webmaster")
public class Webmaster {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String avatar;
    private String signature;
    private String introduction;
    private String email;
    private String phone;
    private String wechat;
    private String qq;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
