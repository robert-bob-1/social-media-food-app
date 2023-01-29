package com.example.application.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/social_media";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory dbInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dbInstance.createConnection();
    }

    public static void close(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("An SQL Exception occurred. Details are provided below:");
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
            }
        }
    }

    public static void close(ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
            }
        }
    }

    private Connection createConnection() throws SQLException {

        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException sqlEx) {
            System.err.println("An SQL Exception occurred. Details are provided below:");
            sqlEx.printStackTrace(System.err);
        }

        return connection;
    }
}
