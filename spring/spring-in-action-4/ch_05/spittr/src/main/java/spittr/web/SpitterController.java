package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.domain.Spitter;

import javax.validation.Valid;

/**
 * Created by wshcatkin on 2018-06-23.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository repository;

    @Autowired
    public SpitterController(SpitterRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/reigster", method = RequestMethod.GET)
    public String showRegisterForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/reigster", method = RequestMethod.POST)
    public String processRegisteration(@Valid Spitter spitter) {
        repository.save(spitter);

        return "redirect:/spitter" + spitter.getUsername();
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        Spitter spitter = repository.findUserByName(username);
        model.addAttribute(spitter);

        return "profile";
    }
}
