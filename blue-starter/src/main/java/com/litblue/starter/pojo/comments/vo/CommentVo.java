package com.litblue.starter.pojo.comments.vo;

import com.litblue.starter.pojo.comments.domain.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommentVo extends Comment {

    /**
     * 子回复
     */
    private List<CommentVo> childRenComment;

    /**
     * 点赞数量
     */
    private Integer likeNums;

    /**
     * 登陆人是否已经点赞
     */
    private Boolean isLike;

    /**
     * 时间处理
     */
    private String publishTime;
}
