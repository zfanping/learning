package com.example.ch8_3;

import com.example.ch8_3.dao.PersonRepository;
import com.example.ch8_3.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void contextLoads() {
        personRepository.save(new Person());
        List<Person> persons = personRepository.findAll();
        System.out.println(persons);
    }

}
