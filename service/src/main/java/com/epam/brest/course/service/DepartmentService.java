package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentService {

    Department getDepartmentById(Integer departmentId);

    void updateDepartmentDescription(Integer departmentId, String description);

}
