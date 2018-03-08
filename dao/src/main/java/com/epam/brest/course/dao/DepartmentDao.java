package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Get all departments.
     * @return list of departments.
     */
    List<Department> getDepartments();

    /**
     * Get department by ID.
     * @param departmentId -- ID of department.
     * @return object department.
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * Add department.
     * @param department object department.
     * @return added department.
     */
    Department addDepartment(Department department);

    /**
     * Edit department.
     * @param department object department.
     */
    void updateDepartment(Department department);

    /**
     * Delete department.
     * @param id -- ID of department.
     */
    void deleteDepartmentById(Integer id);

}
