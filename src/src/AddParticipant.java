package src;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.time.LocalDate;
import java.util.function.Consumer;

public class AddParticipant {

    public static class FormData {
        String bib, firstName, lastName, sex, email, phone, team, category, wave;
        LocalDate birthDate;
    }

    private final Consumer<FormData> onSave;

    public AddParticipant(Consumer<FormData> onSave) {
        this.onSave = onSave;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Add Participant");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(12);
        grid.setPadding(new Insets(15));

        // Column constraints: [Label, Field, Label, Field]
        ColumnConstraints col1 = new ColumnConstraints(100);
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints(300);
        ColumnConstraints col3 = new ColumnConstraints(100);
        col3.setHalignment(HPos.RIGHT);
        ColumnConstraints col4 = new ColumnConstraints(300);
        grid.getColumnConstraints().addAll(col1, col2, col3, col4);

        String fieldStyle = "-fx-pref-height: 30px; -fx-pref-width: 300px;";

        // Input Fields
        TextField bibField = new TextField();          bibField.setStyle(fieldStyle);
        TextField lastNameField = new TextField();     lastNameField.setStyle(fieldStyle);
        ComboBox<String> sexCombo = new ComboBox<>();  sexCombo.getItems().addAll("M", "F"); sexCombo.setStyle(fieldStyle);
        TextField teamField = new TextField();         teamField.setStyle(fieldStyle);
        TextField emailField = new TextField();        emailField.setStyle(fieldStyle);

        ComboBox<String> categoryCombo = new ComboBox<>(); categoryCombo.getItems().addAll("5K","10K","21K"); categoryCombo.setStyle(fieldStyle);
        TextField firstNameField = new TextField();    firstNameField.setStyle(fieldStyle);
        DatePicker birthDatePicker = new DatePicker(); birthDatePicker.setStyle(fieldStyle);
        ComboBox<String> waveCombo = new ComboBox<>(); waveCombo.getItems().addAll("Wave 1","Wave 2","Wave 3"); waveCombo.setStyle(fieldStyle);
        TextField phoneField = new TextField();        phoneField.setStyle(fieldStyle);

        // Labels
        Label lblBib = new Label("Bib Num");
        Label lblLast = new Label("Last Name");
        Label lblSex = new Label("Sex");
        Label lblTeam = new Label("Team");
        Label lblEmail = new Label("Email");
        Label lblCategory = new Label("Category");
        Label lblFirst = new Label("First Name");
        Label lblBirth = new Label("Birth Date");
        Label lblWave = new Label("Wave");
        Label lblPhone = new Label("Phone");

        Label[] labels = {lblBib,lblLast,lblSex,lblTeam,lblEmail,
                lblCategory,lblFirst,lblBirth,lblWave,lblPhone};
        for (Label lbl : labels) {
            lbl.setFont(Font.font("System", FontWeight.NORMAL, 15));
        }

        // Susun Grid (2 kolom form)
        grid.addRow(0, lblBib, bibField, lblCategory, categoryCombo);
        grid.addRow(1, lblLast, lastNameField, lblFirst, firstNameField);
        grid.addRow(2, lblSex, sexCombo, lblBirth, birthDatePicker);
        grid.addRow(3, lblTeam, teamField, lblWave, waveCombo);
        grid.addRow(4, lblEmail, emailField, lblPhone, phoneField);

        // Tombol
        Button btnSave = new Button(" Save", new FontIcon("fas-save"));
        btnSave.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-pref-height: 35px; -fx-pref-width: 85px; -fx-font-size: 14px; -fx-icon-size: 18;");
        btnSave.setOnAction(e -> {
            FormData data = new FormData();
            data.bib = bibField.getText();
            data.firstName = firstNameField.getText();
            data.lastName = lastNameField.getText();
            data.sex = sexCombo.getValue();
            data.email = emailField.getText();
            data.phone = phoneField.getText();
            data.team = teamField.getText();
            data.category = categoryCombo.getValue();
            data.wave = waveCombo.getValue();
            data.birthDate = birthDatePicker.getValue();

            if (onSave != null) onSave.accept(data);
            stage.close();
        });

        Button btnCancel = new Button(" Cancel", new FontIcon("fas-times"));
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-pref-height: 35px; -fx-pref-width: 85px;");
        btnCancel.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox(10, btnSave, btnCancel);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(0, 20, 15, 20)); // atas, kanan, bawah, kiri

        VBox root = new VBox(12, grid, buttonBox);
        root.setPadding(new Insets(2)); // padding keseluruhan
        root.setStyle("-fx-background-color: #f4f4f4;");

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
