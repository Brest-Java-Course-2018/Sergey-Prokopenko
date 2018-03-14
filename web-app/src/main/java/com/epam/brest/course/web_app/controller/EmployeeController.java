package com.epam.brest.course.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping(value = "/employees")
    public final String departments() {
        return "employees";
    }

    @GetMapping(value = "/employee")
    public final String department() {
        return "employee";
    }

}