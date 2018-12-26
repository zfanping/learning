package com.example.demo.ch09;

import java.util.List;
import javax.annotation.Resource;

import com.example.demo.ch01.mapper.CountryMapper;
import com.example.demo.ch01.model.Country;
import com.example.demo.ch02.mapper.UserMapper;
import com.example.demo.ch02.model.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationSpringTest extends TestCase {
    @Resource
    private UserMapper userMapper;

    @Resource
    private CountryMapper countryMapper;

    @Test
    public void test() {
        List<Country> countries = countryMapper.selectAll();
        System.out.println("countries = " + countries);
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }
}
