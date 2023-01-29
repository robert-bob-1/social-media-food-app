package com.example.application.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int ID;
    private String title;
    private String description;
    private String picture;
    private String details;
    private String ingredients;
    private String preparation;
    private int likes;
    private Date datetime;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private User user;

    public Post(int ID, String title, String description, String picture, String details, String ingredients, String preparation, int likes, User user) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.details = details;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.likes = likes;
        this.datetime = new Date();
        this.user = user;
    }
    public Post(int ID, String title, String description, String picture, String details, String ingredients, String preparation, User user) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.details = details;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.likes = 0;
        this.datetime = new Date();
        this.user = user;
    }
    public Post(String title, String description, String picture, String details, String ingredients, String preparation, User user) {
        this.ID = -1;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.details = details;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.likes = 0;
        this.datetime = new Date();
        this.user = user;
    }

    public Post(String title, String description, String picture, String details, String ingredients, String preparation, int likes, String datetime, User user) {
        this.ID = -1;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.details = details;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.likes = likes;
        try{
            this.datetime = simpleDateFormat.parse(datetime);
        } catch(ParseException e){
            e.printStackTrace();
        }
        this.user = user;
    }

    public Post(int ID, String title, String description, String picture, String details, String ingredients, String preparation, int likes, String datetime, User user) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.details = details;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.likes = likes;
        try{
            this.datetime = simpleDateFormat.parse(datetime);
        } catch(ParseException e){
            e.printStackTrace();
        }
        this.user = user;
    }

    public Date getDatetimeDate() {
        return datetime;
    }
    public String getDatetimeString() {
        return simpleDateFormat.format(datetime);
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
