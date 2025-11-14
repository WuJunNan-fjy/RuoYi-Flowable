package com.ruoyi.starters.mybatisplus.core.query;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class MPJLambdaWrapperX<T> extends MPJLambdaWrapper<T> {
    public MPJLambdaWrapperX<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (MPJLambdaWrapperX<T>) super.like(column, val);
        }
        return this;
    }

    public MPJLambdaWrapperX<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (values != null && !values.isEmpty()) {
            return (MPJLambdaWrapperX<T>) super.in(column, values);
        }
        return this;
    }

    public MPJLambdaWrapperX<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (MPJLambdaWrapperX<T>) super.eq(column, val);
        }
        return this;
    }

    
}
