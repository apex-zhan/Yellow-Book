package com.litblue.starter.pojo.user.vo;

import com.litblue.starter.pojo.user.domian.LitUserInfo;
import com.litblue.starter.pojo.user.dto.RelationChoose;
import lombok.Data;

import java.util.List;

@Data
public class LitHomeUserDataVo {

    /**
     * 用户信息
     */
    private LitUserInfo litUserInfo;

    /**
     * 操作名称
     */
    private String editName;

    /**
     * 和本人的好友关系
     */
    private String isLoginCurrentUser;

    /**
     * 可操作权限
     */
    private List<RelationChoose> relationChooseList;
}
