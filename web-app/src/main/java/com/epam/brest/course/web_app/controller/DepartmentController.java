package com.epam.brest.course.web_app.controller;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public final String getDepartments(Model model) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsDTO();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping(value = "/department")
    public final String addDepartment(Model model) {
        Department department = new Department();
        String navbarBrandText = "Add department";
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("department", department);
        model.addAttribute("isNew", true);
        return "department";
    }

    @PostMapping(value = "/department")
    public String addDepartment(Department department, BindingResult result) {
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.addDepartment(department);
            return "redirect:/departments";
        }
    }

    @GetMapping(value = "/department/{id}")
    public final String getDepartmentById(@PathVariable Integer id, Model model){
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("isNew", false);
        return "department";
    }

    @PostMapping(value = "/department/{id}")
    public String updateDepartment(@PathVariable Integer id, Department department, BindingResult result) {
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }

    @GetMapping(value = "/department/{id}/delete")
    public String removeDepartment (@PathVariable Integer id) {
            this.departmentService.removeDepartment(id);
            return "redirect:/departments";
    }

}