package com.example.application.businesslogic;

import com.example.application.dao.LoginDAO;
import com.example.application.views.MainView;

import java.util.Observable;

public class LoginBL extends Observable {
    private static final LoginBL loginLogic = new LoginBL();
    private final LoginDAO login;

    public LoginBL() {
        login = new LoginDAO();
    }

    public static LoginBL getLoginLogic() {
        return loginLogic;
    }

    public boolean authenticate(String email, String password) {
        return login.isUser(email, password);
    }

    public int getID(String email) {
        return login.findID(email);
    }

//    public void send() {
//        setChanged();
//        addObserver(new MainView());
//        notifyObservers();
//    }
}
