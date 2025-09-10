package src;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteSplit {
    public boolean showAndWait() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete Split");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label lblConfirm = new Label("Are You Sure to Delete This Split");
        lblConfirm.setFont(Font.font(15));
        lblConfirm.setStyle("-fx-alignment: center;");

        HBox buttons = new HBox(25);
        buttons.setStyle("-fx-alignment: center;");

        Button btnOk = new Button("Ok");
        btnOk.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnOk.setPrefSize(70, 40);

        Button btnCancel = new Button("Cancel");
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnCancel.setPrefSize(70, 40);

        buttons.getChildren().addAll(btnOk, btnCancel);

        root.getChildren().addAll(lblConfirm, buttons);

        Scene scene = new Scene(root, 350, 150);
        stage.setScene(scene);

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
