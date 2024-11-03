package com.litblue.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litblue.starter.cache.redis.RedisCache;
import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.domian.LitUserInfo;
import com.litblue.user.mapper.LitUserInfoMapper;
import com.litblue.user.service.ILitUserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utils.VerificationCodeGenerator;

@Service
@AllArgsConstructor
public class ILitUserInfoServiceImpl extends ServiceImpl<LitUserInfoMapper, LitUserInfo> implements ILitUserInfoService {

    private LitUserInfoMapper litUserInfoMapper;
    private RedisCache redisCache;

    /**
     * 生成验证码
     *
     * @param phone
     * @return
     */
    @Override
    public AjaxResult getPhoneCode(String phone) {
        String code = VerificationCodeGenerator.generateVerificationCode();
        this.redisCache.setCacheObject("phoneCode:" + phone, code, 5, java.util.concurrent.TimeUnit.MINUTES);
        return new AjaxResult("短信发送成功", "200");
    }
}
