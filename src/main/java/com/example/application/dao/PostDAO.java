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


    public ArrayList<Post> getPostsByUserID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query =
                "SELECT ID, title, description, picture, details, ingredients, " +
                "preparation, likes, datetime " +
                "FROM post " +
                "WHERE user_ID = ?";

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            ArrayList<Post> posts = new ArrayList<>();
            while (resultSet.next()){
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
                posts.add(post);
            }
            return posts;

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

    public boolean editPost(Post post) {
        Connection con = null;
        PreparedStatement insertStatement = null;
        String query =
                "UPDATE post " +
                "SET title = ?, description = ?, picture = ?, details = ?," +
                "ingredients = ?, preparation = ? " +
                "WHERE ID = ? ";
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
            insertStatement.setString(7, String.valueOf(post.getID()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String createSelectFollowedPostsQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * " +
                "FROM post " +
                "WHERE user_ID in (SELECT followed_id " +
                                   "FROM follow " +
                                    "WHERE follower_id = (?)) " +
                "ORDER BY datetime ASC");
        return sb.toString();
    }
    public ArrayList<Post> getFollowedPosts(int userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectFollowedPostsQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();

            ArrayList<Post> postList = new ArrayList<>();
            while (resultSet.next()){
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
                        userBL.getUser(resultSet.getString(10)));
                postList.add(post);
            }
            return postList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public ArrayList<Post> getAllPosts() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = new String (
                "SELECT * " +
                "FROM post " +
                "ORDER BY datetime DESC");

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            ArrayList<Post> postList = new ArrayList<>();
            while (resultSet.next()){
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
                        userBL.getUser(resultSet.getString(10)));
                postList.add(post);
            }
            return postList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public void saveLikes(int id, int likes) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = new String (
                "UPDATE post " +
                "SET likes = ? " +
                "WHERE ID = ?");

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, likes);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public boolean getLikeStatus(int userID, int postID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = new String (
                "SELECT * " +
                "FROM liked_posts " +
                "WHERE user_id = ? AND post_id = ?");

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, postID);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    public void setLikeStatus(int userID, int postID, int setLike) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query;
        if (setLike == 0){
            query = "DELETE FROM liked_posts " +
                    "WHERE post_ID = ? AND user_ID = ?";
        } else {
            query = "INSERT INTO liked_posts (post_ID, user_ID) " +
                    "VALUES (?, ?) ";
        }

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, postID);
            statement.setInt(2, userID);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


    public void removePost(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String queryPre1 = "DELETE FROM liked_posts " +
                            "WHERE post_id = ?";
        String query = "DELETE FROM post " +
                       "WHERE ID = ?";


        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(queryPre1);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public ArrayList<Post> getMostPopularPosts() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query =
                "SELECT * " +
                "FROM post " +
                "WHERE datetime > now() - interval 24 hour " +
                "ORDER BY likes DESC";

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            ArrayList<Post> postList = new ArrayList<>();
            while (resultSet.next()){
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
                        userBL.getUser(resultSet.getString(10)));
                postList.add(post);
            }
            return postList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public ArrayList<Post> getPostsByTitle(String searchCriteria) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT * " +
                "FROM post " +
                "WHERE (title LIKE ?) ";

        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchCriteria + "%");
            resultSet = statement.executeQuery();
            ArrayList<Post> postList = new ArrayList<>();
            while (resultSet.next()){
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
                        userBL.getUser(resultSet.getString(10)));
                postList.add(post);
            }
            return postList;

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
