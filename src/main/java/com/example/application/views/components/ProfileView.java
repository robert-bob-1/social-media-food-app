package com.example.application.views.components;

import com.example.application.businesslogic.UserBL;
import com.example.application.model.User;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ProfileView extends VerticalLayout {
    private User visitedUser, currentUser;
    private UserBL userBL = new UserBL();
    //list with user firstname, lastName
    VerticalLayout standardView = new VerticalLayout();
    Label userName = new Label("");
    Label description  = new Label("");
    Image profilePicture;



    public ProfileView (User visitedUser, User currentUser) {
        this.visitedUser = visitedUser;
        this.currentUser = currentUser;

        makeStandardView();

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.AROUND);
    }

    private void makeStandardView(){

        userName.setText(visitedUser.getFirstName() + " " + visitedUser.getLastName());
        userName.getStyle().set("font-family", "Georgia");
        userName.getStyle().set("font-size", "30px");
        userName.getStyle().set("color", "#2B3467");

        description.setText(visitedUser.getDescription());
        description.getStyle().set("font-family", "Georgia");
        description.getStyle().set("font-size", "12px");
        description.getStyle().set("color", "#2B3467");

        

        standardView.setWidth("800px");
        standardView.getStyle().set("background-color", "white");
        standardView.setAlignItems(Alignment.CENTER);
        standardView.setJustifyContentMode(JustifyContentMode.AROUND);
    }
}
