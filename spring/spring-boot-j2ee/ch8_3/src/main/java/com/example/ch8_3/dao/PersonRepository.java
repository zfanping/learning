package com.example.ch8_3.dao;

import com.example.ch8_3.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by frank on 2018-06-06.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByNameStartsWith(@Param("name") String name);
}
