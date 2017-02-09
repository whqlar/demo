package com.demo.design.interceptor.common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wu on 16/11/21.
 */
public class InterceptorChain<T extends BaseReq> implements Interceptor<T> {

    private List<Interceptor> interceptors = Lists.newArrayList();

    @Override
    public void intercept(T request) throws InterceptException {
        for(Interceptor interceptor: this.interceptors) {
            interceptor.intercept(request);
        }
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
