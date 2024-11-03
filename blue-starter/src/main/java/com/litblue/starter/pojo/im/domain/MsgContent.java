package com.litblue.starter.pojo.im.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *聊天記錄
 */
@Data
@Document(collection = "msg_content")
public class MsgContent {


    @Id
    private String id;
    /**
     * 发送类型 (0 群发 1 单发)
     */
    private String sendType;

    /**
     * 消息类型 (0文本 1文件 2作品分享)
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 其他业务消息结构体
     */
    private String messageMap;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 发送人id
     */
    private String sendUserId;




    /**
     * 接收人id(在单发的时候触发)
     */
    private String acceptUserId;



    /**
     * 群组id(在群发的时候携带)
     */
    private String groupId;

    /**
     * 发送时间
     */
    private Date sendTime;


    /**
     * 消息是否已读(0已读 1未读)
     */
    private Boolean isRead;

    /**
     * 消息状态名称
     */
    private String readName;
}
