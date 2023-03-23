package com.example.application.views.components;

import com.example.application.businesslogic.UserBL;
import com.example.application.model.User;
import com.example.application.views.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class CreateAdminDialog extends Dialog {
    private User user;
    UserBL userBL = new UserBL();

    //Form
    VerticalLayout form = new VerticalLayout();
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField username = new TextField("User name");
    TextField password = new TextField("Password");

    Button addAdminButton = new Button("Add admin");
    Notification notification = new Notification("");

    public CreateAdminDialog(User u) {
        this.user = u;

        firstName.setValue(user.getFirstName());
        lastName.setValue(user.getLastName());
        username.setValue(user.getUsername());
        password.setValue(user.getPassword());

        addAdminButton.addClickListener(e -> {
            user.setFirstName(firstName.getValue());
            user.setLastName(lastName.getValue());
            user.setUsername(username.getValue());
            user.setPassword(password.getValue());

            if (userBL.addAdmin(user)) {
                notification.setText("Admin added succesfully!");
                clearAll();
            } else {
                notification.setText("Admin couldn't be created!");
            }
            notification.open();
            notification.setDuration(2000);



            user = userBL.getUser(String.valueOf(user.getUserID()));
        });


        form.add(firstName, lastName, username, password, addAdminButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Add admin");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);
    }

    public void clearAll(){
        firstName.clear();
        lastName.clear();
        username.clear();
        password.clear();
    }
}
