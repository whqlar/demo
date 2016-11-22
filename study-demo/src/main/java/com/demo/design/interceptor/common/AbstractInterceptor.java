package com.demo.design.interceptor.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wu on 16/11/21.
 */
public abstract class AbstractInterceptor<T extends BaseReq> implements Interceptor<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractInterceptor.class);

    @Override
    public void intercept(T request) throws InterceptException {
        try {
            doIntercept(request);
        } catch (InterceptException e) {
            LOGGER.error("intercept error", e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("intercept unknown error", e);
            throw InterceptException.newInstance();
        }
    }

    protected abstract void doIntercept(T request) throws InterceptException;
}
