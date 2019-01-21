package com.example.demo.lazy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

public class TestBean implements InitializingBean, BeanNameAware {
    private static final Logger logger = LoggerFactory.getLogger(TestBean.class);
    private String beanName;
    private TestBean other;
    
    static {
        logger.info("static");
    }

    public TestBean() {
        logger.info("construct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("{} afterPropertiesSet", beanName);
    }

    public void setOther(TestBean other) {
        this.other = other;
    }

    public TestBean getOther() {
        return other;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
