package com.example.application.views.components;

import com.example.application.businesslogic.PostBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.example.application.views.MakePostView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

public class EditPostDialog extends Dialog {
    private EditPostView editPostView;

    private PostBL postBL = new PostBL();

    private Post post;
    public EditPostDialog(User user, Post p){
        editPostView = new EditPostView(user, p);
        post = p;
        setHeaderTitle("Edit post");
        add(editPostView);

        Button closeDialog = new Button("Close", l -> this.close());
        Button removePostButton = new Button ("Delete",
                l -> {
            this.close();
            postBL.removePost(p);
        });
        getFooter().add(removePostButton, closeDialog);
    }
}
