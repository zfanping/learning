package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by wshcatkin on 2018-10-03.
 */
@RunWith(SpringRunner.class)
@MybatisTest
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void findByStateTest() {
        City city = cityMapper.findByState("CA");
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }
}