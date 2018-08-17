package com.example.demo;


import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wshcatkin on 2018-07-19.
 */
public class UserCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVICE/user/{1}", String.class, id);
    }

    @Override
    protected String getFallback() {
        return "error";
    }
}
