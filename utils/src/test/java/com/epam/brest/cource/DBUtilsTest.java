package com.epam.brest.cource;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException
    {
        DBUtils dbUtils = new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }

    @org.junit.Test
    public void testAddUser() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Connection connection = dbUtils.getConnection();

        dbUtils.createUserTable(connection);

        Assert.assertEquals(dbUtils.addUser(connection, "testAdmin", "testPassword","testTest"),1);

        Assert.assertEquals(dbUtils.addUser(connection, "", "",""),0);

    }


}