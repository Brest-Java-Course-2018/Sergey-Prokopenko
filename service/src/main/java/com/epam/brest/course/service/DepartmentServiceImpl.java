package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dto.DepartmentDTO;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{

    private static final Logger LOGGER =  LogManager.getLogger();

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("updateDepartmentDescription({}, {})", departmentId, description);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDao.updateDepartment(department);
    }

    @Override
    public List<DepartmentDTO> getDepartmentsDTO() {
        List<DepartmentDTO> list = departmentDao.getDepartmentsDTO();
        return list;
    }

    @Override
    public Department addDepartment(Department department) {

        return departmentDao.addDepartment(department);
    }

    @Override
    public void removeDepartment(Integer id) {
        departmentDao.deleteDepartmentById(id);
    }
}
