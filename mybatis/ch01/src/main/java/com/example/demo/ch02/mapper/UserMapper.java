package com.example.demo.ch02.mapper;

import com.example.demo.ch02.User;

import java.util.List;

/**
 * Created by frank on 2018-09-29.
 */
public interface UserMapper {
    User selectById(Long id);

    List<User> selectAll();
}
