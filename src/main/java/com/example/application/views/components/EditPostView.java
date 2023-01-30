package com.example.application.views.components;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class EditPostView extends VerticalLayout {
    UserBL userBL = new UserBL();
    PostBL postBL = new PostBL();
    Post post;
    TextField title = new TextField("Title");
    TextArea description = new TextArea("Description");
    TextField picture = new TextField("Image link");
    TextArea details = new TextArea("Details");
    TextArea ingredients = new TextArea("Ingredients");
    TextArea preparation = new TextArea("Preparation");

    Button editPostButton = new Button ("Edit post");
    Notification notification = new Notification("");

    public EditPostView(User user, Post p){
        this.post = p;

        title.setValue(post.getTitle());
        description.setValue(post.getDescription());
        picture.setValue(post.getPicture());
        details.setValue(post.getDetails());
        ingredients.setValue(post.getIngredients());
        preparation.setValue(post.getPreparation());

        editPostButton.addClickListener( e-> {
                    
            post.setTitle(title.getValue());
            post.setDescription(description.getValue());
            post.setPicture(picture.getValue());
            post.setDetails(details.getValue());
            post.setIngredients(ingredients.getValue());
            post.setPreparation(preparation.getValue());

            if (postBL.editPost(post)){
                notification.setText("Post edited succesfully!");
                //clear all text fields
                //clearText();
            } else{
                notification.setText("Post couldn't be edited!");
            }
            notification.open();
            notification.setDuration(2000);
        });


        add(title, description, picture, details, ingredients, preparation, editPostButton);
        setWidth("600px");
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
