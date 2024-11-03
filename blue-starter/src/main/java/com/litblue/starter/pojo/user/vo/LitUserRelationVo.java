package com.litblue.starter.pojo.user.vo;

import com.litblue.starter.pojo.user.domian.LitUserInfo;
import com.litblue.starter.pojo.user.domian.LitUserRelation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitUserRelationVo extends LitUserRelation {
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
}
