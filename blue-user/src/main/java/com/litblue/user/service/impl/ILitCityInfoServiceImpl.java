package com.litblue.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litblue.starter.cache.redis.RedisCache;
import com.litblue.starter.cache.redis.RedisKeys;
import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.dto.GetCityDto;
import com.litblue.user.properties.MapProperties;
import com.litblue.user.service.ILitCityInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utils.HttpClientUtils;
import utils.UserContext;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ILitCityInfoServiceImpl extends ServiceImpl implements ILitCityInfoService {

    //添加腾讯云的位置接口
    private final MapProperties mapProperties;
    private final RedisCache redisCache;

    /**
     * 获取城市信息
     *
     * @param getCityDto
     * @return
     */
    @Override
    public AjaxResult getCityInfo(GetCityDto getCityDto) {
        //1. 获取经纬度
        String latitude = getCityDto.getLatitude();
        String longitude = getCityDto.getLongitude();

        //2. 拿到用户的信息，因为每一个用户都会有一个唯一的id，所以可以通过这个id来获取用户的信息
        Long LoginUser = UserContext.getUser();
        //3.获取位置信息
        String PerimeterUrl = mapProperties.getPerimeterUrl();
        //4.设置缓存键名
        RedisKeys redisKeys = RedisKeys.forCityMap(latitude, longitude, LoginUser.toString());
        List<Map> citySize = null;
        citySize = redisCache.getCacheList(redisKeys.CITY_MAP_KEY);
        if (!CollectionUtil.isEmpty(citySize)) {
            return new AjaxResult("调用成功", "200", citySize);
        }
        //获取周边推荐的url
        StringBuilder url = new StringBuilder(PerimeterUrl);
        url.append("?key=")
                .append(mapProperties.getKey())
                .append("&boundary=nearby(")
                .append(latitude)
                .append(",")
                .append(longitude)
                .append(",")
                .append(mapProperties.getRadius())
                .append(")");
        try {
            //5.调用腾讯地图的接口
            String result = HttpClientUtils.doGet(String.valueOf(url));
            //6.解析数据,将result转换为json对象,然后获取status和data
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getInteger("status") == 0) {
                //需要将data转换为list，与第三方接口返回的数据结构一致
                citySize = (List<Map>) jsonObject.get("data");
                if (!CollectionUtil.isEmpty(citySize)) {
                    //7.将数据存入redis
                    redisCache.setCacheList(redisKeys.CITY_MAP_KEY, citySize);
                }
                return new AjaxResult("调用成功", "200", citySize);
            } else {
                return new AjaxResult("调用失败", "500",citySize);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
