package com.prevalentware.monitoringapp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);
    public LogUtils() {}
    public static void catchThrowable(Throwable e) {
        logger.error(e.getMessage(), e);
        while (e.getCause() != null) {
            e = e.getCause();
            logger.error(e.getMessage(), e);
        }
    }

    public static void error(String message, String detail) {
        logger.error(message, detail);
    }

}
