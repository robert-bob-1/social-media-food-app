package com.example.application.businesslogic;

import com.example.application.dao.UserDAO;
import com.example.application.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBL {
    private UserDAO user;
    private static UserBL userLogic = new UserBL();

    public UserBL(){
        user = new UserDAO();
    }

    public User getUser(String userID) {
        return user.getUser(userID);
    }


    public String getUsername(String id) {
        return user.getUsername(id);
    }

    public ArrayList<Integer> getFollowedUsers(int userID) {
        return user.getFollowedUsers(userID);
    }

    public boolean getFollowStatus(User followedUser, User follower) {
        return user.getFollowStatus(followedUser.getUserID(), follower.getUserID());
    }

    public boolean unfollow(User followedUser, User follower) {
        return user.unfollow(followedUser.getUserID(), follower.getUserID());
    }

//    public boolean updatePassword(int id,String password) throws SQLException {
//        return user.updatePassword(id,password);
//    }
//
//    public boolean updateName(int id,String name) throws SQLException {
//        return user.updateName(id,name);
//    }
//
    //
//    public String getRole(int id) {
//        return user.getRole(id);
//    }
//
//    public int getID(String email){
//        return user.getID(email);
//    }
//
//    public static userBL getuserLogic() {
//        return userLogic;
//    }
//

//
//    public void modifyUser(User u) {
//        user.modifyUser(u);
//    }
//
//    public void participate(User u, Event e) {
//        user.participate(u, e);
//    }
//
//    public void feedback(User u, Event e, String feed) {
//        user.feedback(u, e, feed);
//    }
//
//    public String getFeedback(User u, Event e) {
//        return user.getFeedback(u, e);
//    }
//
//    public ArrayList<Integer> getEvents(String userID) {
//        return user.getEvents(userID);
//    }
}
