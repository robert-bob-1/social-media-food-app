package com.example.application.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Foodbook")
@Route(value = "")
public class MainView extends VerticalLayout {

    Button btnLogin = new Button("Login");
    Button btnRegister = new Button("Register");

    Label title = new Label ("Foodbook");
    Label introduction = new Label("Connecting all the chefs around the world!");
    public MainView() {
        this.setAlignItems(Alignment.CENTER);
//        addClassName("main-view");
        getStyle().set("background-color", "#FCFFE7");
        Image img = new Image("https://images.unsplash.com/photo-1491438590914-bc09fcaaf77a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8aGFwcHklMjBwZW9wbGV8ZW58MHx8MHx8&w=1000&q=80", "social-event");
        img.getStyle().set("height", "500px");

        btnLogin.addClickListener(e -> {
            UI.getCurrent().navigate(LoginView.class);
        });

        btnRegister.addClickListener(e -> {
            UI.getCurrent().navigate(RegisterView.class);
        });

        setWidth("100%");
        setHeight("100%");

        btnRegister.getStyle().set("background-color", "#EF6C33");
        btnLogin.getStyle().set("background-color", "#EF6C33");
        btnRegister.getStyle().set("color", "#FFFFFF");
        btnLogin.getStyle().set("color", "#FFFFFF");
        btnRegister.getStyle().set("font-size", "30px");
        btnLogin.getStyle().set("font-size", "30px");
        btnRegister.getStyle().set("padding", "30px 50px");
        btnLogin.getStyle().set("padding", "30px 50px");
//        btnLogin.getStyle().set("border-radius", "0px");
//        btnRegister.getStyle().set("border-radius", "0px");

        introduction.getStyle().set("font-family", "Georgia");
        introduction.getStyle().set("font-size", "36px");
        introduction.getStyle().set("color", "#2B3467");
        title.getStyle().set("font-family", "Georgia");
        title.getStyle().set("font-size", "64px");
        title.getStyle().set("color", "#2B3467");

        HorizontalLayout buttons = new HorizontalLayout();

        buttons.add(btnLogin, btnRegister);

        add(title, introduction, buttons, img);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.AROUND);
    }

}
