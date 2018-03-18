package com.epam.brest.course.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping(value = "/")
    public final String defaultPageRedirect() {
        return "redirect:departments";
    }


}