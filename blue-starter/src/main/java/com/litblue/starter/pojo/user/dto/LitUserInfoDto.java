package com.litblue.starter.pojo.user.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
/**
 * 登录用户信息
 */
public class LitUserInfoDto {

    /**
     * 登录方式(0,账号密码 1,手机号加验证码 3, 微信登录)
     */
    private String loginMethod;

    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 登录验证码
     */
    private String code;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;
}
