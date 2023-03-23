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

    public boolean follow(User followedUser, User follower) {
        return user.follow(followedUser.getUserID(), follower.getUserID());
    }

    public boolean editUser(User u) {
        return user.editUser(u);
    }


    public void removeUser(User u) {
        user.removeUser(u.getUserID());
    }

    public ArrayList<User> getUsersByName(String searchCriteria) {
        return user.getUsersByName(searchCriteria);
    }

    public boolean addAdmin(User u) {
        u.setAdmin(true);

        return user.addAdmin(u);
    }
}
