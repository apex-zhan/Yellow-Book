package com.litblue.starter.pojo.user.domian;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.litblue.starter.core.BaseEntity;
import lombok.Data;

/**
 * 用户信息
 *
 * @author MECHREVO
 */
@Data
public class LitUserInfo extends BaseEntity {

    // 主键
    @TableId(type = IdType.ASSIGN_ID)
    @JSONField(serializeUsing = String.class)
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

    // 小黄书号
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
