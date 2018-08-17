package com.example.ch10_4.dao;

import com.example.ch10_4.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by frank on 2018-06-08.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
