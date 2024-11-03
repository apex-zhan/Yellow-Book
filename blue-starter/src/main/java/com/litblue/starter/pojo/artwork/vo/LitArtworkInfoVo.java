package com.litblue.starter.pojo.artwork.vo;

import com.litblue.starter.pojo.artwork.domain.LitArtworkInfo;
import com.litblue.starter.pojo.comments.domain.Comment;
import com.litblue.starter.pojo.comments.vo.CommentVo;
import com.litblue.starter.pojo.user.domian.LitUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkInfoVo extends LitArtworkInfo {

    /**
     * 作品评论
     */
    private List<CommentVo> commentList;
    /**
     * 用户信息
     */
    private LitUserInfo litUserInfo;
    /**
     * 作品图片信息
     */
    private List<String> artworkImgArraySize;

    /**
     * 分类tag
     */
    private List<String> categoryIdArray;

    /**
     * 喜欢点赞数
     */
    private Integer likeNums;

    /**
     * 登陆人是否赞过
     */
    private Boolean isLike;

    /**
     * 评论数量
     */
    private Integer commentNums;

    /**
     * 收藏数量
     */
    private Integer favoritesNums;

    /**
     * 登陆人是否收藏过
     */
    private Boolean isFavorites;
}
