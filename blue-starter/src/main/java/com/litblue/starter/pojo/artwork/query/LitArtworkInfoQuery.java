package com.litblue.starter.pojo.artwork.query;


import com.litblue.starter.core.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * 作品查询条件
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class LitArtworkInfoQuery extends BaseEntity {

    /**
     * 发布人id
     */
    private String userId;
    /**
     * 发布状态
     */
    private String publishStatus;

    /**
     * 作品可见状态
     */
    private String artAuthStatus;

    /**
     * 发布城市
     */
    private String publishCity;

    /**
     * 作品类型(0 图文 1 视频)
     */
    private String artworkType;

    /**
     * 关注id
     */
    private List<String> focusIds;

    /**
     * 查询内容
     */
    private String searchContent;

}
