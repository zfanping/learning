package com.example.demo.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy(false)
public class TestInjectWithAutowired {
//    @Autowired
    private TestBean2 bean;
}
