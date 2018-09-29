package com.example.demo.ch01;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by frank on 2018-09-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryMapperTests {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
    }

    @Test
    public void testAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Country> countryList = sqlSession.selectList("selectAll");
        countryList.forEach(System.out::println);
        sqlSession.close();
    }

}
