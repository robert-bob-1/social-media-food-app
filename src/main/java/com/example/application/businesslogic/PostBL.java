package com.example.application.businesslogic;

import com.example.application.dao.PostDAO;
import com.example.application.dao.UserDAO;
import com.example.application.model.Post;
import com.example.application.model.User;

import java.util.ArrayList;

public class PostBL {
    private PostDAO postDAO = new PostDAO();
    private UserDAO userDAO = new UserDAO();

    public PostBL(){}

    /**
     * Get the post with the given ID
     * @param id
     * @return
     */
    public Post getPostByID(int id){
        return postDAO.getPostByID(id);
    }

    /**
     * Get the post with the given ID
     * @param id
     * @return
     */
    public Post getPostByUserID(int id){
        return postDAO.getPostByUserID(id);
    }

    /**
     * Save a post to database
     */
    public boolean savePost(Post post){ return postDAO.savePost(post); }

    public boolean editPost(Post post) { return postDAO.editPost(post); }

    /**     * Fetch a user's followed users, fetch all posts from those users, order by date, return id's.
     * @param userID
     * @return
     */
    public ArrayList<Post> getFollowedPosts(int userID) {
        return postDAO.getFollowedPosts(userID);
    }

    public ArrayList<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }

    public void saveLikes(Post post, User currentUser, boolean alreadyLiked) {
        if (alreadyLiked){
            postDAO.setLikeStatus(currentUser.getUserID(), post.getID(), 0);
            postDAO.saveLikes(post.getID(), post.getLikes());
        }
        else {
            postDAO.setLikeStatus(currentUser.getUserID(), post.getID(), 1);
            postDAO.saveLikes(post.getID(), post.getLikes());
        }
    }

    public boolean getLikeStatus(User currentUser, Post post) {
        return postDAO.getLikeStatus(currentUser.getUserID(), post.getID());
    }


    public void removePost(Post p) {
        postDAO.removePost(p.getID());
    }
}
