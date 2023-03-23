package com.example.application.views.components;

import com.example.application.model.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ExploreProfileView extends HorizontalLayout {
    User user;
    User currentUser;

    //components
    Image userImage;
    Button username = new Button("");
    FollowButton followButton;
    Button editPostButton = new Button();
    ProfileDialog profileDialog;

    public ExploreProfileView(User u, User visiting) {
        user = u;
        currentUser = visiting;

        setWidth("500px");

        userImage = new Image(user.getImage(), "user's profile picture");
        userImage.setMaxWidth("40px");
        userImage.getStyle().set("padding", "10px 10px");

        username.setText(user.getFirstName() + " " + user.getLastName());
        username.getStyle().set("padding", "0px 0px");
        username.getStyle().set("background-color", "white");
        username.addClickListener( e -> {
            profileDialog = new ProfileDialog(user, currentUser);
            profileDialog.open();
        });
        
        followButton = new FollowButton(user, currentUser);
        
        add(userImage, username, followButton);

        getStyle().set("background-color", "white");
        getStyle().set("border", "2px outset #CFCAC9"); //#2B3467
        getStyle().set("border-radius", "7px");
        setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    }
}
