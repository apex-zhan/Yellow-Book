package com.litblue.starter.pojo.user.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class GetCityDto {

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;
}
