package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public class ImportDataParticipant {

    public void show(Stage owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Import Data Participants");
        dialog.setResizable(false);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(14));

        Label title = new Label("Import Data Participants");
        title.setFont(Font.font("Arial", 14));
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(10, 0, 0, 0));

        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS);
        col0.setPercentWidth(70);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(120); 
        col1.setHalignment(javafx.geometry.HPos.RIGHT);

        grid.getColumnConstraints().addAll(col0, col1);

        TextField fileField = new TextField();
        fileField.setPromptText("Upload XLSX Data");
        fileField.setEditable(false);
        fileField.setPrefWidth(350);

        Button pickFileBtn = new Button(" Pick File", new FontIcon("fas-file-upload"));
        pickFileBtn.setFocusTraversable(false);
        pickFileBtn.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            File sel = fc.showOpenDialog(dialog);
            if (sel != null) {
                fileField.setText(sel.getAbsolutePath());
            }
        });

        Button templateBtn = new Button(" Template", new FontIcon("fas-download"));
        templateBtn.setFocusTraversable(false);
        templateBtn.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Download template not implemented.", ButtonType.OK);
            a.initOwner(dialog);
            a.showAndWait();
        });

        grid.add(fileField, 0, 0);
        grid.add(pickFileBtn, 1, 0);
        GridPane.setValignment(pickFileBtn, VPos.CENTER); 
        grid.add(templateBtn, 1, 1);
        GridPane.setValignment(templateBtn, VPos.TOP);

        CheckBox headerCheck = new CheckBox("First Row as Header");
        CheckBox bibCheck = new CheckBox("Create Bib Number Default");

        VBox centerBox = new VBox(12, grid, headerCheck, bibCheck);
        centerBox.setAlignment(Pos.TOP_LEFT);
        centerBox.setPadding(new Insets(6, 0, 0, 0));
        root.setCenter(centerBox);

        Button importBtn = new Button(" Import", new FontIcon("fas-save"));
        importBtn.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Import not implemented.", ButtonType.OK);
            a.initOwner(dialog);
            a.showAndWait();
        });

        Button cancelBtn = new Button(" Cancel", new FontIcon("fas-times"));
        cancelBtn.setOnAction(e -> dialog.close());

        HBox bottomRow = new HBox(10, importBtn, cancelBtn);
        bottomRow.setAlignment(Pos.CENTER_RIGHT);
        bottomRow.setPadding(new Insets(10, 0, 0, 0));
        root.setBottom(bottomRow);

        Scene scene = new Scene(root, 480, 220);
        dialog.setScene(scene);
        dialog.setOnShown(ev -> {
            if (owner != null) {
                double ownerX = owner.getX();
                double ownerY = owner.getY();
                double ownerW = owner.getWidth();
                double ownerH = owner.getHeight();
                double dlgW = dialog.getWidth();
                double dlgH = dialog.getHeight();
                dialog.setX(ownerX + (ownerW - dlgW) / 2.0);
                dialog.setY(ownerY + (ownerH - dlgH) / 2.0);
            } else {
                dialog.centerOnScreen();
            }
        });

        dialog.showAndWait();
    }
}
