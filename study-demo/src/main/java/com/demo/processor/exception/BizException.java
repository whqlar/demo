package com.demo.processor.exception;

import com.demo.processor.enums.ResultEnum;

/**
 * 运行时异常类
 *
 * Created by wu
 */
public class BizException extends RuntimeException {

    private ResultEnum resultEnum;

    public BizException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.resultEnum = resultEnum;
    }

    public BizException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.resultEnum = resultEnum;
    }

    public static BizException newInstance(ResultEnum resultEnum) {
        return new BizException(resultEnum);
    }

    public static BizException newInstance(ResultEnum resultEnum, Throwable throwable) {
        return new BizException(resultEnum, throwable);
    }

    @Override
    public String toString() {
        return "BizException{code=" + resultEnum.getCode() + ", msg=" + resultEnum.getMsg() + "}";
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}