package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String GET_DEPARTMENTS_SQL = "SELECT departmentId, departmentName, description FROM department";

    private final String GET_DEPARTMENT_BY_ID_SQL = "SELECT departmentId, departmentName, description FROM department WHERE departmentId = :departmentId";

    private final String ADD_A_DEPARTMENT = "INSERT INTO department (departmentName, description) VALUES (:departmentName, :description)";

    private final String UPDATE_A_DEPARTMENT = "UPDATE department SET departmentName = :departmentName, description = :description WHERE departmentId = :departmentId";

    private final String DELETE_A_DEPARTMENT = "DELETE FROM department WHERE departmentId = :departmentId";

    public DepartmentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = jdbcTemplate.query(GET_DEPARTMENTS_SQL, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(
                        GET_DEPARTMENT_BY_ID_SQL,
                        namedParameters,
                        new DepartmentRowMapper());
        return department;
    }

    @Override
    public Department addDepartment(Department department) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource
                        ("departmentName", department.getDepartmentName())
                        .addValue("description", department.getDescription());
        namedParameterJdbcTemplate.update(ADD_A_DEPARTMENT, namedParameters);
        return department;
    }

    @Override
    public void updateDepartment(Department department) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(
                        "departmentId", department.getDepartmentId())
                        .addValue("departmentName", department.getDepartmentName())
                        .addValue("description", department.getDescription());
        namedParameterJdbcTemplate.update(UPDATE_A_DEPARTMENT, namedParameters);
    }

    @Override
    public void deleteDepartmentById(Integer id)
    {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", id);
        namedParameterJdbcTemplate.update(DELETE_A_DEPARTMENT, namedParameters);
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {

            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDescription(resultSet.getString(3));

            return department;
        }
    }
}
