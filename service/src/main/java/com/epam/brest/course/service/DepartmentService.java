package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;

/**
 * Department DAO Interface.
 */
public interface DepartmentService {
    /**
     * Get Department By Id.
     * @param departmentId -- ID of department.
     * @return department object.
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Edit department.
     * @param departmentId -- ID of department.
     * @param description -- description.
     */
    void updateDepartmentDescription(Integer departmentId, String description);
}
