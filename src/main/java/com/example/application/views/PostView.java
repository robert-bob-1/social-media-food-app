package com.example.application.views;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.example.application.views.components.EditPostDialog;
import com.example.application.views.components.FollowButton;
import com.example.application.views.components.ProfileDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class PostView extends VerticalLayout {
    PostBL postBL = new PostBL();
    UserBL userBL = new UserBL();

    Post post;
    User currentUser;

    //header components
    HorizontalLayout header = new HorizontalLayout();
    Image userImage;
    Button username = new Button("");
    FollowButton followButton;
    Button editPostButton = new Button();
    ProfileDialog profileDialog;

    //post components
    VerticalLayout content = new VerticalLayout();
    Label title = new Label("");
    Label description  = new Label("");
    Image image;
    Label details = new Label("");
    Label ingredients = new Label("");
    Label preparation  = new Label("");
    Button likes = new Button("Likes: 0");

    //edit components
    EditPostDialog editPostDialog;
    public PostView(Post post, User currentUser){
        this.post = post;
        this.currentUser = currentUser;

        makeHeader();
        add(header);

        makeContent();
        add(content);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        this.setWidth("600px");
    }


    private void makeHeader() {
        header.setWidth("500px");

        userImage = new Image(post.getUser().getImage(), "user's profile picture");
        userImage.setMaxWidth("40px");
        userImage.getStyle().set("padding", "10px 10px");

        username.setText(this.post.getUser().getFirstName() + " " + this.post.getUser().getLastName());
        username.getStyle().set("padding", "0px 0px");
        username.getStyle().set("background-color", "white");

        followButton = new FollowButton(this.post.getUser(), this.currentUser);
        header.add(userImage, username, followButton);

        if (currentUser.getUserID() == post.getUser().getUserID() || currentUser.isAdmin()){
            editPostButton.setIcon(new Icon("lumo", "edit"));
            header.add(editPostButton);
            editPostButton.getStyle().set("margin-left", "auto");
            editPostButton.getStyle().set("padding", "10px 10px");
            editPostButton.addClickListener( e -> {
                editPostDialog = new EditPostDialog(post);
                editPostDialog.open();
            });
        }

        username.addClickListener( e -> {
            profileDialog = new ProfileDialog(post.getUser(), currentUser);
            profileDialog.open();
        });

        header.getStyle().set("background-color", "white");
        header.getStyle().set("border", "2px outset #CFCAC9"); //#2B3467
        header.getStyle().set("border-radius", "7px");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

    private void makeContent() {
        title.setText(post.getTitle());

        description.setText(post.getDescription());

        image = new Image(post.getPicture(), "post image");
        image.setMaxWidth("400px");
        details.setText(post.getDetails());

        ingredients.setText(post.getIngredients());

        preparation.setText(post.getPreparation());

        likes.setText("👍 " + post.getLikes());
        boolean liked = postBL.getLikeStatus(currentUser, post);
        if (!liked){
            likes.getStyle().set("background-color", "#2B3467");
            likes.getStyle().set("color", "white");
        } else {
            likes.getStyle().set("background-color", "#F8F5F5");
            likes.getStyle().set("color", "#2B3467");
        }

        likes.addClickListener( e -> {
            boolean alreadyLiked = postBL.getLikeStatus(currentUser, post);

            if (alreadyLiked){
                post.setLikes(post.getLikes() - 1);
                postBL.saveLikes(post, currentUser, alreadyLiked);

                likes.getStyle().set("background-color", "#2B3467");
                likes.getStyle().set("color", "white");
            } else {
                post.setLikes(post.getLikes() + 1);
                postBL.saveLikes(post, currentUser, alreadyLiked);

                likes.getStyle().set("background-color", "#F8F5F5");
                likes.getStyle().set("color", "#2B3467");
            }

            likes.setText("👍 " + post.getLikes());
        });

        content.add(title, description, image, details, ingredients, preparation, likes);
        content.getStyle().set("padding", "30px, 0px");
        content.getStyle().set("background-color", "white");
        content.getStyle().set("border", "2px outset #CFCAC9"); //#2B3467
        content.getStyle().set("border-radius", "7px");
        //content.setDefaultHorizontalComponentAlignment();
    }
}
