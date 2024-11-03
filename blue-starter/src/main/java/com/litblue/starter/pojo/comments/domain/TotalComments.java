package com.litblue.starter.pojo.comments.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 点赞统计
 */
@Data
@Document(collection = "total_comments")
public class TotalComments {

    @Id
    private String id;
    private String commentId;
    private String userId;

}
