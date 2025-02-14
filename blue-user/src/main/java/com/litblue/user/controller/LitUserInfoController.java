package com.litblue.user.controller;

import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.dto.LitUserInfoDto;
import com.litblue.user.service.ILitUserInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 */
@RestController
@RequestMapping("/user/info")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LitUserInfoController {
    private final ILitUserInfoService litUserInfoService;

    @ApiOperation("生成验证码")
    @GetMapping("/genPhoneCode")
    public AjaxResult getPhoneCode(String phone) {
        return litUserInfoService.getPhoneCode(phone);
    }

    @ApiOperation("登录用户信息")
    @GetMapping("/loginUser")
    public AjaxResult loginUser(@RequestBody LitUserInfoDto litUserInfoDto) {
        return litUserInfoService.loginUser(litUserInfoDto);
    }


}
