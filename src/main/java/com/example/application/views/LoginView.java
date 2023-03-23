package com.example.application.views;

import com.example.application.businesslogic.LoginBL;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;

@Route(value = "Login")
@PageTitle("Login")
public class LoginView extends VerticalLayout {
    private final LoginForm login = new LoginForm();
    private final Button registerButton = new Button("Register a new account");

    private final LoginBL loginbl = LoginBL.getBusinessLogic();

    public LoginView(){
        getStyle().set("background-color", "#FCFFE7");

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        VerticalLayout header = new VerticalLayout();
        H1 title = new H1("Foodbook");
        title.getStyle().set("color", "#2B3467");
        header.add(title);
        header.setAlignItems(Alignment.CENTER);

        login.addLoginListener(e -> {
            int role = loginbl.validate(e.getUsername(), e.getPassword());
            if (role >= 0) {
                loginbl.send();
                int id = loginbl.getID(e.getUsername(), e.getPassword());
                UI.getCurrent().navigate(HomeView.class, new RouteParameters("userID", String.valueOf(id)));
            } else {
                login.setError(true);
            }
        });

        registerButton.addClickListener(e -> {
            UI.getCurrent().navigate(RegisterView.class);
        });

        registerButton.setWidth("19%");
        registerButton.getStyle().set("background-color","blue");
        registerButton.getStyle().set("color","white");

        add(header, login, registerButton);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
