package com.example.application.views;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import java.util.stream.Stream;

public class RegistrationForm extends VerticalLayout {

    private H3 title;

    private TextField firstName;
    private TextField lastName;
    private TextField username;

    private PasswordField password;
    private PasswordField passwordConfirm;

    private Span errorMessageField;

    private Button submitButton;


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
        setRequiredIndicatorVisible(firstName, lastName, username, password, passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button("Join the community");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getStyle().set("padding", "20px 161px");

        add(title, firstName, lastName, username, password,
                passwordConfirm, errorMessageField,
                submitButton);

        // Max width of the Form
        setMaxWidth("500px");

        // Allow the form layout to be responsive.
        // On device widths 0-490px we have one column.
        // Otherwise, we have two columns.
//        setResponsiveSteps(
//                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
//                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));
//
//        // These components always take full width
//        setColspan(title, 2);
//        setColspan(firstName, 1);
//        setColspan(lastName, 1);
//        setColspan(password, 1);
//        setColspan(passwordConfirm, 1);
//        setColspan(username, 1);
//        setColspan(errorMessageField, 1);
//        setColspan(submitButton, 1);

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
