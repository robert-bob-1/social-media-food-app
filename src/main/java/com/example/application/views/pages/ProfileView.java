package com.example.application.views.pages;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.example.application.views.PostView;
import com.example.application.views.components.EditUserDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

public class ProfileView extends VerticalLayout {
    private User visitedUser, currentUser;
    private UserBL userBL = new UserBL();
    private PostBL postBL = new PostBL();

    //base view available for all visitors
    VerticalLayout standardView = new VerticalLayout();
    Label userName = new Label("");
    Label description  = new Label("");
    Image profilePicture;
    VerticalLayout postsLayout = new VerticalLayout();

    //extra editing for users viewing their own page
    HorizontalLayout personalView = new HorizontalLayout();
    Button editUserButton = new Button();
    EditUserDialog editUserDialog;

    public ProfileView (User visitedUser, User currentUser) {
        this.visitedUser = visitedUser;
        this.currentUser = currentUser;

        //add extra buttons for those visiting their own page
        if (visitedUser.getUserID() == currentUser.getUserID()){
            makePersonalView();
            add(personalView);
        }

        makeStandardView();
        add(standardView);



        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.AROUND);
    }

    private void makePersonalView() {
        editUserDialog = new EditUserDialog(visitedUser);

        editUserButton.setIcon(new Icon("lumo", "cog"));
        editUserButton.addClickListener( e -> {
           editUserDialog.open();
        });
        personalView.add(editUserButton);
        personalView.setWidth("800px");
        personalView.getStyle().set("background-color", "white");
        personalView.setAlignItems(Alignment.CENTER);
        personalView.setJustifyContentMode(JustifyContentMode.AROUND);
    }

    private void makeStandardView(){
            profilePicture = new Image(visitedUser.getImage(), "Profile picture");
        profilePicture.setMaxWidth("400px");

        userName.setText(visitedUser.getFirstName() + " " + visitedUser.getLastName());
        userName.getStyle().set("font-family", "Georgia");
        userName.getStyle().set("font-size", "30px");
        userName.getStyle().set("color", "#2B3467");

        description.setText(visitedUser.getDescription());
        description.getStyle().set("font-family", "Georgia");
        description.getStyle().set("font-size", "12px");
        description.getStyle().set("color", "#2B3467");

        ArrayList<Post> posts = postBL.getPostsByUserID(visitedUser.getUserID());
        for(Post post : posts){
            postsLayout.add(new PostView(post, currentUser));
        }

        postsLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        standardView.setWidth("800px");
        standardView.getStyle().set("background-color", "white");
        standardView.setAlignItems(Alignment.CENTER);
        standardView.setJustifyContentMode(JustifyContentMode.AROUND);

        standardView.add(profilePicture, userName, description, postsLayout);
    }
}
