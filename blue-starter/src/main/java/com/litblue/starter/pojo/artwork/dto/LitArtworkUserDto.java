package com.litblue.starter.pojo.artwork.dto;

import com.litblue.starter.pojo.artwork.domain.LitArtworkInfo;
import com.litblue.starter.pojo.artwork.domain.LitArtworkUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkUserDto  extends LitArtworkUser {

//    private Boolean firstEnter = false;
}
