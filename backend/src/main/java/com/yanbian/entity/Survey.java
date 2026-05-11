package com.yanbian.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("survey")
public class Survey {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String ageRange;
    private String visitPurpose;
    private Integer satisfaction;
    private String suggestions;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
