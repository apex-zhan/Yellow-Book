package com.litblue.user.service;

import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.dto.GetCityDto;

/**
 * 获取城市信息
 */
public interface ILitCityInfoService {
    /**
     * 获取城市信息
     *
     * @param getCityDto
     * @return
     */
    AjaxResult getCityInfo(GetCityDto getCityDto);
}
