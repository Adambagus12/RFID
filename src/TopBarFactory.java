package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class TopBarFactory {

    private static final Label clockLabel = new Label();
    private static Timeline clockTimeline;

    private static Consumer<String> navigator;
    public static void setNavigator(Consumer<String> nav) { navigator = nav; }

    public static HBox createTopBar() {
        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(8, 12, 8, 12));
        topBar.setStyle("-fx-background-color: #f5f5f7; -fx-border-color: #e6e6eb; -fx-border-width: 0 0 1 0;");

        Button tagCheckBtn = new Button("Tag Check");
        Button counterTimeBtn = new Button("Counter Time");

        tagCheckBtn.setPrefSize(120, 35);
        tagCheckBtn.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        tagCheckBtn.setStyle("-fx-background-color: #219d20; -fx-text-fill: white;");

        counterTimeBtn.setPrefSize(120, 35);
        counterTimeBtn.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        counterTimeBtn.setStyle("-fx-background-color: #219d20; -fx-text-fill: white;");

        tagCheckBtn.setOnAction(e -> { if (navigator != null) navigator.accept("Tag Check"); });
        counterTimeBtn.setOnAction(e -> { if (navigator != null) navigator.accept("Timing"); });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        clockLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        clockLabel.setTextFill(Color.web("#2c2c2c"));

        VBox rightClock = new VBox(clockLabel);
        rightClock.setAlignment(Pos.TOP_RIGHT);

        topBar.getChildren().addAll(tagCheckBtn, counterTimeBtn, spacer, rightClock);

        startClock(); 
        return topBar;
    }

    private static void startClock() {
        if (clockTimeline != null && clockTimeline.getStatus() == Timeline.Status.RUNNING) {
            return; 
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        clockTimeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> clockLabel.setText(LocalDateTime.now().format(fmt))),
            new KeyFrame(Duration.seconds(1))
        );
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }
}
