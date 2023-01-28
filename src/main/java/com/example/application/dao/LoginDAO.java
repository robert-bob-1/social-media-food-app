package com.example.application.dao;

import com.example.application.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    private String createSelectQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                    "user.username, user.password " +
                  "FROM social_media.users " +
                  "WHERE users.username = ? AND user.password = ?");
        return sb.toString();
    }

    private String createSelectIDQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                    "userID " +
                  "FROM social_media.users " +
                  "WHERE email = ?;");
        return sb.toString();
    }

    public boolean isUser(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next())
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

    public int findID(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return -1;
    }
}
