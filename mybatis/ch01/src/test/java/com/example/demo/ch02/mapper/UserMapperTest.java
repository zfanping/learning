package com.example.demo.ch02.mapper;

import com.example.demo.ch02.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.*;

/**
 * Created by frank on 2018-09-29.
 */
public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() throws Exception {
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(1L);
        sqlSession.close();
    }

    @Test
    public void testSelectAll() throws Exception {

    }
}