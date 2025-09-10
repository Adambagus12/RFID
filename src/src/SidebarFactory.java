package src;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SidebarFactory {

    public static VBox createSidebar(Consumer<String> onNavigate) {
        VBox sidebar = new VBox();
        sidebar.setStyle("-fx-background-color: #151233;");
        sidebar.setPrefWidth(80);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setSpacing(20);
        sidebar.setPadding(new Insets(0, 0, 0, 0));

        sidebar.setMinHeight(0);
        sidebar.setMaxHeight(Double.MAX_VALUE);

        sidebar.parentProperty().addListener((obs, oldP, newP) -> {
            if (newP instanceof Region r) {
                sidebar.prefHeightProperty().bind(r.heightProperty());
            }
        });

        ImageView logo = new ImageView(new Image("file:assets/ironativ_logo.png"));
        logo.setFitHeight(30);
        logo.setPreserveRatio(true);

        VBox logoBox = new VBox(logo);
        logoBox.setAlignment(Pos.CENTER);
        sidebar.getChildren().add(logoBox);

        VBox btnHome        = createSidebarButton("Home", "file:assets/home.png", onNavigate);
        VBox btnParticipant = createSidebarButton("Participant", "file:assets/running.png", onNavigate);
        VBox btnTiming      = createSidebarButton("Timing", "file:assets/stopwatch.png", onNavigate);
        VBox btnSetting     = createSidebarButton("Setting", "file:assets/settings.png", onNavigate);

        btnHome.disableProperty().bind(Navigator.eventActiveProperty().not());
        btnParticipant.disableProperty().bind(Navigator.eventActiveProperty().not());
        btnTiming.disableProperty().bind(Navigator.eventActiveProperty().not());
        btnSetting.disableProperty().bind(Navigator.eventActiveProperty().not());

        sidebar.getChildren().addAll(btnHome, btnParticipant, btnTiming, btnSetting);

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);
        sidebar.getChildren().add(filler);

        return sidebar;
    }

    private static VBox createSidebarButton(String title, String iconPath, Consumer<String> onNavigate) {
        VBox box = new VBox(3);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(4));
        box.setStyle("-fx-background-color: transparent;");

        ImageView icon = new ImageView(new Image(iconPath));
        icon.setFitHeight(18);
        icon.setPreserveRatio(true);

        Label label = new Label(title);
        label.setTextFill(Color.web("#00d9f5"));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(70);
        label.setMinHeight(24);

        Tooltip.install(box, new Tooltip(title));
        box.getChildren().addAll(icon, label);

        Navigator.activePageProperty().addListener((obs, oldPage, newPage) -> {
            if (title.equals(newPage)) {
                box.setStyle("-fx-background-color: #4B437F;");
            } else {
                box.setStyle("-fx-background-color: transparent;");
            }
        });

        box.setOnMouseEntered(e -> {
            if (!box.isDisabled()) {
                box.setStyle("-fx-background-color: #2f2a52;");
                box.setCursor(javafx.scene.Cursor.HAND);
            }
        });
        box.setOnMouseExited(e -> {
            if (title.equals(Navigator.getActivePage())) {
                box.setStyle("-fx-background-color: #4B437F;");
            } else {
                box.setStyle("-fx-background-color: transparent;");
            }
        });

        box.setOnMouseClicked(e -> {
            if (!box.isDisabled() && onNavigate != null) {
                onNavigate.accept(title);
            }
        });

        return box;
    }
}
