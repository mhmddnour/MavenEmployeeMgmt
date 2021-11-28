package com.monour.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;

    private String URL = "jdbc:mysql://localhost:3306/mdp";
    private String USER = "root";
    private String PASSWORD = "P@ssw0rd12";

    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null)
        {
            instance = new DBConnection();
            // to trace connection :
            System.out.println(" Connection >> New DB Connection created");
        }

        try {
            //System.out.println("before call driver manager");
            return DriverManager.getConnection(instance.URL, instance.USER, instance.PASSWORD);
        }
        catch (SQLException e){
            throw e;
        }

    }


    public static void closeConn(Connection connection)
    {
        try {
            if (connection != null)
            {
                connection.close();
                connection=null;
                System.out.println("*** DB Connection Closed ***");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
