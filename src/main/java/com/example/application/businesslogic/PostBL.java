package com.example.application.businesslogic;

import com.example.application.dao.PostDAO;
import com.example.application.dao.UserDAO;
import com.example.application.model.Post;

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
    public boolean savePost(Post post){ return postDAO.savePost(post);}

    /**
     * Fetch a user's followed users, fetch all posts from those users, order by date, return id's.
     * @param userID
     * @return
     */
    public ArrayList<Post> getFollowedPosts(int userID) {
        return postDAO.getFollowedPosts(userID);
    }

    public ArrayList<Integer> getAllPosts() {

    }
}
