package com.litblue.starter.mq;

/**
 * 作品队列配置
 */
public interface MqExChangeArtWorkInterProperties {

    //文章交换机
    public static final String DIRECT_ARTWORK = "artwork.direct";

    //文章新增队列
    public static final String QUEUE_ARTWORK_ADD = "queue.artwork.add";

    //文章移除队列
    public static final String QUEUE_ARTWORK_DEL = "queue.artwork.del";


    //点赞新增添加key
    public static final String ROUTING_ARTWORK_KEY_ADD = "art.add";


    //点赞移除添加key
    public static final String ROUTING_ARTWORK_KEY_REMOVE = "art.del";
}