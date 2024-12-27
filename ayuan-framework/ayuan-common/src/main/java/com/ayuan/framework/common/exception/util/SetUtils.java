package com.ayuan.framework.common.exception.util;

import cn.hutool.core.collection.CollUtil;

import java.util.Set;

public class SetUtils {

    @SafeVarargs
    public static <T> Set<T> asSet(T... objs) {
        return CollUtil.newHashSet(objs);
    }

}
