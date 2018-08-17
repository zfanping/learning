package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Controller
@RequestMapping({"/","/homepage"})

public class HomeController {
    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }
}
