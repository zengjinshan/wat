package com.fh.framewrok.util;

import org.slf4j.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by admin on 2017/11/1.
 */
public class WeChatProperty {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WeChatProperty.class);

    private final static String WECHAT_PROPERTIES_FILE="wx.properties";

    private static Properties properties = null;

    private static WeChatProperty instance=new WeChatProperty();

    private WeChatProperty() {
        if (properties == null) {
            properties = new Properties();
            init();
        }
    }
    private void init() {
        InputStream io = null;
        try {
            io = WeChatProperty.class.getClassLoader().getResourceAsStream("wx"+ File.separator+WECHAT_PROPERTIES_FILE);
            properties.load(io);
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
