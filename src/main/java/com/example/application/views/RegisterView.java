package com.example.application.views;

import com.example.application.businesslogic.LoginBL;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;

@Route("Register")
@PageTitle("Register")
public class RegisterView extends VerticalLayout {
    private final LoginForm login = new LoginForm();
    private final LoginBL loginBL = new LoginBL();
    public RegisterView(){
        RegistrationForm registrationForm = new RegistrationForm();
        add(registrationForm);
        setHeightFull();
        setWidthFull();
        getStyle().set("background-color", "#FCFFE7");
        registrationForm.getElement().getThemeList().add("dark");

        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
