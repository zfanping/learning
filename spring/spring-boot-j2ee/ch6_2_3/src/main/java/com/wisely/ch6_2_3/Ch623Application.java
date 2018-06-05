package com.wisely.ch6_2_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch623Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch623Application.class, args);
    }

    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;

    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/")
    String index() {
        return "book name is: " + bookName + " and book author is: " + bookAuthor +
                "<br>author name is: " + authorSettings.getName() + " and author age is: " + authorSettings.getAge();
    }
}
