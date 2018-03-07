package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeesByDepartmentId(Integer departmentId);

    List<Employee> getEmployees();

    Employee getEmployeeById(Integer employeeId);

    Employee addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer employeeId);


}
