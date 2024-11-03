package com.litblue.starter.sql;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.litblue.starter.interceptor.MvcConfig;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import utils.UserContext;

import java.util.Date;

/**
 * 只能登录情况下才能填充
 */
@Component
@ConditionalOnClass(MvcConfig.class)
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 新增操作
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        String loginId = String.valueOf(UserContext.getUser());
        this.strictInsertFill(metaObject, "createTime", Date.class, now);
        this.strictInsertFill(metaObject, "createBy", String.class, loginId);
        this.strictInsertFill(metaObject, "updateTime", Date.class, now);
        this.strictInsertFill(metaObject, "updateBy", String.class, loginId);
        // 默认设置状态1，正常显示
        this.setFieldValByName("deleteStatus", 1, metaObject);
    }

    /**
     * 修改操作
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        String loginId = String.valueOf(UserContext.getUser());
        // 更新时填充更新时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, now);
        this.strictUpdateFill(metaObject, "updateBy", String.class, loginId);
    }
}