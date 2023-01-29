package com.example.application.views;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PostView extends VerticalLayout {
    PostBL postBL = new PostBL();
    UserBL userBL = new UserBL();
    int postID;

    public PostView(int postID, String userID){
        this.postID = postID;

    }
}
