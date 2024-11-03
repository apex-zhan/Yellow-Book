package com.litblue.starter.pojo.user.vo;


import com.litblue.starter.pojo.user.domian.LitUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LitUserInfoVo extends LitUserInfo {
}
