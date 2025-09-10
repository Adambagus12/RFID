package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class AddWave {
    public String showAndWait() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Wave");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        // Label & TextField
        Label lblName = new Label("Name");
        lblName.setFont(Font.font(18));
        lblName.setPrefWidth(80); // lebar label
        lblName.setMinWidth(Region.USE_PREF_SIZE);
        lblName.setMaxWidth(Region.USE_PREF_SIZE);

        TextField txtName = new TextField();
        txtName.setPrefWidth(255);   // lebar input
        txtName.setPrefHeight(40);   // tinggi input

        HBox inputRow = new HBox(15, lblName, txtName);

        // Tombol
        HBox buttons = new HBox(15);
        buttons.setPadding(new Insets(10));
        buttons.setAlignment(Pos.CENTER_RIGHT); // tombol rata kanan

        Button btnSave = new Button("Save", new FontIcon("fas-save"));
        btnSave.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnSave.setFont(Font.font(14));

        Button btnCancel = new Button("Cancel", new FontIcon("fas-times"));
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnCancel.setFont(Font.font(14));

        buttons.getChildren().addAll(btnSave, btnCancel);

        root.getChildren().addAll(inputRow, buttons);

        Scene scene = new Scene(root, 400, 160);
        stage.setScene(scene);

        final String[] result = {null};

        btnSave.setOnAction(e -> {
            result[0] = txtName.getText().trim();
            stage.close();
        });

        btnCancel.setOnAction(e -> {
            result[0] = null;
            stage.close();
        });

        stage.showAndWait();
        return result[0];
    }
}
