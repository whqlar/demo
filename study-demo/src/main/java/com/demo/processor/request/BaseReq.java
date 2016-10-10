package com.demo.processor.request;

import com.demo.processor.enums.BizChannelEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数基类
 *
 * Created by wu
 */
public class BaseReq implements Serializable {

    /**
     * 请求的业务渠道
     */
    protected BizChannelEnum channel;

    /**
     * 请求额外信息扩展字段
     */
    private Map<String, String> extras = new HashMap<String, String>();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public BizChannelEnum getChannel() {
        return channel;
    }

    public void setChannel(BizChannelEnum channel) {
        this.channel = channel;
    }
}