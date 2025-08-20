package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Setting extends BorderPane {

    public Setting() {
        // TabPane utama
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // ================= TAB: EVENT =================
        Tab tabEvent = new Tab("Event");
        tabEvent.setContent(buildEventTab());

        // ================= TAB: WAVE ==================
        Tab tabWave = new Tab("Wave");
        tabWave.setContent(buildWaveTab());

        // ================= TAB: CATEGORY ==============
        Tab tabCategory = new Tab("Category");
        tabCategory.setContent(buildCategoryTab());

        // ================= TAB: SPLIT =================
        Tab tabSplit = new Tab("Split");
        tabSplit.setContent(buildSplitTab());

        tabPane.getTabs().addAll(tabEvent, tabWave, tabCategory, tabSplit);

        // Taruh di CENTER supaya mengikuti ukuran window
        setCenter(tabPane);
    }

    // ------------------- EVENT TAB -------------------
    private AnchorPane buildEventTab() {
        AnchorPane anchor = new AnchorPane();
        VBox wrapper = new VBox(10);
        wrapper.setPadding(new Insets(10));
        wrapper.setPrefSize(600, 471);

        // ===== Section: Event =====
        VBox sectionEvent = new VBox(5);
        sectionEvent.setAlignment(Pos.TOP_CENTER);

        Label lblEventTitle = new Label("Event");
        lblEventTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        VBox.setMargin(lblEventTitle, new Insets(7, 0, 7, 0));

        HBox row1 = new HBox(10);
        HBox.setHgrow(row1, Priority.ALWAYS);

        VBox vCode = createVBoxWithLabelAndField("Code");
        HBox.setHgrow(vCode, Priority.ALWAYS);

        VBox vName = createVBoxWithLabelAndField("Name");
        HBox.setHgrow(vName, Priority.ALWAYS);

        VBox vBackup = createVBoxWithLabelAndField("Backup URL");
        HBox.setHgrow(vBackup, Priority.ALWAYS);

        row1.getChildren().addAll(vCode, vName, vBackup);

        HBox row2 = new HBox(10);
        HBox.setHgrow(row2, Priority.ALWAYS);

        VBox vDate = createVBoxWithLabelAndField("Date");
        HBox.setHgrow(vDate, Priority.ALWAYS);

        VBox vDistance = createVBoxWithLabelAndField("Distance");
        HBox.setHgrow(vDistance, Priority.ALWAYS);

        VBox vLaps = createVBoxWithLabelAndField("Laps");
        HBox.setHgrow(vLaps, Priority.ALWAYS);

        row2.getChildren().addAll(vDate, vDistance, vLaps);

        sectionEvent.getChildren().addAll(lblEventTitle, row1, row2);

        // ===== Section: Address =====
        VBox sectionAddress = new VBox(5);
        sectionAddress.setAlignment(Pos.TOP_CENTER);

        Label lblAddressTitle = new Label("Address");
        lblAddressTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        VBox.setMargin(lblAddressTitle, new Insets(7, 0, 7, 0));

        HBox rowA1 = new HBox(10);
        HBox.setHgrow(rowA1, Priority.ALWAYS);

        VBox vCountry = createVBoxWithLabelAndField("Country");
        HBox.setHgrow(vCountry, Priority.ALWAYS);

        VBox vState = createVBoxWithLabelAndField("State");
        HBox.setHgrow(vState, Priority.ALWAYS);

        rowA1.getChildren().addAll(vCountry, vState);

        HBox rowA2 = new HBox(10);
        HBox.setHgrow(rowA2, Priority.ALWAYS);

        VBox vCity = createVBoxWithLabelAndField("City");
        HBox.setHgrow(vCity, Priority.ALWAYS);

        VBox vStreet = createVBoxWithLabelAndField("Street");
        HBox.setHgrow(vStreet, Priority.ALWAYS);

        VBox vZip = createVBoxWithLabelAndField("Zip Code");
        HBox.setHgrow(vZip, Priority.ALWAYS);

        rowA2.getChildren().addAll(vCity, vStreet, vZip);

        sectionAddress.getChildren().addAll(lblAddressTitle, rowA1, rowA2);

        // ===== Section: Race Director =====
        VBox sectionRD = new VBox(5);
        sectionRD.setAlignment(Pos.TOP_CENTER);

        Label lblRDTitle = new Label("Race Director");
        lblRDTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        VBox.setMargin(lblRDTitle, new Insets(7, 0, 7, 0));

        HBox rowRD1 = new HBox(10);
        HBox.setHgrow(rowRD1, Priority.ALWAYS);

        VBox vRDName = createVBoxWithLabelAndField("Name");
        HBox.setHgrow(vRDName, Priority.ALWAYS);

        VBox vRDEmail = createVBoxWithLabelAndField("Email");
        HBox.setHgrow(vRDEmail, Priority.ALWAYS);

        VBox vRDPhone = createVBoxWithLabelAndField("Phone");
        HBox.setHgrow(vRDPhone, Priority.ALWAYS);

        rowRD1.getChildren().addAll(vRDName, vRDEmail, vRDPhone);

        HBox rowRD2 = new HBox(10);
        rowRD2.setAlignment(Pos.CENTER_LEFT);

        Button btnDeleteDataTime = new Button("Delete Data Time");
        btnDeleteDataTime.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnDeleteDataTime.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnSave.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        HBox.setHgrow(btnDeleteDataTime, Priority.ALWAYS);
        HBox.setHgrow(btnSave, Priority.ALWAYS);

        rowRD2.getChildren().addAll(btnDeleteDataTime, btnSave);

        sectionRD.getChildren().addAll(lblRDTitle, rowRD1, rowRD2);

        wrapper.getChildren().addAll(sectionEvent, sectionAddress, sectionRD);

        AnchorPane.setTopAnchor(wrapper, 0.0);
        AnchorPane.setBottomAnchor(wrapper, 0.0);
        AnchorPane.setLeftAnchor(wrapper, 0.0);
        AnchorPane.setRightAnchor(wrapper, 0.0);

        anchor.getChildren().add(wrapper);
        return anchor;
    }

    // ------------------- WAVE TAB -------------------
    private AnchorPane buildWaveTab() {
        return buildTableTab("#", "Name");
    }

    // ------------------- CATEGORY TAB -------------------
    private AnchorPane buildCategoryTab() {
        return buildTableTab("#", "Name", "Wave");
    }

    // ------------------- SPLIT TAB -------------------
    private AnchorPane buildSplitTab() {
        return buildTableTab("#", "Name", "Type", "Distance", "Gap", "Category");
    }

    // ------------------- UTILITY: TABLE TAB -------------------
    private AnchorPane buildTableTab(String... columns) {
        AnchorPane anchor = new AnchorPane();
        VBox wrapper = new VBox(5);
        wrapper.setPadding(new Insets(10));

        HBox buttons = new HBox(5);
        buttons.setAlignment(Pos.CENTER_LEFT);

        Button btnAdd = new Button("+ Add");
        btnAdd.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnAdd.setFont(Font.font("Arial", FontWeight.BOLD, 10));

        Button btnEdit = new Button("Edit");
        btnEdit.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        btnEdit.setFont(Font.font("Arial", FontWeight.BOLD, 10));

        Button btnDelete = new Button("Delete");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnDelete.setFont(Font.font("Arial", FontWeight.BOLD, 10));

        buttons.getChildren().addAll(btnAdd, btnEdit, btnDelete);

        TableView<Void> table = new TableView<>();
        for (String colName : columns) {
            TableColumn<Void, String> col = new TableColumn<>(colName);
            table.getColumns().add(col);
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(table, Priority.ALWAYS);

        wrapper.getChildren().addAll(buttons, table);

        AnchorPane.setTopAnchor(wrapper, 0.0);
        AnchorPane.setBottomAnchor(wrapper, 0.0);
        AnchorPane.setLeftAnchor(wrapper, 0.0);
        AnchorPane.setRightAnchor(wrapper, 0.0);

        anchor.getChildren().add(wrapper);
        return anchor;
    }

    // ------------------- UTILITY: CREATE VBOX WITH LABEL + FIELD -------------------
    private VBox createVBoxWithLabelAndField(String labelText) {
        VBox vbox = new VBox(3);
        Label label = new Label(labelText);
        label.setFont(Font.font(11));

        TextField textField = new TextField();
        textField.setMaxWidth(Double.MAX_VALUE); // responsif

        HBox hbox = new HBox(textField);
        HBox.setHgrow(textField, Priority.ALWAYS);
        vbox.getChildren().addAll(label, hbox);

        return vbox;
    }
}
