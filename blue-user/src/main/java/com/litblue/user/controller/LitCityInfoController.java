package com.litblue.user.controller;

import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.dto.GetCityDto;
import com.litblue.user.service.ILitCityInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取城市信息
 */
@RestController
@RequestMapping("/user/city")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LitCityInfoController {
    private final ILitCityInfoService litCityInfoService;

    /**
     * 获取城市信息
     *
     * @param
     * @return
     */
    @RequestMapping("/getCityInfo")
    public AjaxResult getCityInfo(@RequestBody GetCityDto getCityDto) {
        return litCityInfoService.getCityInfo(getCityDto);
    }

}
