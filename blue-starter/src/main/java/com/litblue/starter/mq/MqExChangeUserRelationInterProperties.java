package com.litblue.starter.mq;

/**
 * 用户关系队列
 */
public interface MqExChangeUserRelationInterProperties {

    //用户关系交换机
    public static final String DIRECT_RELATION = "relation.direct";

    //用户关注操作队列
    public static final String QUEUE_RELATION_FOCUS = "queue.relation.focus";

    //用户拉黑操作队列
    public static final String QUEUE_RELATION_LOCK = "queue.relation.lock";

    //用户取消关注操作队列
    public static final String QUEUE_RELATION_UN_FOCUS = "queue.relation.unfocus";

    //用户移除拉黑操作队列
    public static final String QUEUE_RELATION_UN_LOCK = "queue.relation.unlock";


    //用户关注key
    public static final String ROUTING_RELATION_KEY_FOCUS = "relation.focus";


    //用户拉黑key
    public static final String ROUTING_RELATION_KEY_LOCK = "relation.lock";


    //用户取消关注key
    public static final String ROUTING_RELATION_KEY_UN_FOCUS = "relation.unfocus";


    //用户移除拉黑key
    public static final String ROUTING_RELATION_KEY_UN_LOCK = "relation.unlock";
}