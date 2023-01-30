package com.example.application.model;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private String description = "No description set yet.";
    private String image = "https://play-lh.googleusercontent.com/DSA2hFJx9INOxXgMvd6TstVxe4UZKqHjMsGVRLNNXDsH-VkOodHfZ1hLtczp3udTvYU";

    public User() {
    }

    public User(int userID, String firstName, String lastName, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(int userID, String firstName, String lastName, String username, String password, String image) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
