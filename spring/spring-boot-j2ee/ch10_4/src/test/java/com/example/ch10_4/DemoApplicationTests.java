package com.example.ch10_4;

import com.example.ch10_4.dao.PersonRepository;
import com.example.ch10_4.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional // 2
public class DemoApplicationTests {
    @Autowired
    PersonRepository personRepository;

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    String expectJson;

    @Before // 3
    public void setUp() throws JsonProcessingException {
        Person p1 = new Person();
        Person p2 = new Person();
        personRepository.save(p1);
        personRepository.save(p2);

        expectJson = obj2Json(personRepository.findAll()); //  4
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String obj2Json(Object obj) throws JsonProcessingException { // 5
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Test
    public void contextLoads() throws Exception {
        String uri = "/person";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn(); // 6
        int stuatus =result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(200,stuatus);
        Assert.assertEquals(expectJson,content);
    }

}
