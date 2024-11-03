package com.litblue.starter.cache.redis;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Redis使用FastJson序列化
 *
 * @author zxw
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Class<T> clazz;

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * 序列化对象为字节数组
     * 此方法用于将给定的对象转换为字节流，以便于存储或传输
     *
     * @param t 要序列化的对象，如果为null，则返回空字节数组
     * @return 序列化后的字节数组如果输入为null，则返回长度为0的字节数组
     * @throws SerializationException 如果序列化过程中发生错误
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * 反序列化字节数组为对象
     * 此方法将字节数组转换回对象实例使用此方法前，应确保字节数组是通过对应的serialize方法序列化的
     *
     * @param bytes 表示对象的字节数组如果为null或空，则返回null
     * @return 反序列化后的对象实例如果输入为null或空字节数组，则返回null
     * @throws SerializationException 如果反序列化过程中发生错误
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz, JSONReader.Feature.SupportAutoType);
    }
}