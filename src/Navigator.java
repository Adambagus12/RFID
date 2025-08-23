package src;

import java.util.function.Supplier;
import javafx.beans.property.*;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Navigator {

    private static BorderPane mainPane;
    private static Pane sidebarHost;
    private static Supplier<Parent> homeSupplier;

    // 🔹 state event aktif
    private static final BooleanProperty eventActive = new SimpleBooleanProperty(false);
    private static final StringProperty activeEventName = new SimpleStringProperty("");

    public static void init(BorderPane main, Pane sidebarContainer) {
        mainPane = main;
        sidebarHost = sidebarContainer;
    }

    public static void setHomeSupplier(Supplier<Parent> supplier) {
        homeSupplier = supplier;
    }

    public static BooleanProperty eventActiveProperty() { return eventActive; }
    public static StringProperty activeEventNameProperty() { return activeEventName; }

    public static void setActiveEvent(String eventName) {
        eventActive.set(true);
        activeEventName.set(eventName);
    }

    public static void clearActiveEvent() {
        eventActive.set(false);
        activeEventName.set("");
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
            case "CloseEvent" -> {
                CloseEvent closeEvent = new CloseEvent();
                closeEvent.setOnCloseAction(() -> navigate("Home"));
                mainPane.setCenter(closeEvent);
            }
            case "AddEvent" -> {
                AddEvent dialog = new AddEvent();
                dialog.showAndWait().ifPresent(eventName -> {
                    setActiveEvent(eventName);
                    mainPane.setCenter((homeSupplier != null) ? homeSupplier.get() : new Label("Home content"));
                });
            }
            case "LoadEvent" -> {
                LoadEvent dialog = new LoadEvent();
                dialog.showAndWait().ifPresent(eventName -> {
                    setActiveEvent(eventName);
                    mainPane.setCenter((homeSupplier != null) ? homeSupplier.get() : new Label("Home content"));
                });
            }
            case "Participant" -> mainPane.setCenter(new Participant().getView());
            case "Timing" -> mainPane.setCenter(new Timing().getView());
            case "Setting" -> mainPane.setCenter(new Setting());
            case "Tag Check" -> mainPane.setCenter(new Label("Tag Check page (TBD)"));
            default -> mainPane.setCenter(new Label("Unknown page: " + page));
        }
    }
}
