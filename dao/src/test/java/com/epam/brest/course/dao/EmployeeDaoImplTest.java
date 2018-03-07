package com.epam.brest.course.dao;


import com.epam.brest.course.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db-spring.xml",
        "classpath:test-dao.xml",
        "classpath:dao.xml"})

@Rollback
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertTrue(employees.isEmpty());
    }

    @Test
    public void getEmployeesByDepartmentId() {

        Employee employee = new Employee("Test1", 500, 1);
        employeeDao.addEmployee(employee);
        Employee employee1 = new Employee("Test2", 600, 1);
        employeeDao.addEmployee(employee1);

        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertFalse(employees.isEmpty());
        Assert.assertTrue(employees.size() == 2);
    }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        int before = employees.size();

        Employee employee = new Employee(3,"Petr", 900, 1);
        Employee newEmployee = employeeDao.addEmployee(employee);
        Assert.assertNotNull(newEmployee.getEmployeeId());

        Assert.assertTrue(newEmployee.getEmployeeName().equals(employee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));

        Assert.assertTrue((before + 1) == employeeDao.getEmployees().size());
    }

    @Test
    public void getEmployeeById() {

        Employee employee = new Employee(1,"Ivan", 1500, 1);
        employee = employeeDao.addEmployee(employee);

        List<Employee> employees = employeeDao.getEmployees();
        int size = employees.size();

        Employee newEmployee = employeeDao.getEmployeeById(employee.getEmployeeId());
        Assert.assertNotNull(employeeDao);
        Assert.assertTrue(newEmployee.getEmployeeId().equals(employee.getEmployeeId()));
        Assert.assertTrue(newEmployee.getEmployeeName().equals(employee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
    }

    @Test
    public void updateEmployee() {

        Employee newEmployee = employeeDao.addEmployee(new Employee(6,"Test Name", 500, 1));

        newEmployee.setEmployeeName("Vasya");
        newEmployee.setSalary(600);

        employeeDao.updateEmployee(newEmployee);
        Employee updatedEmployee = employeeDao.getEmployeeById(newEmployee.getEmployeeId());

        Assert.assertTrue(updatedEmployee.getEmployeeName().equals(newEmployee.getEmployeeName()));
        Assert.assertTrue(updatedEmployee.getSalary().equals(newEmployee.getSalary()));
        Assert.assertTrue(updatedEmployee.getDepartmentId().equals(newEmployee.getDepartmentId()));
    }

    @Test
    public void deleteEmployeeById (){
        Employee newEmployee = employeeDao.addEmployee(new Employee(7,"Test Name", 500, 1));
        List<Employee> employees = employeeDao.getEmployees();
        int size = employees.size();

        employeeDao.deleteEmployeeById(newEmployee.getEmployeeId());
        Assert.assertTrue((size - 1) == employeeDao.getEmployees().size());
    }

}
