package com.example.application.views.components;

import com.example.application.businesslogic.UserBL;
import com.example.application.model.User;
import com.vaadin.flow.component.button.Button;

public class FollowButton extends Button {
    private User followedUser, follower;
    private boolean isFollowed;
    private final UserBL userBL = new UserBL();

    public FollowButton (User followedUser, User follower){
        getStyle().set("font-size", "12px");
        getStyle().set("padding", "20px");
        getStyle().set("border", "2px ");

        if (followedUser.getUserID() == follower.getUserID()){
            this.setVisible(false);
        } else {
            isFollowed = userBL.getFollowStatus(followedUser, follower);
            updateStyle();
        }
        addClickListener( e -> {
            isFollowed = userBL.getFollowStatus(followedUser, follower);
            //unfollow if is followed
            if (isFollowed){
                userBL.unfollow(followedUser, follower);

            } else {
                userBL.follow(followedUser, follower);
            }
            isFollowed = !isFollowed;
            updateStyle();
        });
    }
    public void updateStyle(){
        if(isFollowed){
            setText("Unfollow😁");
            getStyle().set("background-color", "#F8F5F5");
            getStyle().set("color", "blue");
        } else{
            setText("Follow");
            getStyle().set("background-color", "red");
            getStyle().set("color", "white");
        }
    }

}
