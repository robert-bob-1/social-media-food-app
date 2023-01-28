package com.example.application.businesslogic;

import com.example.application.dao.RegisterDAO;

public class RegisterBL {
    RegisterDAO registerDAO = new RegisterDAO();

    public boolean insertUser(String firstName, String lastName, String username, String password) {
        return registerDAO.insertUser(firstName, lastName, username, password);
    }
}
