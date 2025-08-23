package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

public class CloseEvent extends BorderPane {

    private Runnable onCloseAction; 

    public CloseEvent() {
        setPrefSize(600, 400);

        
        VBox headerBox = new VBox();
        headerBox.setPadding(new Insets(10));
        headerBox.setSpacing(10);

        Label lblWelcome = new Label("Welcome");
        lblWelcome.setFont(Font.font("Arial Bold", 20));

        Label lblOptimize = new Label("Optimize Your Race Event");
        Label lblUsing = new Label("Using This App");

        HBox buttonHBox = new HBox(10);
        buttonHBox.setAlignment(Pos.CENTER);

        Button btnAddEvent = new Button("Add Event");
        btnAddEvent.setPrefSize(100, 40);
        btnAddEvent.setGraphic(new FontIcon("fas-folder-plus"));

        Button btnLoadEvent = new Button("Load Event");
        btnLoadEvent.setPrefSize(100, 40);
        btnLoadEvent.setGraphic(new FontIcon("fas-download"));

        buttonHBox.getChildren().addAll(btnAddEvent, btnLoadEvent);

        headerBox.getChildren().addAll(lblWelcome, lblOptimize, lblUsing, buttonHBox);
        setTop(headerBox);


        TableView<?> tableView = new TableView<>();
        tableView.setPrefHeight(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.getColumns().addAll(
                createColumn("Ping", 55),
                createColumn("Connected", 88),
                createColumn("Name", 117),
                createColumn("Model", 113),
                createColumn("IP", 42),
                createColumn("Port", 75),
                createColumn("Antena", 75)
        );

        setCenter(tableView);

    
        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        Button btnFindReaders = new Button("Find Readers");
        btnFindReaders.setFont(Font.font("System Bold", 12));

        Button btnCloseEvent = new Button("Close Event");
        btnCloseEvent.setFont(Font.font("System Bold", 12));
        btnCloseEvent.setGraphic(new FontIcon("fas-times-circle"));

        btnCloseEvent.setOnAction(e -> {
            if (onCloseAction != null) onCloseAction.run();
        });

        bottomBox.getChildren().addAll(btnFindReaders, btnCloseEvent);
        setBottom(bottomBox);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private TableColumn createColumn(String title, double width) {
        TableColumn col = new TableColumn(title);
        col.setPrefWidth(width);
        return col;
    }

    public void setOnCloseAction(Runnable action) {
        this.onCloseAction = action;
    }
}
