package com.example.ch8_2.web;

import com.example.ch8_2.dao.PersonRepository;
import com.example.ch8_2.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by frank on 2018-06-06.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        return personRepository.save(new Person(name, age, address));
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        return personRepository.findByAddress(address);
    }

    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        return personRepository.findByNameAndAddress(name, address);
    }

    @RequestMapping("/q3")
    public Person q3(String name, String address) {
        return personRepository.withNameAndAddressQuery(name, address);
    }

    @RequestMapping("q4")
    public Person q4(String name, String address) {
        return personRepository.withNameAndAddressNamedQuery(name, address);
    }

    @RequestMapping("/sort")
    public List<Person> sort() {
        return personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
    }

    @RequestMapping("/page")
    public Page<Person> page() {
        return personRepository.findAll(PageRequest.of(1, 2));
    }

}
