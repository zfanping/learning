package com.example.ch10_4.web;

import com.example.ch10_4.dao.PersonRepository;
import com.example.ch10_4.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by frank on 2018-06-08.
 */
@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/person")
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
