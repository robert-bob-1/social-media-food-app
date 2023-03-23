package com.example.application.views;

import com.example.application.businesslogic.PostBL;
import com.example.application.businesslogic.UserBL;
import com.example.application.model.Post;
import com.example.application.model.User;
import com.example.application.views.components.CreateAdminDialog;
import com.example.application.views.pages.ExploreView;
import com.example.application.views.pages.ProfileView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY_INLINE;

@Route("user/:userID/")
@PageTitle("HomeView")
public class HomeView extends AppLayout implements Observer, BeforeEnterObserver {
    private String userID;
    private User user;

    private UserBL userBL = new UserBL();
    private PostBL postBL = new PostBL();

    private final VerticalLayout main = new VerticalLayout();

    private final Button homeButton = new Button("Home");
    private final Button exploreButton = new Button("Explore");
    private final Button profileButton = new Button("Profile");
    private final Button createAdminButton = new Button("Create admin");

    private CreateAdminDialog createAdminDialog;
    private Dialog createPostDialog = new Dialog();
    private Button createPostButton;

    private VerticalLayout homePage = new VerticalLayout();
    private VerticalLayout explorePage = new VerticalLayout();
    private VerticalLayout profilePage = new VerticalLayout();

    private VerticalLayout currentPage;

    public HomeView() {
        getStyle().set("background-color", "#FCFFE7");

        homeButton.addClickListener(e -> {
            makeHomePage();

            main.remove(explorePage);
            main.remove(profilePage);
            main.remove(homePage);

            main.add(homePage);
        });
        homePage.setAlignItems(FlexComponent.Alignment.CENTER);
        homePage.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        exploreButton.addClickListener( e -> {
            explorePage = new ExploreView(user);

            main.remove(explorePage);
            main.remove(homePage);
            main.remove(profilePage);
            main.add(explorePage);
        });

        profileButton.addClickListener( e -> {
            profilePage = new ProfileView(user, user);

            main.remove(profilePage);
            main.remove(explorePage);
            main.remove(homePage);
            main.add(profilePage);
        });

        createAdminButton.addClickListener( e -> {
            createAdminDialog = new CreateAdminDialog(user);
            createAdminDialog.open();
            createAdminDialog.clearAll();
        });

        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, homePage, explorePage, profilePage);
        setContent(main);
    }


    private void makeHeader(){
        HorizontalLayout header = new HorizontalLayout();

        String welcomeText;
        if(!user.isAdmin())
            welcomeText = "Welcome, chef " + user.getFirstName() + " " + user.getLastName() + "!";
        else
            welcomeText = "Welcome, admin!";

        H3 hello = new H3(welcomeText);
        hello.getStyle().set("font-family", "Georgia, serif");
        hello.getStyle().set("font-size", "18px");
        hello.getStyle().set("padding", "100px, 130px");

        homeButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        exploreButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        profileButton.addThemeVariants(ButtonVariant.LUMO_ICON);

        header.add(hello, homeButton, exploreButton);
        if(!user.isAdmin())
            header.add(profileButton);
        else
            header.add(createAdminButton);
        header.setSizeFull();
        header.getStyle().set("background-color", "white");
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.getStyle().set("border", "1px outset #E7E2E1");
        header.getStyle().set("border-radius", "7px");
        main.add(header);
    }

    /**
     * Create and set the post creating portion of the layout which is always present
     */
    private void makeCreatePostLayout() {
        VerticalLayout createPostLayout = new VerticalLayout();

        createPostDialog.setHeaderTitle("Insert details to create a new post");
        createPostDialog.add(new MakePostView(user));
        createPostButton = new Button("Create a new post", l -> createPostDialog.open());

        Button closeDialog = new Button("Close", l -> createPostDialog.close());
        createPostDialog.getFooter().add(closeDialog);

        createPostLayout.getStyle().set("background-color", "white");
        createPostLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        createPostLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        createPostLayout.add(createPostButton);
        createPostLayout.setWidth("200px");
        createPostLayout.setHeight("60px");
        createPostLayout.getStyle().set("border", "1px outset #E7E2E1");
        createPostLayout.getStyle().set("border-radius", "7px");

        main.add(createPostLayout);
        main.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, createPostLayout);
    }

    /**
     * Design and controller class for the primary home page.
     * This function creates the home page for every user, populating it with POSTS from USERS that CURRENT USER FOLLOWS.
     * They will be ordered cronologically.
     */
    private void makeHomePage(){
        ArrayList<Post> posts = postBL.getFollowedPosts(user.getUserID());
        if(posts == null || posts.size() == 0){
            posts = postBL.getAllPosts();
        }

        homePage.removeAll();
        for(Post post : posts){
            homePage.add(new PostView(post, user));
        }
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        userID = beforeEnterEvent.getRouteParameters().get("userID").orElse("nu s-a primit id");
        user = userBL.getUser(userID);

        makeHeader();
        if(!user.isAdmin())
            makeCreatePostLayout();

        makeHomePage();

        main.add(homePage);
    }

    @Override
    public void update(Observable o, Object arg) {
        Notification notification = createNotification();
        notification.open();
    }

    private static Notification createNotification() {
        Notification notification = new Notification();

        Span name = new Span();
        name.getStyle().set("font-weight", "500");
        Div info = new Div(
                name,
                new Text("Log in succeeded!")
        );

        HorizontalLayout layout = new HorizontalLayout(info, createCloseButton(notification));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.setDuration(2000);

        return notification;
    }
    private static Button createCloseButton(Notification notification) {
        Button closeButton = new Button(
                VaadinIcon.CLOSE_SMALL.create(),
                clickEvent -> notification.close());
        closeButton.addThemeVariants(LUMO_TERTIARY_INLINE);

        return closeButton;
    }
}
