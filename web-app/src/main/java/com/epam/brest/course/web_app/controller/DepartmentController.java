package com.epam.brest.course.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @GetMapping(value = "/departments")
    public final String departments() {
        return "departments";
    }

    @GetMapping(value = "/department")
    public final String department() {
        return "department";
    }

}