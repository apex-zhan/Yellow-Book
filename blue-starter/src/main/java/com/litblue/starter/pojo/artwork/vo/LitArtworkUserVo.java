package com.litblue.starter.pojo.artwork.vo;

import com.litblue.starter.pojo.artwork.domain.LitArtworkUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkUserVo extends LitArtworkUser {
}
