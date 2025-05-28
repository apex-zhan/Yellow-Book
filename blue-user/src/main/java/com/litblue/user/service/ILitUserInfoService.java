package com.litblue.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.domian.LitUserInfo;
import com.litblue.starter.pojo.user.dto.LitUserInfoDto;

public interface ILitUserInfoService extends IService<LitUserInfo> {
    AjaxResult getPhoneCode(String phone);

    /**
     * 用户登录
     *
     * @param litUserInfoDto
     * @return
     */
    AjaxResult loginUser(LitUserInfoDto litUserInfoDto);

    /**
     * 用户退出
     *
     * @return
     */
    AjaxResult logout();

    /**
     * 编辑用户信息接口
     *
     * @param litUserInfo
     * @return
     */

    AjaxResult editUserInfo(LitUserInfo litUserInfo);

    /**
     * 查询用户信息接口
     *
     * @param userId
     * @return
     */

    LitUserInfo queryUserInfo(Long userId);
}
