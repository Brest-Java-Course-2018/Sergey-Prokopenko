package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 * Implementation of EmployeeDao.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    /**
     * SQL query select all employees.
     */
    @Value("${employee.select}")
    private String select;

    /**
     * SQL query select employees by department.
     */
    @Value("${employee.selectEmployees}")
    private String selectEmployees;

    /**
     * SQL query add employee.
     */
    @Value("${employee.insert}")
    private String insert;

    /**
     * SQL query select employee by ID.
     */
    @Value("${employee.selectById}")
    private String selectById;

    /**
     * SQL query edit employee by ID.
     */
    @Value("${employee.update}")
    private String update;

    /**
     * SQL query delete employee by ID.
     */
    @Value("${employee.delete}")
    private String delete;

    /**
     * setNamedParameterJdbcTemplate variable.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final List<Employee>
    getEmployeesByDepartmentId(final Integer departmentId) {
        List<Employee> employees =
                        namedParameterJdbcTemplate.
                        getJdbcOperations().
                        query(selectEmployees,
                        new Object[]{departmentId},
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public final List<Employee> getEmployees() {

        List<Employee> employees = namedParameterJdbcTemplate.
                                getJdbcOperations().
                                query(select, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public final Employee getEmployeeById(final Integer employeeId) {

        SqlParameterSource namedParameters =
                new MapSqlParameterSource("employeeId", employeeId);
        Employee employee =
                namedParameterJdbcTemplate.queryForObject(selectById,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    @Override
    public final Employee addEmployee(final Employee employee) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("employeeName", employee.getEmployeeName());
        namedParameters.addValue("salary", employee.getSalary());
        namedParameters.addValue("departmentId", employee.getDepartmentId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insert, namedParameters, generatedKeyHolder);

        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());

        return employee;
    }

    @Override
    public final void updateEmployee(final Employee employee) {
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(update, namedParameters);
    }

    @Override
    public final void deleteEmployeeById(final Integer employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, employeeId);
    }

}
