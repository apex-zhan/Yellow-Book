package com.litblue.starter.pojo.comments.domain;


import java.util.Date;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 作品评论表
 *
 * @TableName lit_blue_comments
 */
@Data
@Document(collection = "comments")
public class Comment {

    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 作品id
     */
    private String artworkId;
    /**
     * 发布人id
     */
    private String userId;

    /**
     * 发布人头像
     */
    private String avatarUrl;

    /**
     * 发布人名称
     */
    private String nickName;
    /**
     * 评论类型(0 文字 1 图片 3 文字加图片)
     */

    private String commentType;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论图片
     */
    private String commentImg;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 回复人id
     */
    private String replyId;

    /**
     * 回复人名称
     */
    private String replyName;
    /**
     * 评论状态(0 待审核 1 已评论 2 评论失败)
     */
    private String commentStatus;
    /**
     * 审核缘由
     */
    private String commentReason;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 发布地
     */
    private String publishCity;

    /**
     * 父评论的ID，顶级评论为 "0"
     */
    private String parentId;

}

