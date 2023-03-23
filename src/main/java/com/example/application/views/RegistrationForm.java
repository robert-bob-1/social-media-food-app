package com.example.application.views;
import com.example.application.businesslogic.RegisterBL;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.util.stream.Stream;

public class RegistrationForm extends VerticalLayout {

    private H3 title;

    private TextField firstName;
    private TextField lastName;
    private TextField username;

    private PasswordField password;
    private PasswordField passwordConfirm;

    private TextArea description;
    private TextField image;

    private Span errorMessageField;

    private Button submitButton;
    Notification notificationBox = new Notification();
    Dialog dialogBox = new Dialog();

    public RegistrationForm() {
        getStyle().set("background-color", "#2B3467");
        title = new H3("Sign-up form");
        title.getStyle().set("padding", "5px 160px");
        firstName = new TextField("First name");
        firstName.getStyle().set("padding", "5px 130px");
        lastName = new TextField("Last name");
        lastName.getStyle().set("padding", "5px 130px");
        username = new TextField("User name to login with");
        username.getStyle().set("padding", "5px 130px");
        password = new PasswordField("Password");
        password.getStyle().set("padding", "5px 130px");
        passwordConfirm = new PasswordField("Confirm password");
        passwordConfirm.getStyle().set("padding", "5px 130px");
        description = new TextArea("Insert a description (optional)");
        description.setWidth("220px");
        description.setMaxHeight("200px");
        description.getStyle().set("padding", "5px 130px");
        image = new TextField("Insert link to profile picture (optional)");
        image.getStyle().set("padding", "5px 130px");
        setRequiredIndicatorVisible(firstName, lastName, username, password, passwordConfirm);

        errorMessageField = new Span();

        notificationBox.setPosition(Notification.Position.TOP_CENTER);
        notificationBox.setDuration(2000);

        dialogBox.setHeaderTitle("Registration successful!");

        submitButton = new Button("Join the community");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getStyle().set("padding", "20px 161px");
        submitButton.addClickListener(e -> {
            if (firstName.getValue().equals("") || lastName.getValue().equals("")
                    || username.getValue().equals("")
                    || password.getValue().equals("")
                    || passwordConfirm.getValue().equals("")) {
                notificationBox.setText("Please complete all fields!");
                notificationBox.open();

            } else if ( !password.getValue().equals(passwordConfirm.getValue())){
                notificationBox.setText("Passwords don't match!");
                notificationBox.open();
            }
            else {
                RegisterBL reg = new RegisterBL();
                if (reg.insertUser(firstName.getValue(), lastName.getValue(), username.getValue(), password.getValue(), description.getValue(), image.getValue())){
                    notificationBox.setText("Welcome, " + firstName.getValue() + " " + lastName.getValue());
                    notificationBox.open();
                }

            }
        });
        add(title, firstName, lastName, username, password, passwordConfirm, description, image, errorMessageField,
                submitButton);

        // Max width of the Form
        setMaxWidth("500px");
    }

    public PasswordField getPasswordField() {
        return password;
    }

    public PasswordField getPasswordConfirmField() {
        return passwordConfirm;
    }

    public Span getErrorMessageField() {
        return errorMessageField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
