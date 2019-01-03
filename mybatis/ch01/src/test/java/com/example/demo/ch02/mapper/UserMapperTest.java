package com.example.demo.ch02.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.ch02.model.Role;
import com.example.demo.ch02.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by frank on 2018-09-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureDataJpa
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
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        sqlSession.close();
    }

    @Test
    public void testSelectRolesByUserId() throws Exception {
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Role> roles = userMapper.selectRolesByUserId(1L);
        sqlSession.close();
    }

    @Test
    public void testInsert() throws Exception {
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1L);
        user.setUsername("test1");
        user.setPassword("13456");
        user.setEmail("a@b.com");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(LocalDateTime.now());

        int result = userMapper.insert(user);
        assertEquals(1, result);

        sqlSession.close();
    }

    @Test
    public void testInsert2() throws Exception {
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("test1");
        user.setPassword("13456");
        user.setEmail("a@b.com");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(LocalDateTime.now());

        int result = userMapper.insert2(user);
        assertEquals(1, result);

        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("test1");
        user.setPassword("13456");
        user.setEmail("a@b.com");
        user.setHeadImg(new byte[]{1, 2, 3});
        user.setCreateTime(LocalDateTime.now());

        int result = userMapper.insert2(user);
        assertEquals(1, result);

        result = userMapper.updateById(user);
        assertEquals(1, result);

        result = userMapper.deleteById(user.getId());
        assertEquals(1, result);


        sqlSession.close();
    }

    @Test
    public void testSelectRoleByUserIdAndRoleName(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.selectRoleByUserIdAndRoleName(1L,"test");

        sqlSession.close();
    }
}