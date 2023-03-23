package com.example.application.views.components;

import com.example.application.model.User;
import com.example.application.views.pages.ProfileView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import org.springframework.core.env.Profiles;

public class ProfileDialog extends Dialog {
    private User visitedUser, visitingUser;

    private ProfileView profileView;

    public ProfileDialog (User visited, User visiting){
        visitedUser = visited;
        visitingUser = visiting;

        setHeaderTitle("Selected user's profile page");
        profileView = new ProfileView(visitedUser, visitingUser);
        add(profileView);

        Button closeDialog = new Button("Close", l -> this.close());
        FollowButton followButton = new FollowButton(visitedUser, visitingUser);

        getFooter().add(followButton, closeDialog);
    }
}
