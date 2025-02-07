package com.litblue.starter.pojo.user.domian;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.litblue.starter.core.BaseEntity;
import lombok.Data;

@Data
public class LitUserInfo extends BaseEntity {

    // 主键
    private Long id;

    // 用户名
    private String userName;

    // 密码
    private String pass;

    // 头像
    private String avatar;

    // 名称
    private String nickName;

    // 手机号
    private String phoneNumber;

    // 所属地区
    private String belongCity;

    // 小楠书号
    private String blueCode;

    // 个人简介
    private String personDes;

    // 性别
    private Character gender;

    // 生日
    private String birthday;

    // 职业
    private String worker;

    // 大学
    private String university;

    // 背景图
    private String backImg;

    // 登录状态，0正常，1禁止
    private Character loginStatus;

    // 关注数量
    private Integer focusNums;

    // 粉丝数量
    private Integer fansNums;

    // 喜欢数量
    private Integer likeNums;

    // 收藏数量
    private Integer favoritesNums;

    /**
     * 数据状态 1-正常 2-删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteStatus;

}
