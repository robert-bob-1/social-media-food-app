package com.example.application.views;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class MakePostView extends VerticalLayout {
    UserBL userBL = new UserBL();
    PostBL postBL = new PostBL();

    TextField title = new TextField("Title");
    TextArea description = new TextArea("Description");
    TextField picture = new TextField("Image link");
    TextArea details = new TextArea("Details");
    TextArea ingredients = new TextArea("Ingredients");
    TextArea preparation = new TextArea("Preparation");

    Button makePostButton = new Button ("Make post");

    Notification notification = new Notification("");

    public MakePostView(User user){

        makePostButton.addClickListener( e-> {
            Post newPost = new Post(title.getValue(),
                    description.getValue(),
                    picture.getValue(),
                    details.getValue(),
                    ingredients.getValue(),
                    preparation.getValue(),
                    user);

            if (postBL.savePost(newPost)){
                notification.setText("Post created succesfully!");
                //clear all text fields
                clearText();
            } else{
                notification.setText("Post couldn't be created!");
            }
            notification.open();
            notification.setDuration(2000);
        });

        add(title, description, picture, details, ingredients, preparation, makePostButton);

        setWidth("600px");
        //setHeight("700px");

        setAlignItems(Alignment.STRETCH);
        setJustifyContentMode(JustifyContentMode.AROUND);
    }
    private void clearText(){
        title.clear();
        description.clear();
        picture.clear();
        details.clear();
        ingredients.clear();
        preparation.clear();
    }

}
