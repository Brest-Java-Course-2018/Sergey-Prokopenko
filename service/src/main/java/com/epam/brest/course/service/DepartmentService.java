package com.epam.brest.course.service;

import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Department DAO Interface.
 */
@Service
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

    void updateDepartment(Department department);

    List<Department> getDepartments();

    List<DepartmentDTO> getDepartmentsDTO();

    Department addDepartment(Department department);

    void removeDepartment(Integer id);
}
