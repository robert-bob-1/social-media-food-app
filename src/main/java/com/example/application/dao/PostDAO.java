package com.example.application.dao;

import com.example.application.businesslogic.UserBL;
import com.example.application.connection.ConnectionFactory;
import com.example.application.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    UserBL userBL = new UserBL();

    public PostDAO(){}

    private String createSelectByIDQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT title, description, picture, details, ingredients, " +
                    "preparation, likes, datetime, user_ID" +
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
                        resultSet.getString(6),
                        Integer.parseInt(resultSet.getString(7)),
                        resultSet.getString(8),
                        userBL.getUser(resultSet.getString(9)));
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
        sb.append("SELECT ID, title, description, picture, details, ingredients, " +
                "preparation, likes, datetime" +
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
                        resultSet.getString(7),
                        Integer.parseInt(resultSet.getString(8)),
                        resultSet.getString(9),
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

    private String createSavePostQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO post "+
                    "(title, description, picture, details, ingredients, preparation, likes, datetime, user_ID)" +
                  "VALUES" +
                  "(?,?,?,?,?,?,?,?,?)");
        return sb.toString();
    }

    public boolean savePost(Post post) {
        Connection con = null;
        PreparedStatement insertStatement = null;
        String query = createSavePostQuery();
        ResultSet resultSet = null;

        try {
            con = ConnectionFactory.getConnection();
            insertStatement= con.prepareStatement(query);
            insertStatement.setString(1, post.getTitle());
            insertStatement.setString(2, post.getDescription());
            insertStatement.setString(3, post.getPicture());
            insertStatement.setString(4, post.getDetails());
            insertStatement.setString(5, post.getIngredients());
            insertStatement.setString(6, post.getPreparation());
            insertStatement.setInt(7, post.getLikes());
            insertStatement.setString(8, post.getDatetimeString());
            insertStatement.setInt(9, post.getUser().getUserID());


            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String createSelectFollowedPostsQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *" +
                "FROM post" +
                "WHERE user_ID in (SELECT followed_id" +
                                   "FROM follow" +
                                    "WHERE follower_id = (?))" +
                "ORDER BY datetime DESC");
        return sb.toString();
    }
    public ArrayList<Post> getFollowedPosts(int userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectByUserIDQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
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
                        resultSet.getString(7),
                        Integer.parseInt(resultSet.getString(8)),
                        resultSet.getString(9),
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
