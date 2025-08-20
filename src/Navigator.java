package src;

import java.util.function.Supplier;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Navigator {

    private static BorderPane mainPane;
    private static Pane sidebarHost;
    private static Supplier<Parent> homeSupplier;

    public static void init(BorderPane main, Pane sidebarContainer) {
        mainPane = main;
        sidebarHost = sidebarContainer;
    }

    public static void setHomeSupplier(Supplier<Parent> supplier) {
        homeSupplier = supplier;
    }

    public static void navigate(String page) {
        if (mainPane == null || sidebarHost == null) return;

        sidebarHost.getChildren().setAll(
            SidebarFactory.createSidebar(page, Navigator::navigate)
        );

        switch (page) {
            case "Home" -> {
                Parent center = (homeSupplier != null) ? homeSupplier.get() : new Label("Home content");
                mainPane.setCenter(center);
            }
            case "Participant" -> mainPane.setCenter(new Participant().getView());
            case "Timing" -> mainPane.setCenter(new Timing().getView());
            case "Setting" -> mainPane.setCenter(new Setting()); 
            case "Tag Check" -> mainPane.setCenter(new Label("Tag Check page (TBD)"));
            default -> mainPane.setCenter(new Label("Unknown page: " + page));
        }
    }
}
