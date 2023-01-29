package com.example.application.dao;

import com.example.application.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDAO {
    private String createInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO " +
                    "user (firstName, lastName, username, password) " +
                  "VALUES (?, ?, ?, ?);");
        return sb.toString();
    }

    public boolean insertUser(String firstName, String lastName, String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertStatement();
        try {

            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            System.out.println(statement.toString());
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return false;
    }
}
