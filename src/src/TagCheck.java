package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class TagCheck extends BorderPane {

    private Stage dialogStage;

    public TagCheck(Stage owner) {
        // === Konstanta global untuk alignment ===
        final double LABEL_W   = 90;   // lebar label sama semua
        final double INPUT_LEFT = 10;  // margin kiri input lebih dekat ke label

        // VBox utama di tengah
        VBox vbox = new VBox(10); // spacing antar baris
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefHeight(200.0);
        vbox.setPrefWidth(100.0);

        // ================== HBox 1: HTML File ==================
        HBox hbox1 = new HBox();
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox1.setPrefWidth(500.0);
        hbox1.setPadding(new Insets(0, 0, 0, 10));

        Label lblHtml = new Label("HTML File");
        lblHtml.setPrefWidth(LABEL_W);

        TextField tfHtml = new TextField();
        tfHtml.setPrefHeight(25.0);
        tfHtml.setPrefWidth(315.0);
        HBox.setMargin(tfHtml, new Insets(0, 0, 0, INPUT_LEFT));

        Button btnPickFile = new Button("Pick File");

        hbox1.getChildren().addAll(lblHtml, tfHtml, btnPickFile);

        // ================== HBox 2: Reader ==================
        HBox hbox2 = new HBox();
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setPrefWidth(500.0);
        hbox2.setPadding(new Insets(0, 0, 0, 10));

        Label lblReader = new Label("Reader");
        lblReader.setPrefWidth(LABEL_W);

        ChoiceBox<String> cbReader = new ChoiceBox<>();
        cbReader.setPrefHeight(25.0);
        cbReader.setPrefWidth(373.0);
        HBox.setMargin(cbReader, new Insets(0, 0, 0, INPUT_LEFT));

        hbox2.getChildren().addAll(lblReader, cbReader);

        // ================== HBox 3: Antenna ==================
        HBox hbox3 = new HBox();
        hbox3.setAlignment(Pos.CENTER_LEFT);
        hbox3.setPrefWidth(500.0);
        hbox3.setPadding(new Insets(0, 0, 0, 10));

        Label lblAntenna = new Label("Antenna");
        lblAntenna.setPrefWidth(LABEL_W);

        TextField tfAntenna = new TextField();
        tfAntenna.setPrefHeight(127.0);
        tfAntenna.setPrefWidth(373.0); // samakan dengan yg atas
        HBox.setMargin(tfAntenna, new Insets(0, 0, 0, INPUT_LEFT));

        hbox3.getChildren().addAll(lblAntenna, tfAntenna);

        // ================== HBox 4: Buttons ==================
        HBox hbox4 = new HBox(10); // spacing antar tombol
        hbox4.setAlignment(Pos.CENTER_RIGHT); // semua tombol di kanan
        hbox4.setPrefWidth(500.0);
        hbox4.setPadding(new Insets(0, 35, 0, 10));

        Button btnOpen = new Button("Open");
        btnOpen.setGraphic(new FontIcon("fas-door-open"));

        Button btnCancel = new Button("Cancel");
        btnCancel.setGraphic(new FontIcon("fas-times"));

        // urutannya: Open lalu Cancel
        hbox4.getChildren().addAll(btnOpen, btnCancel);

        // ================== Tambahkan ke VBox ==================
        vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4);

        // Taruh VBox ke center BorderPane
        this.setCenter(vbox);

        // === Buat Stage popup ===
        dialogStage = new Stage();
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Tag Check");

        Scene scene = new Scene(this, 520, 300);
        dialogStage.setScene(scene);

        // Tutup popup kalau klik Cancel
        btnCancel.setOnAction(e -> dialogStage.close());
    }

    // method untuk munculin popup
    public void showDialog() {
        dialogStage.showAndWait();
    }
}
