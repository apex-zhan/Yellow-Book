package com.litblue.starter.pojo.comments.dto;

import com.litblue.starter.pojo.comments.domain.TotalComments;
import lombok.Data;

@Data
public class TotalCommentsDto extends TotalComments {

    private String artworkId;
}
