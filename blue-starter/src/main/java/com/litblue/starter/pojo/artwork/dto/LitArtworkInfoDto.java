package com.litblue.starter.pojo.artwork.dto;

import com.litblue.starter.pojo.artwork.domain.LitArtworkInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkInfoDto extends LitArtworkInfo {

    /**
     * 所选图片集
     */
    private List<String> artworkImgArraySize;

    /**
     * 所选分类集
     */
    private List<String> categoryIdArray;

    /**
     * 拒绝好友组可见
     */
    private List<String> refuseIdArray;
}
