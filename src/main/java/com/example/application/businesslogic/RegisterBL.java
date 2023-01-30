package com.example.application.businesslogic;

import com.example.application.dao.RegisterDAO;

public class RegisterBL {
    RegisterDAO registerDAO = new RegisterDAO();

    public boolean insertUser(String firstName, String lastName, String username, String password, String description, String image) {
        if (image.isEmpty()){
            if (description.isEmpty()){
                return registerDAO.insertUser(firstName, lastName, username, password);
            }
            else
                return registerDAO.insertUserWithImage(firstName, lastName, username, password, image);
        } else if (description.isEmpty()) {
            return registerDAO.insertUserWithDescription(firstName, lastName, username, password, description);
        }
        else{
            return registerDAO.insertUserWithImageAndDescription(firstName, lastName, username, password, description, image);
        }

    }
}
