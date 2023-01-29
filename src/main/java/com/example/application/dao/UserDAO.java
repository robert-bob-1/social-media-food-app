package com.example.application.dao;

import com.example.application.connection.ConnectionFactory;
import com.example.application.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private String createUpdateNameQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("eventizer.user");
        sb.append(" SET ");
        sb.append(" name = ?");
        sb.append(" WHERE userID =?");
        return sb.toString();
    }

    private String createUpdatePasswordQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("eventizer.login");
        sb.append(" SET ");
        sb.append(" password = ?");
        sb.append(" WHERE userID =?");
        return sb.toString();
    }

    private String createSelectEmailQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("email ");
        sb.append("FROM eventizer.user ");
        sb.append("WHERE userID = ?;");
        return sb.toString();
    }

    private String createSelectIDQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT userID " +
                  "FROM user " +
                  "WHERE username = ?;");
        return sb.toString();
    }

    private String createSelectNameQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT user.username " +
                  "FROM social_media.user " +
                  "WHERE ID = ?;");
        return sb.toString();
    }

    private String createSelectRoleQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("role ");
        sb.append("FROM eventizer.user ");
        sb.append("WHERE userID = ?;");
        return sb.toString();
    }

    private String createGetUserQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT user.username, user.email, user.phone, user.password, user.role ");
        sb.append("FROM eventizer.user WHERE ");
        sb.append("userID = ?;");
        return sb.toString();
    }

    private String createModifyUserQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE eventizer.user ");
        sb.append("SET username = ?, email = ?, phone = ?, password = ? ");
        sb.append("WHERE userID = ?;");
        return sb.toString();
    }

//    public boolean modifyUser(User u) {
//        Connection con = null;
//        PreparedStatement updateStatement = null;
//        String query = createModifyUserQuery();
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionFactory.getConnection();
//            updateStatement = con.prepareStatement(query);
//            updateStatement.setString(1, u.getUsername());
//            updateStatement.setString(2, u.getEmail());
//            updateStatement.setString(3, u.getPhone());
//            updateStatement.setString(4, u.getPassword());
//            updateStatement.setInt(5, u.getUserID());
//
//            updateStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public User getUser(String userID) {
        Connection con = null;
        PreparedStatement selectStatement = null;
        String query = createGetUserQuery();
        ResultSet rs = null;

        try {
            con = ConnectionFactory.getConnection();
            selectStatement = con.prepareStatement(query);
            selectStatement.setString(1, userID);
            rs = selectStatement.executeQuery();

            if (rs.next()) {
                User u = new User(Integer.parseInt(userID), rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));

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

    public boolean updatePassword(int id, String password) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String query = createUpdatePasswordQuery();

        try {
            updateStatement = connection.prepareStatement(query);
            updateStatement.setString(1, password);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }

        return false;
    }

    public boolean updateName(int id, String name) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String query = createUpdateNameQuery();

        try {
            updateStatement = connection.prepareStatement(query);
            updateStatement.setString(1, name);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }

        return false;
    }

    public String getEmailAddress(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectEmailQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
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
        return null;
    }

    public String getRole(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectRoleQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
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

        return null;
    }

    public int getID(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectIDQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if(resultSet.next())
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
