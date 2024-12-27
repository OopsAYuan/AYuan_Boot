package com.ayuan.framework.mybatis.core.handler;

import com.ayuan.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 默认参数填充实现类
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDo = (BaseDO) metaObject.getOriginalObject();

            LocalDateTime current = LocalDateTime.now();
            if (Objects.isNull(baseDo.getCreateTime())) {
                baseDo.setCreateTime(current);
            }
            if (Objects.isNull(baseDo.getUpdateTime())) {
                baseDo.setUpdateTime(current);
            }

            // TODO 获取当前登录用户
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

        // TODO 获取当前登录用户
    }
}
