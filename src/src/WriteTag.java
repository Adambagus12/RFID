package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class WriteTag extends BorderPane {

    public WriteTag() {
        setPadding(new Insets(10));

        // Top navigation
        HBox topNav = new HBox(10);
        topNav.setAlignment(Pos.CENTER);

// Tombol kiri
FontIcon leftIcon = new FontIcon("far-arrow-alt-circle-left");
leftIcon.setIconSize(32); // atur ukuran ikon (default 16px)

Button leftBtn = new Button();
leftBtn.setGraphic(leftIcon);
leftBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
leftBtn.setPrefSize(55, 55); // ukuran tombol biar proporsional

// Field pencarian
TextField searchField = new TextField();
searchField.setPrefWidth(250);
searchField.setPrefHeight(55);

// Tombol kanan
FontIcon rightIcon = new FontIcon("far-arrow-alt-circle-right");
rightIcon.setIconSize(32); // atur ukuran ikon

Button rightBtn = new Button();
rightBtn.setGraphic(rightIcon);
rightBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
rightBtn.setPrefSize(55, 55);


        topNav.getChildren().addAll(leftBtn, searchField, rightBtn);
        setTop(topNav);

        // Info labels
        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(200);
        infoGrid.setVgap(60);
        infoGrid.setPadding(new Insets(15, 0, 20, 10));

        Label nameLabel = new Label("Name: -");
        nameLabel.setFont(new Font(14));
        Label emailLabel = new Label("Email: -");
        emailLabel.setFont(new Font(14));
        Label phoneLabel = new Label("Phone: -");
        phoneLabel.setFont(new Font(14));
        Label categoryLabel = new Label("Category: -");
        categoryLabel.setFont(new Font(14));

        infoGrid.add(nameLabel, 0, 0);
        infoGrid.add(emailLabel, 1, 0);
        infoGrid.add(phoneLabel, 0, 1);
        infoGrid.add(categoryLabel, 1, 1);

        setCenter(infoGrid);

        // Checkbox on right
        CheckBox literalBibCheck = new CheckBox("Create Literal Bib");
        BorderPane.setAlignment(literalBibCheck, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(literalBibCheck, new Insets(5, 15, 10, 0));
        setRight(literalBibCheck);

        // Table
        TableView<String> table = new TableView<>();
        table.setPlaceholder(new Label("No content in table"));

        TableColumn<String, String> colBib = new TableColumn<>("Bib");
        TableColumn<String, String> colSaved = new TableColumn<>("Saved");
        TableColumn<String, String> colSeen = new TableColumn<>("Seen");
        TableColumn<String, String> colWrite = new TableColumn<>("Write");
        TableColumn<String, String> colDelete = new TableColumn<>("Delete");

        colBib.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
        colSaved.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        colSeen.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        colWrite.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        colDelete.prefWidthProperty().bind(table.widthProperty().multiply(0.1));

        table.getColumns().addAll(colBib, colSaved, colSeen, colWrite, colDelete);
        setBottom(table);
    }

    // Tambahkan method supaya bisa dipanggil langsung
    public void showWindow() {
        Stage stage = new Stage();
        stage.setTitle("Write Tag");
        stage.setScene(new Scene(this, 650, 450));
        stage.show();
    }
}
