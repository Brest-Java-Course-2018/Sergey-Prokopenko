package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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

/**
 * Implementation of DepartmentDao.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    /**
     * Logger for DepartmentDaoImpl class.
     */
    private static final Logger LOGGER =
            org.apache.logging.log4j.LogManager.getLogger();

    /**
     * Constant variable.
     */
    public static final String DEPARTMENT_ID = "departmentId";

    /**
     * Constant variable.
     */
    public static final String DEPARTMENT_NAME = "departmentName";

    /**
     * Constant variable.
     */
    public static final String DESCRIPTION = "description";

    /**
     * SQL query select all departments.
     */
    @Value("${department.select}")
    private String departmentSelect;

    /**
     * SQL query select department by ID.
     */
    @Value("${department.selectById}")
    private String selectById;

    /**
     * SQL query add department.
     */
    @Value("${department.insert}")
    private String insert;

    /**
     * SQL query edit department.
     */
    @Value("${department.update}")
    private String update;

    /**
     * SQL query delete department.
     */
    @Value("${department.delete}")
    private String delete;

    /**
     * SQL query check department.
     */
    @Value("${department.checkDepartment}")
    private String checkDepartment;

    /**
     * Variable namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Setter for NamedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate -- variable.
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Constructor.
     * @param namedParameterJdbcTemplate
     */
    public DepartmentDaoImpl
            (final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final List<Department> getDepartments() {
        LOGGER.debug("getDepartments()");
        List<Department> departments =
                        namedParameterJdbcTemplate.
                        getJdbcOperations().
                        query(departmentSelect, new DepartmentRowMapper());
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
    public final Department getDepartmentById(final Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(selectById,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Department.class));

        return department;
    }

    @Override
    public final Department addDepartment(final Department department) {

        LOGGER.debug("addDepartment({})", department);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_NAME, department.getDepartmentName());

        Integer result =
                namedParameterJdbcTemplate.queryForObject(checkDepartment,
                                namedParameters, Integer.class);

        LOGGER.debug("result({})", result);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(DEPARTMENT_NAME, department.getDepartmentName());
            namedParameters.addValue(DESCRIPTION, department.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insert,
                    namedParameters, generatedKeyHolder);

            department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException
                    ("Department with the same name already exists in Database!");
        }
        return department;
    }

    @Override
    public final void updateDepartment(final Department department) {
        LOGGER.debug("updateDepartment({})", department);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameters);
    }

    @Override
    public final void deleteDepartmentById(final Integer departmentId) {
        LOGGER.debug("deleteDepartmentById({})", departmentId);
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
