package src;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Home {

    public static VBox createMainContent() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.setStyle("-fx-background-color: #f4f4f4;");

        VBox topSection = new VBox(10);
        topSection.getChildren().addAll(createHeaderText(), createStatsRow());

        Region spacer = new Region();
        spacer.setPrefHeight(150);

        VBox bottomSection = createFindReadersSection();

        content.getChildren().addAll(topSection, spacer, bottomSection);
        return content;
    }

    private static VBox createHeaderText() {
        VBox box = new VBox(4);
        box.setAlignment(Pos.TOP_LEFT);

        Text title = new Text("Welcome");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        VBox.setMargin(title, new Insets(10, 0, 0, 10));

        Text subtitle = new Text();
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        VBox.setMargin(subtitle, new Insets(0, 0, 0, 10)); 

        subtitle.textProperty().bind(
            Bindings.when(Navigator.eventActiveProperty())
                    .then(Navigator.activeEventNameProperty())
                    .otherwise("")
        );

        box.getChildren().addAll(title, subtitle);
        return box;
    }

    private static HBox createStatsRow() {
        HBox stats = new HBox(20);
        stats.setPadding(new Insets(10, 0, 10, 0));

        VBox participantsBox = createStatBox("Number of Participants", "0", "#0b7203");
        VBox categoriesBox = createStatBox("Number of Categories", "0", "#f29c11");

        HBox.setHgrow(participantsBox, Priority.ALWAYS);
        HBox.setHgrow(categoriesBox, Priority.ALWAYS);

        stats.getChildren().addAll(participantsBox, categoriesBox);
        return stats;
    }

    private static VBox createStatBox(String title, String value, String backgroundColor) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white;");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setPadding(new Insets(8));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        valueLabel.setMaxWidth(Double.MAX_VALUE);
        valueLabel.setPadding(new Insets(10, 0, 10, 0));

        VBox whiteBox = new VBox(titleLabel, valueLabel);
        whiteBox.setStyle("-fx-background-color: white; -fx-background-radius: 4;");
        whiteBox.setAlignment(Pos.CENTER);
        whiteBox.setPadding(new Insets(5));
        whiteBox.setMinHeight(100);

        if (title.equals("Number of Categories")) {
            Button closeEventBtn = new Button("✖ Close Event");
            closeEventBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            closeEventBtn.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            closeEventBtn.setOnAction(e -> Navigator.navigateTo("CloseEvent"));

            StackPane stack = new StackPane(whiteBox);
            StackPane.setAlignment(closeEventBtn, Pos.TOP_RIGHT);
            StackPane.setMargin(closeEventBtn, new Insets(-20, 5, 0, 2));
            stack.getChildren().add(closeEventBtn);

            VBox wrapper = new VBox(stack);
            wrapper.setAlignment(Pos.CENTER);
            return wrapper;
        }

        VBox wrapper = new VBox(whiteBox);
        wrapper.setAlignment(Pos.CENTER);
        return wrapper;
    }

    private static VBox createFindReadersSection() {
        VBox section = new VBox(10);

        Button findReadersBtn = new Button("Find Readers");
        findReadersBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        findReadersBtn.setPadding(new Insets(6, 16, 6, 16));

        HBox buttonRow = new HBox(findReadersBtn);
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
}
