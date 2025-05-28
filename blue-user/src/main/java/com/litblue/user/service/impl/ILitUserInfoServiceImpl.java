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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utils.RandomUtils;
import utils.UserContext;
import utils.VerificationCodeGenerator;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
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
        this.redisCache.setCacheObject("phoneCode:" + phone, code, 10, TimeUnit.MINUTES);
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
            log.error(e.getMessage());
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
            /**
             * 硬编码问题：私钥 RedisKeys.PRIVATE_KEY 被硬编码在代码中，这是一个安全隐患
             */
            //加密后的密码
            String pass = litUserInfo.getPass();
            Object aPublic = this.redisCache.getCacheObject(RedisKeys.PUBLIC_KEY);
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
        if (StpUtil.isLogin(litUserInfo.getId())) {
            return new AjaxResult("您已经登录过了", "200");
        }
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

    /**
     * 用户退出
     */
    @Override
    public AjaxResult logout() {
        try {
            if (StpUtil.isLogin()) {
                // 获取当前登录用户的ID
                Object loginId = StpUtil.getLoginId();
                // 清理相关缓存，例如用户的token缓存
                redisCache.deleteObject(RedisKeys.DEFINE_TOKEN + loginId);
                // 清理在线状态缓存
                redisCache.deleteObject(RedisKeys.AUTH_IS_LOGIN + loginId);

                // 用户退出登录
                StpUtil.logout();
                log.info("用户 {} 退出成功", loginId);
                return new AjaxResult("退出成功", "200", null);
            }
            log.info("用户未登录，无法执行退出操作");
            return new AjaxResult("用户未登入", "", null);
        } catch (Exception e) {
            log.error("用户退出时发生异常", e);
            return new AjaxResult("退出失败，发生异常", "500", null);
        }
    }

    /**
     * 编辑用户信息接口
     *
     * @param litUserInfo
     * @return
     */
    @Override
    public AjaxResult editUserInfo(LitUserInfo litUserInfo) {
        //1. 拿到用户id
        Long LoginUserId = UserContext.getUser();
        // 2. 将获取的用户ID设置到传入的用户信息对象中
        litUserInfo.setId(LoginUserId);
        //3. 更新数据库中的信息
        baseMapper.updateById(litUserInfo);
        //4. 设置redis缓存键，并删除缓存中原本的用户缓存信息，确保下次获取时从数据库加载最新数据
        RedisKeys redisKeysUserId = RedisKeys.forUser(String.valueOf(LoginUserId));
        redisCache.deleteObject(redisKeysUserId.USER_KEY);
        //调用查询用户接口
        this.queryUserInfo(LoginUserId);
        return new AjaxResult("修改成功", "200", litUserInfo);
    }

    /**
     * 查询用户信息接口
     *
     * @param userId
     * @return
     */

    @Override
    public LitUserInfo queryUserInfo(Long userId) {
        //设置redis键
        RedisKeys redisKeys = RedisKeys.forUser(String.valueOf(userId));
        LitUserInfo litUserInfo = null;
        //先从缓存中查询
        litUserInfo = this.redisCache.getCacheObject(redisKeys.USER_KEY);
        if (litUserInfo != null) {
            return litUserInfo;
        }
        //如果缓存中没有，查询数据库，在添加到缓存中
        litUserInfo = litUserInfoMapper.selectById(userId);
        if (litUserInfo != null) {
            redisCache.setCacheObject(redisKeys.USER_KEY, litUserInfo);
        }
        return litUserInfo;
    }
}