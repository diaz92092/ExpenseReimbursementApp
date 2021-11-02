package com.revature.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class ConnectionUtil {

    private ConnectionUtil(){}

    public static Connection getConnection() throws SQLException {

        Driver mariaDBDriver = new Driver();
        DriverManager.registerDriver(mariaDBDriver);

            String username = "admin";
            String password = "Password";
            String connectionString = "jdbc:mariadb://database-1.cciv7qrx3v2i.us-west-1.rds.amazonaws.com:3306/demo3";

        Connection connection = DriverManager.getConnection(connectionString, username, password);

        return connection;
    }
}
