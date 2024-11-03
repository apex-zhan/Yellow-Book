package com.litblue.starter.pojo.artwork.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.litblue.starter.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkInfo extends BaseEntity {
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 所选分类
     */
    private String categoryIds;
    /**
     * 作品标题
     */
    private String artworkTitle;
    /**
     * 作品类型(0 图文 1 视频)
     */
    private String artworkType;
    /**
     * 类型名称
     */
    private String artworkTypeName;
    /**
     * 作品描述及介绍
     */
    private String artworkDes;
    /**
     * 图片集
     */
    private String artworkImgArray;
    /**
     * 视频源
     */
    private String artworkVideo;

    /**
     * 播放量
     */
    private String playsNums;
    /**
     * 发布状态(0 待审核 1审核成功 2审核失败)
     */
    private String publishStatus;

    /**
     * 作品权限状态(0 公开可见 1 自己可见 2 部分可见)
     */
    private String artAuthStatus;
    /**
     * 发布缘由
     */
    private String publishReason;
    /**
     * 发布城市
     */
    private String publishCity;

    /**
     * 封面预览
     */
    private String previewImg;
    /**
     * 具体地址
     */
    private String cityDes;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;
}
