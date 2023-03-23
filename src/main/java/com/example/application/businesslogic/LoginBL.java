package com.example.application.businesslogic;

import com.example.application.dao.LoginDAO;
import com.example.application.views.HomeView;

import java.util.Observable;

public class LoginBL extends Observable {
    private static final LoginBL loginBL = new LoginBL();
    private final LoginDAO login;

    public LoginBL() {
        login = new LoginDAO();
    }

    public static LoginBL getBusinessLogic() {
        return loginBL;
    }

    public int validate(String email, String password) {
        return login.isUser(email, password);
    }

    public int getID(String username, String password) {
        return login.findID(username, password);
    }

    public void send() {
        setChanged();
        addObserver(new HomeView());
        notifyObservers();
    }
}
