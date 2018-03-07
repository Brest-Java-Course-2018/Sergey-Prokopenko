package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    String select = "SELECT employeeName, salary, departmentId FROM employee";

    String selectEmployees = "SELECT employeeName, salary, departmentId FROM employee WHERE departmentId = ?";

    String insert = "INSERT INTO employee (employeeName, salary, departmentId) VALUES (:employeeName, :salary, :departmentId)";

    String selectById = "SELECT * FROM employee WHERE employeeId = :employeeId";

    String update = "UPDATE employee SET employeeName = :employeeName, salary = :salary, departmentId = :departmentId WHERE employeeId = :employeeId";

    String delete = "DELETE FROM employee WHERE employeeId = ?";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId)
    {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);

        List<Employee> employees =
                        namedParameterJdbcTemplate.
                        getJdbcOperations().
                        query(selectEmployees,
                        new Object[]{departmentId},
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> employees = namedParameterJdbcTemplate.
                getJdbcOperations().
                query(select, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("employeeId", employeeId);
        Employee employee =
                namedParameterJdbcTemplate.
                        queryForObject(selectById,
                        namedParameter,
                        BeanPropertyRowMapper.
                        newInstance(Employee.class));

        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("employeeName", employee.getEmployeeName());
        namedParameters.addValue("salary", employee.getSalary());
        namedParameters.addValue("departmentId", employee.getDepartmentId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insert, namedParameters, generatedKeyHolder);

        employee.setDepartmentId(generatedKeyHolder.getKey().intValue());

        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(update, namedParameters);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, employeeId);
    }

}
