package com.litblue.starter.pojo.artwork.domain;


import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.litblue.starter.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
* 作品操作统计表
* @TableName lit_artwork_user
*/
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkUser extends BaseEntity {

    /**
    * 主键
    */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
    * 作品id
    */
    private Long artworkId;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 操作类型(0 点赞 1 收藏)
    */
    private String operateType;
    /**
    * 类型名称
    */
    private String operateTypeName;


    /**
    * 数据状态 1-正常 2-删除
    */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;

}
