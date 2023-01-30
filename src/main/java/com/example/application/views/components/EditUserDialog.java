package com.example.application.views.components;

import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class EditUserDialog extends Dialog {
    private final User user;
    UserBL userBL = new UserBL();

    //Form
    VerticalLayout form = new VerticalLayout();
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField picture = new TextField("Image link");
    TextField username = new TextField("User name");
    TextField password = new TextField("Password");
    TextArea description = new TextArea("Description");

    Button editUserButton = new Button("Edit user");
    Notification notification = new Notification("");

    public EditUserDialog(User u) {
        this.user = u;

        firstName.setValue(user.getFirstName());
        lastName.setValue(user.getLastName());
        picture.setValue(user.getImage());
        username.setValue(user.getUsername());
        password.setValue(user.getPassword());
        description.setValue(user.getDescription());

        editUserButton.addClickListener(e -> {

            user.setFirstName(firstName.getValue());
            user.setDescription(description.getValue());
            user.setLastName(lastName.getValue());
            user.setUsername(username.getValue());
            user.setPassword(password.getValue());
            user.setImage(picture.getValue());

            if (userBL.editUser(user)) {
                notification.setText("User edited succesfully!");
            } else {
                notification.setText("User couldn't be edited!");
            }
            notification.open();
            notification.setDuration(2000);
        });


        form.add(firstName, lastName, username, password, picture, description, editUserButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Edit user");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());
        Button removeUserButton = new Button ("Delete account",
                l -> {
                    this.close();
                    userBL.removeUser(u);
                });
        getFooter().add(removeUserButton, closeDialog);
    }
}
