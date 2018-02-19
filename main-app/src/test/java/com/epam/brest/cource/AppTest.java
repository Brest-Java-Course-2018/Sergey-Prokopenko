package com.epam.brest.cource;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.SQLException;

public class AppTest extends TestCase
{

    public AppTest(String testName)
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite(AppTest.class);
    }


    public void testApp() throws SQLException, ClassNotFoundException
    {

    }

}
