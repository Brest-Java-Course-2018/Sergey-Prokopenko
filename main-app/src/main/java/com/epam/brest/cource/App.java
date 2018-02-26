package com.epam.brest.cource;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello World!");
        DBUtils dbUtils = new DBUtils();
        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);
        dbUtils.addUser(connection, "admin", "admin", "User admin");
        dbUtils.addUser(connection, "admin1", "admin3", "User admin54");
        dbUtils.addUser(connection, "admin2", "admin4", "User admin23");
        dbUtils.addUser(connection, "user", "user", "User user");
        dbUtils.getUsers(connection);
        dbUtils.removeUser(connection, 2);
        dbUtils.getUsers(connection);
    }
}
