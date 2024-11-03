package com.litblue.starter.pojo.im.properties;


import lombok.Data;

import java.util.Date;


/**
 * 消息列表
 */
@Data
public class MessageStatistics {

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 发送人头像
     */
    private String sendUserAvatarUrl;

    /**
     * 发送人名称
     */
    private String sendUserNickName;

    /**
     * 发送内容
     */
    private String sendText;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 未读条数
     */
    private String msgTotal;

    /**
     * 接收人id
     */
    private String acceptUserId;

    /**
     * 发送人是否在线
     */
    private Boolean isOnline;
}
