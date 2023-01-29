package com.example.application.views;

import com.example.application.businesslogic.UserBL;
import com.example.application.model.User;
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

import java.util.Observable;
import java.util.Observer;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY_INLINE;

@Route("user/:userID/")
@PageTitle("HomeView")
public class HomeView extends AppLayout implements Observer, BeforeEnterObserver {
    private String userID;
    private User user;
    private String userEmail;
    private String userRole;
    private UserBL userBL = new UserBL();

    private final VerticalLayout main = new VerticalLayout();

    private final Button homeButton = new Button("Home");
    private final Button exploreButton = new Button("Explore");
    private final Button profileButton = new Button("Profile");

    private Dialog createPostDialog = new Dialog();
    private Button createPostButton;

    private final VerticalLayout homePage = new VerticalLayout();
    private final VerticalLayout explorePage = new VerticalLayout();
    private final VerticalLayout personalPage = new VerticalLayout();

    private VerticalLayout currentPage;

    public HomeView() {
        getStyle().set("background-color", "#FCFFE7");


        setContent(main);
    }

    private void makeHeader(String userid){
        HorizontalLayout header = new HorizontalLayout();
        H3 hello = new H3("Welcome, chef " + userBL.getUsername(userid) + "!");
        hello.getStyle().set("font-family", "Georgia, serif");
        hello.getStyle().set("font-size", "18px");
        hello.getStyle().set("padding", "100px, 130px");
        hello.getStyle().set("border-radius", "0px");
//        Image homeImage = new Image("src/images/homeButton.png", "home image");
//        homeImage.setWidth("100px");

        homeButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        exploreButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        profileButton.addThemeVariants(ButtonVariant.LUMO_ICON);

        header.add(hello, homeButton, exploreButton, profileButton);
        header.setSizeFull();
        header.getStyle().set("background-color", "white");
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        main.add(header);
    }

    private void makeCreatePostLayout() {
        HorizontalLayout createPostLayout = new HorizontalLayout();

        createPostDialog.setHeaderTitle("Insert details to create a new post");
        createPostDialog.add(new MakePostView(user));
        createPostButton = new Button("Create a new post", l -> createPostDialog.open());

        Button closecDialog = new Button("Close", l -> createPostDialog.close());
        createPostDialog.getFooter().add(closecDialog);

        createPostLayout.getStyle().set("background-color", "white");
        createPostLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        createPostLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        createPostLayout.add(createPostButton);

        main.add(createPostLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        userID = beforeEnterEvent.getRouteParameters().get("userID").orElse("nu s-a primit id");
        user = userBL.getUser(userID);

        makeHeader(userID);
        makeCreatePostLayout();

        currentPage = homePage;
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
