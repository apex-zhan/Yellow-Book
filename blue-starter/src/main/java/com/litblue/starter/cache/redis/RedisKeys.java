package com.litblue.starter.cache.redis;

import com.litblue.starter.pojo.artwork.dto.LitArtworkUserDto;
import com.litblue.starter.pojo.artwork.query.LitArtworkInfoQuery;
import lombok.Data;

/**
 * redis令牌管理
 */
@Data
public class RedisKeys {

    /**
     * 公钥key
     */
    public final static String PUBLIC_KEY = "publicKey";

    /**
     * 私钥key
     */
    public final static String PRIVATE_KEY = "privateKey";

    /**
     * token前缀
     */
    public final static String DEFINE_TOKEN = "Authorization:login:token:";

    /**
     * 在线key前缀
     */
    public final static String AUTH_IS_LOGIN = "Authorization:login:session";

    /**
     * 所有作品正则
     */
    public final static String LIT_ALL_ARTWORK_VOS_KEY = "litArtworkInfoVos:*";

    /**
     * 查询所有作品key
     */
    public static String LIT_ARTWORK_INFO_VOS_KEY;

    /**
     * 作品操作key
     */
    public static String LIT_ARTWORK_USER_KEY;

    /**
     * 点赞收藏统计key
     */
    public static String LIT_ARTWORK_TOTAL_KEY;

    /**
     * 刷新作品key
     */
    public static String LIT_ARTWORK_INFO_KEY;

    /**
     * 附近推荐key
     */
    public static String CITY_MAP_KEY;

    /**
     * 用户key
     */
    public static String USER_KEY;

    /**
     * 对话消息内容
     */
    public static String CHAT_IM_KEY;

    /**
     * 访客key
     */
    public static String VISITOR_KEY;

    /**
     * 存储访客数量
     */
    public static String GENDER_VISITOR_NUMS_KEY;
    /**
     *
     * 绑定用户关系key
     */
    public static String BIND_USER_REL_KEY;

    public RedisKeys() {}

    /**
     * 作品列表信息
     * @param loginId
     * @param litArtworkInfoQuery
     */
    public static RedisKeys forArtworkInfoVos(String loginId,LitArtworkInfoQuery litArtworkInfoQuery) {
        RedisKeys keys = new RedisKeys();
        keys.LIT_ARTWORK_INFO_VOS_KEY = "litArtworkInfoVos:" + litArtworkInfoQuery + loginId;
        return keys;
    }

    /**
     * 获取用户作品信息
     * @param litArtworkUserDto
     * @param keyPrefix
     */
    public static RedisKeys forArtworkUser(LitArtworkUserDto litArtworkUserDto, String keyPrefix) {
        RedisKeys keys = new RedisKeys();
        keys.LIT_ARTWORK_USER_KEY = "litArtworkUser:" + litArtworkUserDto.getUserId() + ":" + litArtworkUserDto.getArtworkId()  + ":" + keyPrefix;
        return keys;
    }

    /**
     * 作品统计信息
     * @param artworkId
     * @param keyPrefix
     */
    public static RedisKeys forArtworkTotal(Long artworkId, String keyPrefix) {
        RedisKeys keys = new RedisKeys();
        keys.LIT_ARTWORK_TOTAL_KEY = artworkId + ":" + keyPrefix;
        return keys;
    }

    /**
     * 获取作品信息
     * @param loginId
     * @param artworkId
     */
    public static RedisKeys forArtworkInfo(String loginId, String artworkId) {
        RedisKeys keys = new RedisKeys();
        keys.LIT_ARTWORK_INFO_KEY = "litArtworkInfo:" + artworkId + ":" + loginId;
        return keys;
    }

    /**
     * 获取地图信息
     * @param latitude 纬度
     * @param longitude 经度
     * @param loginId 登录id
     */
    public static RedisKeys forCityMap(String latitude, String longitude, String loginId) {
        RedisKeys keys = new RedisKeys();
        keys.CITY_MAP_KEY = "cityInfo:" + latitude  + ":" + longitude + ":" + loginId;
        return keys;
    }

    /**
     * 获取用户信息
     * @param userId
     */
    public static RedisKeys forUser(String userId) {
        RedisKeys keys = new RedisKeys();
        keys.USER_KEY = "userInfo:" + userId;
        return keys;
    }

    /**
     * im通讯消息
     * @param userId
     */
    public static RedisKeys forChatIm(String userId) {
        RedisKeys keys = new RedisKeys();
        keys.CHAT_IM_KEY = "chat:" + userId;
        return keys;
    }

    /**
     * 获取访客信息
     * @param hisId
     * @param mineId
     * @return
     */
    public static RedisKeys forVisitor(String hisId,String mineId) {
        RedisKeys keys = new RedisKeys();
        keys.VISITOR_KEY = "vis:" + mineId + ":" + hisId;
        return keys;
    }

    /**
     * 生成访客数量
     * @param userId
     * @return
     */
    public static RedisKeys genderVisitorNums(String userId) {
        RedisKeys keys = new RedisKeys();
        keys.GENDER_VISITOR_NUMS_KEY = "visNums:" + userId;
        return keys;
    }


    /**
     * 绑定用户关系
     * @param applyUserId
     * @param targetUserId
     * @param keyPrefix
     * @return
     */
    public static RedisKeys forBingUserRelation(String applyUserId,String targetUserId,String keyPrefix) {
        RedisKeys keys = new RedisKeys();
        keys.BIND_USER_REL_KEY = "bind:" + applyUserId + ":" + targetUserId + ":" + keyPrefix;
        return keys;
    }
}