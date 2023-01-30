package com.example.application.views.components;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class EditPostView extends VerticalLayout {
    UserBL userBL = new UserBL();
    PostBL postBL = new PostBL();

    TextField title = new TextField("Title");
    TextArea description = new TextArea("Description");
    TextField picture = new TextField("Image link");
    TextArea details = new TextArea("Details");
    TextArea ingredients = new TextArea("Ingredients");
    TextArea preparation = new TextArea("Preparation");

    Button makePostButton = new Button ("Edit post");

    Notification notification = new Notification("");

    public EditPostView(User user,)
}
