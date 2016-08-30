package com.dianping.study.biz.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wu on 16/8/30.
 */
public class NbLogger {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(NbLogger.class);
        logger.info("hello world");
        logger.debug("{} is good", 1);
        logger.warn("{} is good", 2);
        logger.error("{} is good", 1);
        logger.trace("{} is good", 1);
    }
}
