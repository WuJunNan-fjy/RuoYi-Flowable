package com.ruoyi.starters.mybatisplus.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

public class RuoYiMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity base = (BaseEntity) metaObject.getOriginalObject();
            Date now = new Date();
            if (base.getCreateTime() == null) {
                base.setCreateTime(now);
            }
            if (base.getUpdateTime() == null) {
                base.setUpdateTime(now);
            }
            try {
                String username = SecurityUtils.getUsername();
                if (username != null && base.getCreateBy() == null) {
                    base.setCreateBy(username);
                }
                if (username != null && base.getUpdateBy() == null) {
                    base.setUpdateBy(username);
                }
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        Object updater = getFieldValByName("updateBy", metaObject);
        try {
            String username = SecurityUtils.getUsername();
            if (username != null && updater == null) {
                setFieldValByName("updateBy", username, metaObject);
            }
        } catch (Exception ignored) {
        }
    }
}

