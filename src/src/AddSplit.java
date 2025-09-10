package src;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class AddSplit {

    public String showAndWait() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Split");

        BorderPane root = new BorderPane();

        VBox mainBox = new VBox();
        root.setCenter(mainBox);

        // Form bagian kiri (label)
        VBox leftBox = new VBox(30);
        leftBox.setPadding(new Insets(28, 0, 0, 20));
        Label lblOrder = new Label("Order");
        lblOrder.setFont(Font.font(17));
        Label lblName = new Label("Name");
        lblName.setFont(Font.font(17));
        Label lblType = new Label("Type");
        lblType.setFont(Font.font(17));
        Label lblDistance = new Label("Distance (m)");
        lblDistance.setFont(Font.font(17));
        Label lblGap = new Label("Gap (ms)");
        lblGap.setFont(Font.font(17));
        Label lblCategory = new Label("Kategori");
        lblCategory.setFont(Font.font(17));
        leftBox.getChildren().addAll(lblOrder, lblName, lblType, lblDistance, lblGap, lblCategory);

        // Form bagian kanan (input)
        VBox rightBox = new VBox(20);
        rightBox.setPadding(new Insets(15, 20, 0, 0));
        TextField txtOrder = new TextField();
        txtOrder.setPrefSize(425, 40);

        TextField txtName = new TextField();
        txtName.setPrefSize(425, 40);

        MenuButton menuType = new MenuButton("Select a Split Type");
        menuType.setPrefSize(425, 40);
        menuType.getItems().addAll(
                new MenuItem("START"),
                new MenuItem("INTERMEDIATE"),
                new MenuItem("FINISH")
        );

        TextField txtDistance = new TextField();
        txtDistance.setPrefSize(425, 40);

        TextField txtGap = new TextField();
        txtGap.setPrefSize(425, 40);

        ListView<String> listCategory = new ListView<>();
        listCategory.setPrefSize(425, 200);

        rightBox.getChildren().addAll(txtOrder, txtName, menuType, txtDistance, txtGap, listCategory);

        HBox form = new HBox(leftBox, rightBox);

        // Tombol Save & Cancel
        HBox buttons = new HBox(20);
        buttons.setPadding(new Insets(10, 20, 10, 0));
        buttons.setSpacing(10);
        buttons.setStyle("-fx-alignment: center-right;");

        Button btnSave = new Button("Save", new FontIcon("fas-save"));
        btnSave.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnSave.setPrefSize(80, 35);

        Button btnCancel = new Button("Cancel", new FontIcon("fas-times"));
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnCancel.setPrefSize(80, 35);

        buttons.getChildren().addAll(btnSave, btnCancel);

        mainBox.getChildren().addAll(form, buttons);

        Scene scene = new Scene(root, 600, 575);
        stage.setScene(scene);

        final String[] result = {null};

        btnSave.setOnAction(e -> {
            result[0] = txtName.getText();
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
