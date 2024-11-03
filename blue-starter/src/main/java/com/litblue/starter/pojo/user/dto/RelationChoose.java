package com.litblue.starter.pojo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户操作可选项
 */
@Data
@AllArgsConstructor
public class RelationChoose {

    /**
     * 关系状态
     */
    private String relationType;
    /**
     * 状态名称
     */
    private String typeName;

    /**
     * 图标地址
     */
    private String iconUrl;
}
