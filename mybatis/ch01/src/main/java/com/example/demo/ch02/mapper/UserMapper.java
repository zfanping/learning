package com.example.demo.ch02.mapper;

import com.example.demo.ch02.Role;
import com.example.demo.ch02.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by frank on 2018-09-29.
 */
public interface UserMapper {
    User selectById(Long id);

    List<User> selectAll();

    List<Role> selectRolesByUserId(Long userId);

    int insert(User user);

    int insert2(User user);

    int updateById(User user);

    int delteById(Long id);

    List<Role> selectRoleByUserIdAndRoleName(@Param("userId") Long userId, @Param("roleName") String roleName);

}
