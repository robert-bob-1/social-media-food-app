package com.example.application.views.pages;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.example.application.views.PostView;
import com.example.application.views.components.ExploreProfileView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;

public class ExploreView extends VerticalLayout {

    private final User currentUser;
    String searchCriteria;

    private final UserBL userBL = new UserBL();
    private final PostBL postBL = new PostBL();

    //search bar
    HorizontalLayout searchLayout = new HorizontalLayout();
    TextField searchField = new TextField("Search users by name and posts by title");
    RadioButtonGroup<String> searchCheckBox = new RadioButtonGroup<>("Select which results you wish to search for:");
    Button searchButton= new Button("Search");

    //search results
    ArrayList<Post> postArrayList = new ArrayList<>();
    ArrayList<User> userArrayList = new ArrayList<>();
    VerticalLayout searchResultsLayout = new VerticalLayout();


    //most liked posts
    VerticalLayout explorePostsLayout = new VerticalLayout();


    public ExploreView (User u) {
        currentUser = u;

        makeSearchBar();
        add(searchLayout);
        add(searchResultsLayout);

        makeExplorePage();
        add(explorePostsLayout);

        setAlignItems(FlexComponent.Alignment.CENTER);

    }

    private void makeExplorePage() {
        ArrayList<Post> posts = postBL.getMostPopularPosts();
        if(posts == null || posts.size() == 0){
            explorePostsLayout.add(new Label("No new posts in the last 24 hours."));
        }
        for(Post post : posts){
            explorePostsLayout.add(new PostView(post, currentUser));
        }
        explorePostsLayout.getStyle().set("background-color", "white");
        explorePostsLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        explorePostsLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        explorePostsLayout.getStyle().set("border", "1px outset #E7E2E1");
        explorePostsLayout.getStyle().set("border-radius", "7px");
    }

    public void makeSearchBar(){
        searchField.getStyle().set("padding", "10px 10px");
        searchField.setMinWidth("300px");

        searchCheckBox.setItems("Users", "Posts");
        searchCheckBox.addValueChangeListener( event -> {
           setSearchResults();
        });
        searchButton.setIcon(new Icon("vaadin", "search"));
        searchButton.getStyle().set("padding", "10px");

        searchButton.addClickListener( e -> {
            searchCriteria = searchField.getValue();

            setSearchResults();
        });

        searchResultsLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        searchResultsLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        searchLayout.add(searchField,searchCheckBox, searchButton, searchResultsLayout);
        searchLayout.getStyle().set("background-color", "white");
        searchLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        searchLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        searchLayout.getStyle().set("border", "1px outset #E7E2E1");
        searchLayout.getStyle().set("border-radius", "7px");
    }

    private void setSearchResults(){
        searchResultsLayout.removeAll();

        //search users
        if(searchCheckBox.getValue().equals("Users")) {
            userArrayList = userBL.getUsersByName(searchCriteria);
            for (User user : userArrayList) {
                searchResultsLayout.add(new ExploreProfileView(user, currentUser));
            }
        }
        //search posts
        if(searchCheckBox.getValue().equals("Posts")) {
            postArrayList = postBL.getPostsByTitle(searchCriteria);
            for (Post post : postArrayList) {
                searchResultsLayout.add(new PostView(post, currentUser));
            }
        }
    }
}
