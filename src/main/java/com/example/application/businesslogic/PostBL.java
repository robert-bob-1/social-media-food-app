package com.example.application.businesslogic;

import com.example.application.dao.PostDAO;
import com.example.application.model.Post;

public class PostBL {
    private PostDAO postDAO = new PostDAO();

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

}
