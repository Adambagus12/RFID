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

public class AddCategory extends AnchorPane {

    private TextField txtName;
    private ComboBox<String> cbWave;
    private String result;

    public AddCategory() {
        setPrefSize(550, 200);

        VBox root = new VBox();
        root.setPrefSize(550, 200);

        // Bagian atas (Label + Field)
        HBox hBoxTop = new HBox();
        hBoxTop.setPrefSize(550, 200);

        // Kolom kiri (Label)
        VBox vboxLeft = new VBox();
        vboxLeft.setPrefSize(145, 150);
        vboxLeft.setPadding(new Insets(15, 0, 0, 20));

        Label lblName = new Label("Name");
        lblName.setFont(new Font(18));
        VBox.setMargin(lblName, new Insets(10, 0, 0, 0));
        lblName.setPadding(new Insets(0, 0, 15, 0));

        Label lblWave = new Label("Wave");
        lblWave.setFont(new Font(18));
        VBox.setMargin(lblWave, new Insets(25, 0, 0, 0));

        vboxLeft.getChildren().addAll(lblName, lblWave);

        // Kolom kanan (Input)
        VBox vboxRight = new VBox();
        vboxRight.setPrefSize(405, 150);
        vboxRight.setPadding(new Insets(15, 20, 0, 0));

        txtName = new TextField();
        txtName.setPrefSize(405, 45);

        cbWave = new ComboBox<>();
        cbWave.setPrefSize(405, 45);
        VBox.setMargin(cbWave, new Insets(20, 0, 0, 0));

        vboxRight.getChildren().addAll(txtName, cbWave);

        hBoxTop.getChildren().addAll(vboxLeft, vboxRight);

        // Bagian bawah (Button)
        HBox hBoxBottom = new HBox();
        hBoxBottom.setPrefSize(550, 150);
        hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
        hBoxBottom.setPadding(new Insets(0, 20, 0, 0));

        Button btnSave = new Button("Save", new FontIcon("fas-save"));
        btnSave.setPrefSize(80, 40);
        btnSave.setStyle("-fx-background-color: green;");
        btnSave.setTextFill(javafx.scene.paint.Color.WHITE);
        btnSave.setFont(new Font(14));
        HBox.setMargin(btnSave, new Insets(0, 15, 0, 0));

        Button btnCancel = new Button("Cancel", new FontIcon("fas-times"));
        btnCancel.setPrefSize(80, 40);
        btnCancel.setStyle("-fx-background-color: red;");
        btnCancel.setTextFill(javafx.scene.paint.Color.WHITE);
        btnCancel.setFont(new Font(14));

        hBoxBottom.getChildren().addAll(btnSave, btnCancel);

        root.getChildren().addAll(hBoxTop, hBoxBottom);

        getChildren().add(root);

        // Action tombol
        btnSave.setOnAction(e -> {
            result = txtName.getText();
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
        });

        btnCancel.setOnAction(e -> {
            result = null;
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
        });
    }

    // ✅ Method showAndWait biar mirip AddWave
    public String showAndWait() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Category");
        dialog.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(this);
        dialog.setScene(scene);

        dialog.showAndWait();
        return result;
    }
}
