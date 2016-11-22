package com.demo.design.processor.template;

import com.demo.design.processor.exception.BizException;
import com.demo.design.processor.request.BaseReq;
import com.demo.design.processor.response.BaseResp;

/**
 * 业务处理器接口
 *
 * Created by wu
 */
public interface BizProcessor<S extends BaseReq, T extends BaseResp> {

    /**
     * 执行业务
     *
     * @param S 请求参数
     * @return T 结果信息
     * @throws Exception
     */
    T execute(final S baseReq) throws BizException;

}