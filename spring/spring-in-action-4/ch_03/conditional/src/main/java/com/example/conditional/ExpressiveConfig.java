package com.example.conditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ExpressiveConfig {
    @Autowired
    Environment env;

    public BlankDisc disc() {
        return new BlankDisc(
                env.getProperty("disc.title"),
                env.getProperty("disc.artist")
        );
    }

    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
