package com.demo.processor.template;

import com.demo.processor.enums.ResultEnum;
import com.demo.processor.exception.BizException;
import com.demo.processor.request.BaseReq;
import com.demo.processor.response.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class AbstractBaseTemplate<S extends BaseReq, T extends BaseResp> implements BizProcessor<S, T>,
        ServiceInvoker<S, T> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseTemplate.class);

    @Override
    public T execute(S req) {
        LOGGER.info("[execute request] >> {}", req);
        Context<S, T> context = initContext(req);
        try {

            //前置处理
            preInvoke(context);

            //服务调用
            invoke(context);

            //后置处理
            postInvoke(context);

        } catch (BizException e) {
            LOGGER.error("[execute error]", e);
            context.getResponse().setResultEnum(e.getResultEnum());
        } catch (Exception e) {
            LOGGER.error("[execute error]", e);
            context.getResponse().setResultEnum(ResultEnum.INNER_ERROR);
        }

        LOGGER.info("[execute response] >> {}", context.getResponse());
        return context.getResponse();
    }

    private Context<S, T> initContext(S req) {
        T resp = initResponse();

        Context<S, T> context = new Context<S, T>();
        context.setRequest(req);
        context.setResponse(resp);
        return context;
    }

    private T initResponse() {
        ParameterizedType type = getParameterizedType(getClass());
        Class<T> clz = (Class<T>) type.getActualTypeArguments()[1];
        try {
            return clz.newInstance();
        } catch (Exception e) {
            LOGGER.error("[initResponse error]", e);
            return null;
        }
    }

    private ParameterizedType getParameterizedType(Class<?> clz) {
        Type type = clz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }
        return getParameterizedType(clz.getSuperclass());
    }

}
