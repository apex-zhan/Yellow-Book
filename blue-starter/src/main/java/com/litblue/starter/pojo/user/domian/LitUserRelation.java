package com.litblue.starter.pojo.user.domian;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.litblue.starter.core.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户关系
 */
@Data

public class LitUserRelation extends BaseEntity {


    /**
     * 主键
     */
    private Long id;
    /**
     * 发起用户id
     */
    private Long applyUserId;
    /**
     * 目标用户id
     */
    private Long targetUserId;
    /**
     * 关系状态(0 关注 1 拉黑 2 取消关注 3 移除拉黑)
     */
    private String relationType;
    /**
     * 状态名称
     */
    private String typeName;

    /**
     * 数据状态 1-正常 2-删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;

}
