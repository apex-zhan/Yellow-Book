package com.litblue.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.domian.LitUserInfo;

public interface ILitUserInfoService extends IService<LitUserInfo> {
    AjaxResult getPhoneCode(String phone);
}
