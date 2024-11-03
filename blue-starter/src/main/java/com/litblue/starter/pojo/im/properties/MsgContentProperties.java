package com.litblue.starter.pojo.im.properties;

import com.litblue.starter.pojo.artwork.domain.LitArtworkInfo;
import com.litblue.starter.pojo.artwork.vo.LitArtworkInfoVo;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息内容
 */
@Data
public class MsgContentProperties {

    /**
     * 发送类型 (0 群发 1 单发)
     */
    private String sendType;

    /**
     * 消息类型 (0 文本对话
     *          1 文件
     *          2 作品分享
     *          3 发送图片
     *          4 发起视频对话请求
     *          5 接收视频对话
     *          6 拒绝视频对话)
     */
    private String msgType;

    /**
     * 其他业务消息结构体
     */
    private Map<String,Object> messageMap = new HashMap<>();

    /**
     * 消息内容
     */
    private String msgContent;


    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 接收人id(在两人对话聊天，或者分享的时候携带)
     */
    private List<String> acceptUserId;

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
