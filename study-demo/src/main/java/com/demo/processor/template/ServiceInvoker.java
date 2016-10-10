package com.demo.processor.template;

import com.demo.processor.exception.BizException;
import com.demo.processor.request.BaseReq;
import com.demo.processor.response.BaseResp;

/**
 * Created by wu
 */
public interface ServiceInvoker<S extends BaseReq, T extends BaseResp> {

    /**
     * 服务前置处理
     */
    void preInvoke(Context<S, T> context) throws BizException;

    /**
     * 执行服务
     */
    void invoke(Context<S, T> context) throws BizException;

    /**
     * 服务后置处理
     */
    void postInvoke(Context<S, T> context) throws BizException;

}
