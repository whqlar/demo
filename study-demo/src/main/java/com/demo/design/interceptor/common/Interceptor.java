package com.demo.design.interceptor.common;

/**
 * 拦截器
 * Created by wu on 16/11/21.
 */
public interface Interceptor<T extends BaseReq> {

    /**
     * 拦截
     * @param request
     * @throws InterceptException
     */
    void intercept(T request) throws InterceptException;

}
