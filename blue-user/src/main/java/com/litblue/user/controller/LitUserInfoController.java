package com.litblue.user.controller;

import com.litblue.api.client.GetArtWorkClient;
import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.domian.LitUserInfo;
import com.litblue.starter.pojo.user.dto.LitUserInfoDto;
import com.litblue.user.service.ILitUserInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用户服务
 */
@RestController
@RequestMapping("/user/info")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LitUserInfoController {
    private final ILitUserInfoService litUserInfoService;
    private final GetArtWorkClient getArtWorkClient;

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

    @GetMapping("/logout")
    public AjaxResult logout() {
        return litUserInfoService.logout();
    }

    @GetMapping("/testClient")
    public AjaxResult testClient() {
        //获取服务1
        String testContent = getArtWorkClient.getTestContent();
        //获取服务2
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", "服务2");
        map.put("testContent", testContent);
        return new AjaxResult(testContent + "查询成功", "200");
    }

    /**
     * 编辑用户信息接口
     *
     * @param litUserInfo
     * @return
     */

    @PostMapping("/editUserInfo")
    public AjaxResult editUserInfo(@RequestBody LitUserInfo litUserInfo) {
        return litUserInfoService.editUserInfo(litUserInfo);
    }

    /**
     * 查询用户信息接口
     *
     * @param userId
     * @return
     */
    @GetMapping("/queryUserInfo")
    public LitUserInfo queryUserInfo(Long userId) {
        return litUserInfoService.queryUserInfo(userId);
    }

}

