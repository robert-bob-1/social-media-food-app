package com.example.application.views.pages;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ExploreView extends VerticalLayout {

    //search bar
    HorizontalLayout searchLayout = new HorizontalLayout();
    TextField searchField = new TextField("Search users by name and posts by title");
    Button searchButton = new Button("Search");
    //most liked posts
    VerticalLayout posts = new VerticalLayout();

    public ExploreView () {
        makeSearchBar();
        searchLayout.add(searchField, searchButton);

        makeExplorePage();
    }

    public void makeSearchBar(){
        searchButton.setIcon(new Icon("vaadin", "search"));
        searchButton.addClickListener( e -> {

        })
    }

}
