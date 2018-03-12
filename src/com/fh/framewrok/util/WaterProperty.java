package com.fh.framewrok.util;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by admin on 2018/2/9.
 */
public class WaterProperty {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WeChatProperty.class);

    private final static String WECHAT_PROPERTIES_FILE="water.properties";

    private static Properties properties = null;

    private static WaterProperty instance=new WaterProperty();

    private WaterProperty() {
        if (properties == null) {
            properties = new Properties();
            init();
        }
    }
    private void init() {
        InputStream io = null;
        try {
            io = WeChatProperty.class.getClassLoader().getResourceAsStream(WECHAT_PROPERTIES_FILE);
            properties.load(new InputStreamReader(io,"UTF-8"));
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        } finally {
            if (io != null)
                try {
                    io.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getPropertyValue(String key) {
        if (properties != null) {
            return properties.getProperty(key);
        }
        return null;
    }
}
