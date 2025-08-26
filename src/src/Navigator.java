package src;

import java.util.function.Supplier;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class Navigator {
    private static BorderPane mainPane;

    // 🔹 Properti untuk event aktif
    private static final BooleanProperty eventActive = new SimpleBooleanProperty(false);

    // 🔹 Nama event aktif
    private static final StringProperty activeEventName = new SimpleStringProperty("");

    // 🔹 Halaman aktif
    private static final StringProperty activePage = new SimpleStringProperty("Welcome");

    // 🔹 Supplier untuk halaman Home
    private static Supplier<Parent> homeSupplier;

    public static void init(BorderPane pane) {
        mainPane = pane;
    }

    public static void setHomeSupplier(Supplier<Parent> supplier) {
        homeSupplier = supplier;
    }

    // 🔹 Navigasi antar halaman
    public static void navigateTo(String page) {
        if (mainPane == null) return;

        // ✅ update halaman aktif
        setActivePage(page);

        switch (page) {
            case "Welcome" -> {
                VBox box = new VBox(15);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));

                Text title = new Text("Optimize Your Race Event\nUsing This App");
                title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                Button addBtn = new Button("Add Event");
                Button loadBtn = new Button("Load Event");

                addBtn.setOnAction(e -> {
                    Window owner = getOwnerWindow();
                    new CloseEvent().showAddEventDialog(owner);
                });

                loadBtn.setOnAction(e -> {
                    Window owner = getOwnerWindow();
                    new CloseEvent().showLoadEventDialog(owner);
                });

                box.getChildren().addAll(title, addBtn, loadBtn);
                mainPane.setCenter(box);
            }
            case "Home" -> {
                if (homeSupplier != null) {
                    mainPane.setCenter(homeSupplier.get());
                }
            }
            case "Participant" -> mainPane.setCenter(new Participant().getView());
            case "Timing" -> mainPane.setCenter(new Timing().getView());
            case "Setting" -> mainPane.setCenter(new Setting().getView());
            case "AddEvent" -> {
                Window owner = getOwnerWindow();
                new CloseEvent().showAddEventDialog(owner);
            }
            case "LoadEvent" -> {
                Window owner = getOwnerWindow();
                new CloseEvent().showLoadEventDialog(owner);
            }
            case "CloseEvent" -> mainPane.setCenter(new CloseEvent().getView());
        }
    }

    // 🔹 Alias biar cocok dengan Main / Sidebar
    public static void navigate(String page) {
        navigateTo(page);
    }

    // 🔹 Event aktif / tidak
    public static BooleanProperty eventActiveProperty() {
        return eventActive;
    }

    public static boolean isEventActive() {
        return eventActive.get();
    }

    public static void setEventActive(boolean active) {
        eventActive.set(active);
    }

    // 🔹 Nama event aktif
    public static StringProperty activeEventNameProperty() {
        return activeEventName;
    }

    public static String getActiveEventName() {
        return activeEventName.get();
    }

    public static void setActiveEventName(String name) {
        activeEventName.set(name);
    }

    // 🔹 Halaman aktif
    public static StringProperty activePageProperty() {
        return activePage;
    }

    public static String getActivePage() {
        return activePage.get();
    }

    public static void setActivePage(String page) {
        activePage.set(page);
    }

    // 🔹 Ambil window utama dengan aman
    private static Window getOwnerWindow() {
        return (mainPane != null && mainPane.getScene() != null) ? mainPane.getScene().getWindow() : null;
    }
}
