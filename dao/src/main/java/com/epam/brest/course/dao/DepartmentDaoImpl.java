package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    public static final String DEPARTMENT_ID = "departmentId";

    public static final String DEPARTMENT_NAME = "departmentName";

    public static final String DESCRIPTION = "description";

    @Value("${department.select}")
    private String departmentSelect;

    @Value("${department.selectById}")
    private String selectById;

    @Value("${department.insert}")
    private String insert;

    @Value("${department.update}")
    private String update;

    @Value("${department.delete}")
    private String delete;

    @Value("${department.checkDepartment}")
    private String checkDepartment;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = namedParameterJdbcTemplate.getJdbcOperations().query(departmentSelect, new DepartmentRowMapper());
        return departments;
    }

    /*@Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(
                        selectById,
                        namedParameters,
                        new DepartmentRowMapper());
        return department;
    }*/

    @Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(selectById, namedParameters, BeanPropertyRowMapper.newInstance(Department.class));

        return department;
    }

    @Override
    public Department addDepartment(Department department) {

        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_NAME,department.getDepartmentName());

        Integer result =
                namedParameterJdbcTemplate.queryForObject(checkDepartment, namedParameters, Integer.class);

        if(result == 0)
        {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(DEPARTMENT_NAME, department.getDepartmentName());
            namedParameters.addValue(DESCRIPTION, department.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insert, namedParameters, generatedKeyHolder);

            department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        }
        else {
            throw new IllegalArgumentException("Department with the same name already exists in Database!");
        }

        return department;
    }

    @Override
    public void updateDepartment(Department department) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameters);
    }

    @Override
    public void deleteDepartmentById(Integer departmentId)
    {
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, departmentId);
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {

            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));

            return department;
        }
    }
}
