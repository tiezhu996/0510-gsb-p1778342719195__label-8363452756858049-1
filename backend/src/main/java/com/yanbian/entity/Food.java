package com.yanbian.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("food")
public class Food {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String detailContent;
    private String coverImage;
    private String images;
    private String category;
    private String priceRange;
    private BigDecimal rating;
    private Integer popularity;
    private Integer isFeatured;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
