package com.example.demo.ch02;

import com.example.demo.ch02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wshcatkin on 2018-10-02.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
