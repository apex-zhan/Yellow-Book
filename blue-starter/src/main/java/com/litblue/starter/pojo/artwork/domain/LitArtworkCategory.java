package com.litblue.starter.pojo.artwork.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.litblue.starter.core.BaseEntity;
import lombok.Data;

@Data
public class LitArtworkCategory extends BaseEntity {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String categoryName;

    /**
     * 数据状态 1-正常 2-删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;
}
