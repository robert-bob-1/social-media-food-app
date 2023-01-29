package com.example.application.dao;

import com.example.application.connection.ConnectionFactory;
import com.example.application.model.User;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private String createSelectNameQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT user.username " +
                  "FROM social_media.user " +
                  "WHERE ID = ?;");
        return sb.toString();
    }

    private String createGetUserQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT firstName, lastName, username, password, description " +
                "FROM user " +
                "WHERE ID = ?;");
        return sb.toString();
    }

    public User getUser(String userID) {
        Connection con = null;
        PreparedStatement selectStatement = null;
        String query = createGetUserQuery();
        ResultSet resultSet = null;

        try {
            con = ConnectionFactory.getConnection();
            selectStatement = con.prepareStatement(query);
            selectStatement.setString(1, userID);
            resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                User u = new User(Integer.parseInt(userID), resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));

                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(con);
        }
        return null;
    }


    public String getUsername(String userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectNameQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return "";
    }

    private String createFollowedUsersQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT followed_ID " +
                "FROM follow " +
                "WHERE follower_ID = ?;");
        return sb.toString();
    }
    public ArrayList<Integer> getFollowedUsers(int userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFollowedUsersQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(userID));
            resultSet = statement.executeQuery();

            ArrayList<Integer> followedUsers = new ArrayList<>();
            while (resultSet.next()){
                followedUsers.add(resultSet.getInt(1));
            }
            return followedUsers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public boolean getFollowStatus(int followedID, int followerID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = new String(
                 "SELECT followed_ID " +
                        "FROM follow " +
                        "WHERE follower_ID = ? AND followed_ID = ?;");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(followerID));
            statement.setString(2, String.valueOf(followedID));
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

//    public boolean participate(User u, Event e) {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        String query = createParticipateQuery();
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(query);
//            stmt.setInt(1, u.getUserID());
//            stmt.setInt(2, e.getEventID());
//            stmt.setString(3, "");
//            stmt.executeUpdate();
//            return true;
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionFactory.close(stmt);
//            ConnectionFactory.close(con);
//        }
//
//        return false;
//    }
//
//    private String createParticipateQuery() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO eventizer.eventparticipation (userID, eventID, feedback) VALUES ");
//        sb.append("(?, ?, ?);");
//        return sb.toString();
//    }
//
//
//    private String createFeedbackQuery() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("UPDATE eventizer.eventparticipation ");
//        sb.append("SET feedback = ? ");
//        sb.append("WHERE userID = ? and eventID = ?;");
//        return sb.toString();
//    }
//
//    public boolean feedback(User u, Event e, String feed) {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        String query = createFeedbackQuery();
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, feed);
//            stmt.setInt(2, u.getUserID());
//            stmt.setInt(3, e.getEventID());
//            stmt.executeUpdate();
//            return true;
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionFactory.close(stmt);
//            ConnectionFactory.close(con);
//        }
//
//        return false;
//    }
//
//    private String createGetFeedbackQuery() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT feedback FROM eventizer.eventparticipation ");
//        sb.append("WHERE eventID = ? and userID = ?;");
//        return sb.toString();
//    }
//
//    public String getFeedback(User u, Event e) {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        String query = createGetFeedbackQuery();
//
//        try {
//            con = ConnectionFactory.getConnection();
//            stmt = con.prepareStatement(query);
//            stmt.setInt(1, e.getEventID());
//            stmt.setInt(2, u.getUserID());
//            rs = stmt.executeQuery();
//
//            if (rs.next())
//                return rs.getString(1);
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionFactory.close(stmt);
//            ConnectionFactory.close(rs);
//            ConnectionFactory.close(con);
//        }
//
//
//        return "";
//    }
//
//    private String createGetEventsQuery() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT eventID from eventizer.eventparticipation ");
//        sb.append("WHERE userID = ?;");
//        return sb.toString();
//    }
//
//    public ArrayList<Integer> getEvents(String userID) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        String query = createGetEventsQuery();
//        ArrayList<Integer> ev = new ArrayList<>();
//
//        try {
//            connection = (Connection) ConnectionFactory.getConnection();
//            statement = connection.prepareStatement(query);
//            statement.setString(1, userID);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                ev.add(resultSet.getInt(1));
//            }
//            return ev;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionFactory.close(resultSet);
//            ConnectionFactory.close(statement);
//            ConnectionFactory.close(connection);
//        }
//        return null;
//    }
}
