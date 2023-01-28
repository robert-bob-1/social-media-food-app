package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Login")
@PageTitle("Login")
public class LoginView extends VerticalLayout {
    private final LoginForm login = new LoginForm();

    public LoginView(){
        getStyle().set("background-color", "#FCFFE7");

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        VerticalLayout header = new VerticalLayout();
        header.add(new H1("Foodbook"));
        header.setAlignItems(Alignment.CENTER);

        add(header, login);
    }
}
