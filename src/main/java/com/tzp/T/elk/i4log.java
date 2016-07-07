package com.tzp.T.elk;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/6/24.
 */
public class i4log {
    private static final Logger LOGGER = Logger.getLogger(i4log.class);
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            LOGGER.error("Info log [" + i + "].");
            Thread.sleep(5000);
        }
    }
}
