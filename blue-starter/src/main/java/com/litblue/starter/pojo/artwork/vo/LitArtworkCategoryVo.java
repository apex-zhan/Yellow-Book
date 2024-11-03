package com.litblue.starter.pojo.artwork.vo;


import com.litblue.starter.pojo.artwork.domain.LitArtworkCategory;
import com.litblue.starter.core.AjaxResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitArtworkCategoryVo extends LitArtworkCategory {


}
