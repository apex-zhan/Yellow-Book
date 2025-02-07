package com.litblue.user.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litblue.starter.cache.redis.RedisCache;
import com.litblue.starter.cache.redis.RedisKeys;
import com.litblue.starter.core.AjaxResult;
import com.litblue.starter.pojo.user.domian.LitUserInfo;
import com.litblue.starter.pojo.user.dto.LitUserInfoDto;
import com.litblue.user.mapper.LitUserInfoMapper;
import com.litblue.user.service.ILitUserInfoService;
import exception.BizIllegalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utils.RandomUtils;
import utils.VerificationCodeGenerator;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        this.redisCache.setCacheObject("phoneCode:" + phone, code, 5, TimeUnit.MINUTES);
        return new AjaxResult("短信发送成功", "200");
    }

    /**
     * 加载密钥
     */
    @PostConstruct
    public void initAuthKey() {
        String puk = this.redisCache.getCacheObject(RedisKeys.PUBLIC_KEY);
        String prk = this.redisCache.getCacheObject(RedisKeys.PRIVATE_KEY);
        if (puk != null && prk != null) {
            return;
        }
        try {
//            生成一对公钥和私钥，其中Map对象 (private=私钥, public=公钥)
            HashMap<String, String> map = SaSecureUtil.rsaGenerateKeyPair();
            String aPublicKey = map.get("public").toString();
            String aPrivateKey = map.get("private").toString();
            this.redisCache.setCacheObject(RedisKeys.PUBLIC_KEY, aPublicKey);
            this.redisCache.setCacheObject(RedisKeys.PRIVATE_KEY, aPrivateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户登录
     *
     * @param litUserInfoDto
     * @return
     */
    @Override
    public AjaxResult loginUser(LitUserInfoDto litUserInfoDto) {
        //1. 获取登录方式
        String loginMethod = litUserInfoDto.getLoginMethod();
        LitUserInfo litUserInfo = null;
        //账号密码登录
        if ("0".equals(loginMethod)) {
            //1.获取用户名和密码
            String userName = litUserInfoDto.getUserName();
            String passWord = litUserInfoDto.getPassWord();
            LambdaQueryWrapper<LitUserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
            LambdaQueryWrapper<LitUserInfo> litUserInfoLambdaQueryWrapper = userInfoLambdaQueryWrapper.eq(LitUserInfo::getUserName, userName);
            litUserInfo = this.baseMapper.selectOne(litUserInfoLambdaQueryWrapper);
            if (litUserInfo == null) {
                throw new BizIllegalException("用户不存在");
            }
            //加密后的密码
            String pass = litUserInfo.getPass();
            Object aPrivate = this.redisCache.getCacheObject(RedisKeys.PUBLIC_KEY);
            //解析成明文
            String authPass = SaSecureUtil.rsaDecryptByPrivate(RedisKeys.PRIVATE_KEY, pass);
            if (!authPass.equals(passWord)) {
                throw new BizIllegalException("密码错误");
            }
        }
        // 验证码登录
        if ("1".equals(loginMethod)) {
            //获取手机号
            String phoneNumber = litUserInfoDto.getPhoneNumber();
            //获取验证码
            String code = litUserInfoDto.getCode();
            //获取redis中的验证码
            String redisCode = this.redisCache.getCacheObject("phoneCode:" + phoneNumber);
            //比较验证码是否一致
            if (redisCode == null || !code.equals(redisCode)) {
                throw new BizIllegalException("验证码错误");
            }
            LambdaQueryWrapper<LitUserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            LambdaQueryWrapper<LitUserInfo> litUserInfoLambdaQueryWrapper = lambdaQueryWrapper.eq(LitUserInfo::getPhoneNumber, phoneNumber);
            litUserInfo = this.baseMapper.selectOne(litUserInfoLambdaQueryWrapper);
            //如果为空，说明新用户
            if (litUserInfo == null) {
                litUserInfo = new LitUserInfo();
                litUserInfo.setUserName(RandomUtils.generateRandomUsername());
                litUserInfo.setBackImg("http://101.43.99.167:9000/blue-oss/1684082113-origin-7301FA2F-E003-4AF6-8440-79D7FEEA9B45.jpg");
                litUserInfo.setPersonDes("用户太懒了");
                litUserInfo.setPhoneNumber(phoneNumber);
                litUserInfo.setUniversity("手艺人大学");
                litUserInfo.setBirthday("");
                litUserInfo.setBlueCode("CODE" + RandomUtils.generateRandomUsername());
                litUserInfo.setNickName("NO" + RandomUtils.generateRandomUsername());
                litUserInfo.setAvatar("http://101.43.99.167:9000/blue-oss/dog.png");
                //生成随机密码
                String randomPassword = RandomUtils.generateRandomPassword();
                String pass = this.securityCodePass(randomPassword);
                litUserInfo.setPass(pass);
                litUserInfo.setCreateTime(new Date());
                litUserInfo.setCreateBy(litUserInfo.getNickName());
                this.baseMapper.insert(litUserInfo);
            }
            //短信已经被消费，清除缓存
            this.redisCache.deleteObject(phoneNumber);
        }
        //todo 微信登录
        if ("2".equals(loginMethod)) {
//            todo ....
        }
        //会话登录；参考文档：https://sa-token.cc/v/v1.38.0/doc.html#/api/stp-util?id=_2%e3%80%81%e7%99%bb%e5%bd%95%e7%9b%b8%e5%85%b3
        StpUtil.login(litUserInfo.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("token", tokenInfo);
        hashmap.put("userInfo", litUserInfo);
        return new AjaxResult("登录成功", "200", hashmap);
    }

    /**
     * 加密密码
     *
     * @param pass
     * @return
     */
    private String securityCodePass(String pass) {
        String publicKey = this.redisCache.getCacheObject(RedisKeys.PUBLIC_KEY);
        return SaSecureUtil.rsaEncryptByPublic(publicKey, pass);
    }
}