package com.demo.arch.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Created by wu on 16/9/22.
 */
public class Log4j2EntryTest {
    private Logger logger = LogManager.getLogger(Log4j2EntryTest.class.getName());

    public static void main(String[] args) {
        Log4j2EntryTest service = new Log4j2EntryTest();
        service.retrieveMessage();
        service.retrieveMessage();
        service.exampleException();
    }

    private String[] messages = new String[] {
            "Hello, World",
            "Goodbye Cruel World",
            "You had me at hello"
    };
    private Random rand = new Random(1);

    public String retrieveMessage() {
        logger.entry();

        String testMsg = getMessage(getKey());

        return logger.exit(testMsg);
    }

    public void exampleException() {
        logger.entry();
        try {
            String msg = messages[messages.length];
            logger.error("An exception should have been thrown");
        } catch (Exception ex) {
            logger.catching(ex);
        }
        logger.exit();
    }

    public String getMessage(int key) {
        logger.entry(key);

        String value = messages[key];

        return logger.exit(value);
    }

    private int getKey() {
        logger.entry();
        int key = rand.nextInt(messages.length);
        return logger.exit(key);
    }
}