package com.demo.processor;

import com.demo.processor.enums.BizChannelEnum;
import com.demo.processor.enums.ResultEnum;
import com.demo.processor.exception.BizException;
import com.demo.processor.request.BaseReq;
import com.demo.processor.response.BaseResp;
import com.demo.processor.template.AbstractBaseTemplate;
import com.demo.processor.template.Context;

/**
 * Created by wu on 16/10/10.
 */
public class IndexProcessor extends AbstractBaseTemplate<BaseReq, BaseResp> {
    @Override
    public void preInvoke(Context<BaseReq, BaseResp> context) throws BizException {

    }

    @Override
    public void invoke(Context<BaseReq, BaseResp> context) throws BizException {
        context.getResponse().setResultEnum(ResultEnum.SUCCESS);
        throw BizException.newInstance(ResultEnum.INNER_ERROR);
    }

    @Override
    public void postInvoke(Context<BaseReq, BaseResp> context) throws BizException {

    }

    public static void main(String[] args) {
        IndexProcessor indexProcessor = new IndexProcessor();
        BaseReq req = new BaseReq();

        req.setChannel(BizChannelEnum.APP_INDEX);
        indexProcessor.execute(req);
    }
}
