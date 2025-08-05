package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class EventDashboardApp extends Application {

    private Label clockLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setLeft(createSidebar());
        root.setCenter(createMainContent());

        Scene scene = new Scene(root, 1366, 768);
        stage.setTitle("Event");
        stage.setScene(scene);
        stage.show();

        startClock();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setStyle("-fx-background-color: #151233;");
        sidebar.setMinWidth(Region.USE_COMPUTED_SIZE);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(6, 4, 0, 4));

        ImageView logo = new ImageView(new Image("file:assets/ironativ_logo.png"));
        logo.setFitHeight(28);
        logo.setPreserveRatio(true);

        VBox logoBox = new VBox(logo);
        logoBox.setAlignment(Pos.CENTER);

        sidebar.getChildren().add(logoBox);
        sidebar.getChildren().addAll(
                createSidebarButton("Home", "file:assets/home.png", true),
                createSidebarButton("Participant", "file:assets/running.png", false),
                createSidebarButton("Timing", "file:assets/stopwatch.png", false),
                createSidebarButton("Setting", "file:assets/settings.png", false)
        );

        return sidebar;
    }

    private VBox createSidebarButton(String title, String iconPath, boolean selected) {
        VBox box = new VBox(3);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(4));
        box.setStyle(selected
                ? "-fx-background-color: #4B437F;"
                : "-fx-background-color: transparent;");

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

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #2f2a52;"));
        box.setOnMouseExited(e -> box.setStyle(selected
                ? "-fx-background-color: #4B437F;"
                : "-fx-background-color: transparent;"));

        return box;
    }

    private VBox createMainContent() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.setStyle("-fx-background-color: #f4f4f4;");

        VBox topSection = new VBox(10);
        topSection.getChildren().addAll(
                createTopBar(),
                createHeaderText(),
                createStatsRow()
        );

        Region spacer = new Region();
        spacer.setPrefHeight(150); 

        VBox bottomSection = createFindReadersSection();

        content.getChildren().addAll(topSection, spacer, bottomSection);
        return content;
    }

    private HBox createTopBar() {
        HBox bar = new HBox(10);
        bar.setAlignment(Pos.CENTER_LEFT);

        Button tagCheckBtn = new Button("Tag Check");
        Button counterTimeBtn = new Button("Counter Time");

        tagCheckBtn.setPrefWidth(120);
        tagCheckBtn.setPrefHeight(35);
        tagCheckBtn.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        counterTimeBtn.setPrefWidth(120);
        counterTimeBtn.setPrefHeight(35);
        counterTimeBtn.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        tagCheckBtn.setStyle("-fx-background-color: #219d20; -fx-text-fill: white;");
        counterTimeBtn.setStyle("-fx-background-color: #219d20; -fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        clockLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        clockLabel.setTextFill(Color.web("#2c2c2c"));

        Button closeEventBtn = new Button("✖ Close Event");
        closeEventBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        closeEventBtn.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        closeEventBtn.setPadding(new Insets(4, 10, 4, 10));

        VBox rightBox = new VBox(5);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        rightBox.getChildren().addAll(clockLabel, closeEventBtn);

        bar.getChildren().addAll(tagCheckBtn, counterTimeBtn, spacer, rightBox);
        return bar;
    }

    private VBox createHeaderText() {
        VBox box = new VBox(4);
        Text title = new Text("Welcome");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Text subtitle = new Text("RUN");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        box.getChildren().addAll(title, subtitle);
        return box;
    }

    private HBox createStatsRow() {
        HBox stats = new HBox(20);
        stats.setPadding(new Insets(10, 0, 10, 0));

        VBox participantsBox = createStatBox("Number of Participants", "0", "#0b7203");
        VBox categoriesBox = createStatBox("Number of Categories", "0", "#f29c11");

        HBox.setHgrow(participantsBox, Priority.ALWAYS);
        HBox.setHgrow(categoriesBox, Priority.ALWAYS);

        stats.getChildren().addAll(participantsBox, categoriesBox);
        return stats;
    }

    private VBox createStatBox(String title, String value, String backgroundColor) {
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setStyle("-fx-background-color: " + backgroundColor + ";");
        titleLabel.setPadding(new Insets(8));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        valueLabel.setTextFill(Color.BLACK);
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        valueLabel.setMaxWidth(Double.MAX_VALUE);
        valueLabel.setPadding(new Insets(10, 0, 10, 0));

        VBox whiteBox = new VBox(titleLabel, valueLabel);
        whiteBox.setStyle("-fx-background-color: white; -fx-background-radius: 4;");
        whiteBox.setPadding(new Insets(5));
        whiteBox.setAlignment(Pos.CENTER);
        whiteBox.setMaxWidth(Double.MAX_VALUE);

        VBox wrapper = new VBox(whiteBox);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setMinHeight(100);
        wrapper.setStyle("-fx-background-color: transparent;");
        wrapper.setMaxWidth(Double.MAX_VALUE);

        return wrapper;
    }

    private VBox createFindReadersSection() {
        VBox section = new VBox(10);

        Button btn = new Button("Find Readers");
        btn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btn.setPadding(new Insets(6, 16, 6, 16));

        HBox buttonRow = new HBox(btn);
        buttonRow.setAlignment(Pos.CENTER_LEFT);
        buttonRow.setPadding(new Insets(10, 0, 5, 5));

        TableView<Object> table = new TableView<>();
        String[] columns = {"Ping", "Connected ?", "Name", "Model", "IP", "Port", "Antenna"};
        for (String colName : columns) {
            TableColumn<Object, String> col = new TableColumn<>(colName);
            col.setMinWidth(100);
            table.getColumns().add(col);
        }
        table.setPlaceholder(new Label("No content in table"));
        table.setPrefHeight(400);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(table, Priority.ALWAYS);

        section.getChildren().addAll(buttonRow, table);
        return section;
    }

    private void startClock() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                String formatted = now.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss"));
                javafx.application.Platform.runLater(() -> clockLabel.setText(formatted));
            }
        }, 0, 1000);
    }
}
