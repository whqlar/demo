package com.demo.processor.response;

import com.demo.processor.enums.ResultEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 响应参数基类
 *
 * Created by
 */
public class BaseResp implements Serializable {

    /**
     * 业务处理结果
     */
    private ResultEnum resultEnum;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}