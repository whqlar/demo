package com.demo.design.interceptor;

import com.demo.design.interceptor.common.AbstractInterceptor;
import com.demo.design.interceptor.common.BaseReq;
import com.demo.design.interceptor.common.InterceptException;
import org.springframework.stereotype.Service;

/**
 * Created by wu on 16/11/21.
 */
@Service
public class AppKeyInterceptor extends AbstractInterceptor<BaseReq> {

    @Override
    protected void doIntercept(BaseReq request) throws InterceptException {

    }
}
