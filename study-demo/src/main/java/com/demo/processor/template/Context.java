package com.demo.processor.template;

import com.demo.processor.request.BaseReq;
import com.demo.processor.response.BaseResp;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求上下文
 *
 * Created by wu on 16/9/22.
 */
public class Context<S extends BaseReq, T extends BaseResp> {
    /**
     * 请求
     */
    private S request;

    /**
     * 响应
     */
    private T response;



    private final Map<String, Object> extensions = new HashMap<String, Object>();

    /**
     * Getter method for property <tt>request</tt>.
     *
     * @return property value of request
     */
    public S getRequest() {
        return request;
    }

    /**
     * Setter method for property <tt>request</tt>.
     *
     * @param request value to be assigned to property request
     */
    public void setRequest(S request) {
        this.request = request;
    }

    /**
     * Getter method for property <tt>response</tt>.
     *
     * @return property value of response
     */
    public T getResponse() {
        return response;
    }

    /**
     * Setter method for property <tt>response</tt>.
     *
     * @param response value to be assigned to property response
     */
    public void setResponse(T response) {
        this.response = response;
    }

    /**
     * 获取所有扩展属性
     *
     * @return
     */
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    /**
     * 获取属性
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return getExtensions().get(key);
    }

    /**
     * 存入属性
     *
     * @param key
     * @param value
     * @return
     */
    public Object put(String key, Object value) {
        return getExtensions().put(key, value);
    }

}