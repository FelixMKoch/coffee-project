package de.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/index")
    public String indexGet(Model model) {
        return "index";
    }

}
