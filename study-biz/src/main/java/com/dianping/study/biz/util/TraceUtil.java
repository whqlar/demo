package com.dianping.study.biz.util;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wu on 16/10/11.
 */
public class TraceUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(TraceUtil.class);

    public static final String DEBUG_INFO = "debugInfo";

    public static void push(boolean isDebug, String message, Object... args) {
        if (isDebug) {
            ThreadContext.push(message, args);
        }
    }

    public static String trace(boolean isDebug) {
        if (isDebug) {
            LOGGER.info("trace info >> {}", ThreadContext.cloneStack());
            return JSON.toJSONString(ThreadContext.cloneStack());
        }
        return null;
    }

    public static void clear() {
        ThreadContext.clearAll();
    }
}
