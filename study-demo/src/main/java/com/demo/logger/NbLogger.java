package com.demo.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * Created by wu on 16/8/30.
 */
public class NbLogger {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(NbLogger.class);
        ThreadContext.put("MDC", "*****");
        logger.info("hello world");
        logger.debug("{} is good", "jim");
        logger.warn("{} is good", "tom");
        logger.error("{} is good", "jim");
        logger.trace("{} is good", "tom");
        ThreadContext.clearAll();
    }
}

