package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.List;

/**
 * Interface EmployeeDao.
 */
public interface EmployeeDao {

    /**
     * Get employees by department ID.
     * @param departmentId - ID of department.
     * @return list of employees by one department
     */
    List<Employee> getEmployeesByDepartmentId(Integer departmentId);

    /**
     * Get all employees.
     * @return all employees.
     */
    List<Employee> getEmployees();

    /**
     * Get employee by ID.
     * @param employeeId -- ID of employee.
     * @return object employee.
     */
    Employee getEmployeeById(Integer employeeId);

    /**
     * Add employee.
     * @param employee -- object employee.
     * @return added employee.
     */
    Employee addEmployee(Employee employee);

    /**
     * Edit employee.
     * @param employee -- object employee.
     */
    void updateEmployee(Employee employee);

    /**
     * Delete employee
     * @param employeeId -- ID of employee.
     */
    void deleteEmployeeById(Integer employeeId);
}
