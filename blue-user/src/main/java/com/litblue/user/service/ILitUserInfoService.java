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
}
