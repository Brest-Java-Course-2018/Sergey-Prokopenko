package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db-spring.xml", "classpath:test-dao.xml"})

public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {

        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
        Assert.assertTrue(department.getDescription().equals("Distribution Department"));
    }

    @Test
    public void addDepartment() {
        Department department = new Department();
        department.setDepartmentName("DepartmentNameTest");
        department.setDescription("DepartmentDescriptionTest");
        departmentDao.addDepartment(department);
        int i = departmentDao.getDepartments().size();
        Assert.assertNotNull(department);
        Assert.assertEquals("DepartmentNameTest", department.getDepartmentName());
        Assert.assertEquals("DepartmentDescriptionTest", department.getDescription());

        Assert.assertTrue(department.getDepartmentName().equals("DepartmentNameTest"));
        Assert.assertTrue(department.getDescription().equals("DepartmentDescriptionTest"));
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDepartmentName("DepartmentNameTest");
        department.setDescription("DepartmentDescriptionTest");
        departmentDao.addDepartment(department);

        department.setDepartmentName("Updated name");
        department.setDescription("Updated description");
        departmentDao.updateDepartment(department);

        Assert.assertEquals("Updated name", department.getDepartmentName());
        Assert.assertEquals("Updated description", department.getDescription());
    }

    @Test
    public void deleteDepartmentById() {

        Department department = new Department();
        department.setDepartmentId(55);
        department.setDepartmentName("Delete department");
        department.setDescription("Delete department descr");
        departmentDao.addDepartment(department);
        departmentDao.deleteDepartmentById(55);
        Assert.assertEquals(null, departmentDao.getDepartmentById(55));
    }
}