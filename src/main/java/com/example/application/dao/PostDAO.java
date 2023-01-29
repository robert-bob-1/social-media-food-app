package com.example.application.dao;

import com.example.application.businesslogic.UserBL;
import com.example.application.connection.ConnectionFactory;
import com.example.application.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    UserBL userBL = new UserBL();

    public PostDAO(){}

    private String createSelectByIDQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT title, description, details, ingredients, " +
                    "preparation, likes, user_ID" +
                  "FROM post" +
                  "WHERE ID = ?");
        return sb.toString();
    }

    public Post getPostByID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectByIDQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.next()){
                Post post = new Post(id,
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Integer.parseInt(resultSet.getString(6)),
                        userBL.getUser(resultSet.getString(7)));
                return post;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createSelectByUserIDQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ID, title, description, details, ingredients, " +
                "preparation, likes" +
                "FROM post" +
                "WHERE user_ID = ?");
        return sb.toString();
    }

    public Post getPostByUserID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectByUserIDQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.next()){
                Post post = new Post(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        Integer.parseInt(resultSet.getString(7)),
                        userBL.getUser(String.valueOf(id)));
                return post;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
