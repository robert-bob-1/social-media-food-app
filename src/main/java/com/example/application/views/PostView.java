package com.example.application.views;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.example.application.views.components.FollowButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class PostView extends VerticalLayout {
    PostBL postBL = new PostBL();
    UserBL userBL = new UserBL();
    int postID;
    Post post;
    User currentUser;

    //view components
    //username with maybe a follow button
    HorizontalLayout header = new HorizontalLayout();
    Label username = new Label("");
    FollowButton followButton;

    //post components
    VerticalLayout content = new VerticalLayout();
    Label title = new Label("");
    Label description  = new Label("");
    Image image;
    Label details = new Label("");
    Label ingredients = new Label("");
    Label preparation  = new Label("");
    Button likes = new Button("Likes: 0");


    public PostView(Post post, User currentUser){
        this.post = post;
        this.currentUser = currentUser;


        makeHeader();
        add(header);


        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        this.setWidth("600px");
    }

    private void makeHeader() {
        header.setWidth("500px");

        username.setText(this.post.getUser().getUsername());
        username.getStyle().set("padding", "0px 20px");

        followButton = new FollowButton(this.post.getUser(), this.currentUser);

        header.add(username, followButton);
        header.getStyle().set("background-color", "white");
        header.getStyle().set("border", "3px black");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);


    }
}
