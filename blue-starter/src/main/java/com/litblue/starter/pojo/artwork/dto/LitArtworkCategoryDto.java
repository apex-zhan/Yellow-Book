package com.litblue.starter.pojo.artwork.dto;

import com.litblue.starter.pojo.artwork.domain.LitArtworkCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkCategoryDto extends LitArtworkCategory {
}
