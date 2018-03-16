package com.fh.framewrok.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     * 获取ApplicationContext
     * @return
     * @author wangdf
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     * @param beanName bean的名字
     * @return 返回一个bean对象
     * @author wangdf
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
