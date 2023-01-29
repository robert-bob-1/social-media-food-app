package com.example.application.views;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class PostView extends VerticalLayout {
    PostBL postBL = new PostBL();
    UserBL userBL = new UserBL();
    int postID;
    Post post;

    //view components
    //username with maybe a follow button
    HorizontalLayout header = new HorizontalLayout();
    Label username = new Label("");
    Button followButton = new Button("Follow");

    //post components
    VerticalLayout content = new VerticalLayout();
    Label title = new Label("");
    Label description  = new Label("");
    Image image;
    Label details = new Label("");
    Label ingredients = new Label("");
    Label preparation  = new Label("");
    Button likes = new Button("Likes: 0");


    public PostView(Post post){
        this.post = post;

        makeHeader(post);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        this.setWidth("600px");

    }

    private void makeHeader(Post post) {
        header.setWidth("500px");

        username.setText(this.post.getUser().getUsername());

        header.getStyle().set("background-color", "white");
        header.getStyle().set("border", "2px black");


    }
}
