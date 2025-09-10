package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteWave {
    public boolean showAndWait() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete Wave");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label lblConfirm = new Label("Are you sure to delete this Wave?");
        lblConfirm.setFont(Font.font(15));
        lblConfirm.setMaxWidth(Double.MAX_VALUE); 
        lblConfirm.setAlignment(Pos.CENTER);      

        HBox buttons = new HBox(20);
        buttons.setStyle("-fx-alignment: center;");

        Button btnOk = new Button("Ok");
        btnOk.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnOk.setFont(Font.font(14));

        Button btnCancel = new Button("Cancel");
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnCancel.setFont(Font.font(14));

        buttons.getChildren().addAll(btnOk, btnCancel);

        root.getChildren().addAll(lblConfirm, buttons);

        Scene scene = new Scene(root, 400, 120);
        stage.setScene(scene);

        stage.setMinWidth(300);
        stage.setMinHeight(120);
        stage.setMaxWidth(600);
        stage.setMaxHeight(400);

        final boolean[] result = {false};

        btnOk.setOnAction(e -> {
            result[0] = true;
            stage.close();
        });

        btnCancel.setOnAction(e -> {
            result[0] = false;
            stage.close();
        });

        stage.showAndWait();
        return result[0];
    }
}
