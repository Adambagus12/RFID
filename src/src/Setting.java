package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Setting extends BorderPane {

    public Setting() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabEvent = new Tab("Event");
        tabEvent.setContent(buildEventTab());

        Tab tabWave = new Tab("Wave");
        tabWave.setContent(buildWaveTab());

        Tab tabCategory = new Tab("Category");
        tabCategory.setContent(buildCategoryTab());

        Tab tabSplit = new Tab("Split");
        tabSplit.setContent(buildSplitTab());

        tabPane.getTabs().addAll(tabEvent, tabWave, tabCategory, tabSplit);

        setCenter(tabPane);
    }

    public Parent getView() {
        return this;
    }

    private AnchorPane buildEventTab() {
        AnchorPane anchor = new AnchorPane();
        VBox wrapper = new VBox(20);
        wrapper.setPadding(new Insets(15));
        VBox.setVgrow(wrapper, Priority.ALWAYS);

        VBox sectionEvent = new VBox(10);
        sectionEvent.setAlignment(Pos.TOP_LEFT);

        Label lblEventTitle = new Label("Event");
        lblEventTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        lblEventTitle.setAlignment(Pos.CENTER);
        lblEventTitle.setMaxWidth(Double.MAX_VALUE);

        HBox row1 = new HBox(15,
                createCodeField("Code", 250, 42, "Adam"),
                createVBoxWithLabelAndField("Name", 280, 42, "Event Name"),
                createVBoxWithLabelAndField("Backup URL", 890, 42, "Event Backup URL"));

        HBox row2 = new HBox(15,
                createVBoxWithDatePicker("Date", 500, 42),
                createVBoxWithLabelAndField("Distance", 450, 42, "Event Distance"),
                createVBoxWithLabelAndField("Laps", 470, 42, "Event Laps"));

        sectionEvent.getChildren().addAll(lblEventTitle, row1, row2);

        VBox sectionAddress = new VBox(10);
        sectionAddress.setAlignment(Pos.TOP_LEFT);

        Label lblAddressTitle = new Label("Address");
        lblAddressTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        lblAddressTitle.setAlignment(Pos.CENTER);
        lblAddressTitle.setMaxWidth(Double.MAX_VALUE);

        HBox rowA1 = new HBox(15,
                createVBoxWithLabelAndField("Country", 710, 42, "Event Country"),
                createVBoxWithLabelAndField("State", 710, 42, "Event State"));

        HBox rowA2 = new HBox(15,
                createVBoxWithLabelAndField("City", 473, 42, "Event City"),
                createVBoxWithLabelAndField("Street", 473, 42, "Event Street"),
                createVBoxWithLabelAndField("Zip Code", 473, 42, "Event Zip Code"));

        sectionAddress.getChildren().addAll(lblAddressTitle, rowA1, rowA2);

        VBox sectionRD = new VBox(10);
        sectionRD.setAlignment(Pos.TOP_LEFT);

        Label lblRDTitle = new Label("Race Director");
        lblRDTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        lblRDTitle.setAlignment(Pos.CENTER);
        lblRDTitle.setMaxWidth(Double.MAX_VALUE);

        HBox rowRD1 = new HBox(15,
                createVBoxWithLabelAndField("Name", 473, 42, "Event Race Director Name"),
                createVBoxWithLabelAndField("Email", 473, 42, "Event Race Director Email"),
                createVBoxWithLabelAndField("Phone", 473, 42, "Event Race Director Phone"));

        HBox rowRD2 = new HBox(20);
        rowRD2.setAlignment(Pos.CENTER);
        rowRD2.setPadding(new Insets(20, 0, 0, 0));

        Button btnDeleteDataTime = new Button("Delete Data Time", new FontIcon("fas-trash"));
        btnDeleteDataTime.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
        btnDeleteDataTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnDeleteDataTime.setPrefWidth(220);
        btnDeleteDataTime.setPrefHeight(35);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnSave = new Button("Save", new FontIcon("fas-save"));
        btnSave.setStyle("-fx-background-color: #388e3c; -fx-text-fill: white;");
        btnSave.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnSave.setPrefWidth(150);
        btnSave.setPrefHeight(35);

        rowRD2.getChildren().addAll(btnDeleteDataTime, spacer, btnSave);

        sectionRD.getChildren().addAll(lblRDTitle, rowRD1, rowRD2);

        wrapper.getChildren().addAll(sectionEvent, sectionAddress, sectionRD);

        AnchorPane.setTopAnchor(wrapper, 0.0);
        AnchorPane.setBottomAnchor(wrapper, 0.0);
        AnchorPane.setLeftAnchor(wrapper, 0.0);
        AnchorPane.setRightAnchor(wrapper, 0.0);

        anchor.getChildren().add(wrapper);
        return anchor;
    }

    private AnchorPane buildWaveTab() {
        AnchorPane anchor = new AnchorPane();
        VBox wrapper = new VBox(15);
        wrapper.setPadding(new Insets(15));
        VBox.setVgrow(wrapper, Priority.ALWAYS);

        Label lblTitle = new Label("Wave");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_LEFT);

        Button btnAddWave = new Button("Add", new FontIcon("fas-plus"));
        btnAddWave.setStyle("-fx-background-color: #388e3c; -fx-text-fill: white;");
        btnAddWave.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Button btnEditWave = new Button("Edit", new FontIcon("fas-edit"));
        btnEditWave.setStyle("-fx-background-color: #f57c00; -fx-text-fill: white;");
        btnEditWave.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Button btnDeleteWave = new Button("Delete", new FontIcon("fas-trash"));
        btnDeleteWave.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
        btnDeleteWave.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        buttons.getChildren().addAll(btnAddWave, btnEditWave, btnDeleteWave);

        TableView<Void> table = new TableView<>();
        TableColumn<Void, String> colNo = new TableColumn<>("#");
        colNo.setMinWidth(120);

        TableColumn<Void, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(200);

        table.getColumns().addAll(colNo, colName);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        VBox.setVgrow(table, Priority.ALWAYS);
        wrapper.getChildren().addAll(lblTitle, buttons, table);

        AnchorPane.setTopAnchor(wrapper, 0.0);
        AnchorPane.setBottomAnchor(wrapper, 0.0);
        AnchorPane.setLeftAnchor(wrapper, 0.0);
        AnchorPane.setRightAnchor(wrapper, 0.0);
        anchor.getChildren().add(wrapper);

        btnAddWave.setOnAction(e -> {
            AddWave dialog = new AddWave();
            String newName = dialog.showAndWait();
            if (newName != null && !newName.isEmpty()) {
                System.out.println("Wave ditambahkan: " + newName);
            }
        });

        btnDeleteWave.setOnAction(e -> {
            DeleteWave dialog = new DeleteWave();
            boolean confirmed = dialog.showAndWait();
            if (confirmed) {
                System.out.println("Wave dihapus");
            }
        });

        return anchor;
    }

    private AnchorPane buildCategoryTab() {
        AnchorPane anchor = new AnchorPane();
        VBox wrapper = new VBox(15);
        wrapper.setPadding(new Insets(15));
        VBox.setVgrow(wrapper, Priority.ALWAYS);

        Label lblTitle = new Label("Category");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_LEFT);

        Button btnAddCategory = new Button("Add", new FontIcon("fas-plus"));
        btnAddCategory.setStyle("-fx-background-color: #388e3c; -fx-text-fill: white;");
        btnAddCategory.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Button btnEditCategory = new Button("Edit", new FontIcon("fas-edit"));
        btnEditCategory.setStyle("-fx-background-color: #f57c00; -fx-text-fill: white;");
        btnEditCategory.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Button btnDeleteCategory = new Button("Delete", new FontIcon("fas-trash"));
        btnDeleteCategory.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
        btnDeleteCategory.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        buttons.getChildren().addAll(btnAddCategory, btnEditCategory, btnDeleteCategory);

        TableView<Void> table = new TableView<>();
        TableColumn<Void, String> colNo = new TableColumn<>("#");
        colNo.setMinWidth(120);

        TableColumn<Void, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(200);

        TableColumn<Void, String> colWave = new TableColumn<>("Wave");
        colWave.setMinWidth(200);

        table.getColumns().addAll(colNo, colName, colWave);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        VBox.setVgrow(table, Priority.ALWAYS);
        wrapper.getChildren().addAll(lblTitle, buttons, table);

        AnchorPane.setTopAnchor(wrapper, 0.0);
        AnchorPane.setBottomAnchor(wrapper, 0.0);
        AnchorPane.setLeftAnchor(wrapper, 0.0);
        AnchorPane.setRightAnchor(wrapper, 0.0);

        anchor.getChildren().add(wrapper);

        btnAddCategory.setOnAction(e -> {
            AddCategory dialog = new AddCategory();
            String result = dialog.showAndWait();
            if (result != null && !result.isEmpty()) {
                System.out.println("Category ditambahkan: " + result);
            }
        });

        btnDeleteCategory.setOnAction(e -> {
            DeleteCategory dialog = new DeleteCategory();
            boolean confirmed = dialog.showAndWait();
            if (confirmed) {
                System.out.println("Category dihapus");
            }
        });

        return anchor;
    }

    private AnchorPane buildSplitTab() {
        AnchorPane anchor = new AnchorPane();
        VBox wrapper = new VBox(15);
        wrapper.setPadding(new Insets(15));
        VBox.setVgrow(wrapper, Priority.ALWAYS);

        Label lblTitle = new Label("Split");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_LEFT);

        Button btnAddSplit = new Button("Add", new FontIcon("fas-plus"));
        btnAddSplit.setStyle("-fx-background-color: #388e3c; -fx-text-fill: white;");
        btnAddSplit.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Button btnEditSplit = new Button("Edit", new FontIcon("fas-edit"));
        btnEditSplit.setStyle("-fx-background-color: #f57c00; -fx-text-fill: white;");
        btnEditSplit.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Button btnDeleteSplit = new Button("Delete", new FontIcon("fas-trash"));
        btnDeleteSplit.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
        btnDeleteSplit.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        buttons.getChildren().addAll(btnAddSplit, btnEditSplit, btnDeleteSplit);

        TableView<Void> table = new TableView<>();
        TableColumn<Void, String> colNo = new TableColumn<>("#");
        colNo.setMinWidth(120);

        TableColumn<Void, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(200);

        TableColumn<Void, String> colType = new TableColumn<>("Type");
        colType.setMinWidth(200);

        TableColumn<Void, String> colDistance = new TableColumn<>("Distance");
        colDistance.setMinWidth(200);

        TableColumn<Void, String> colGap = new TableColumn<>("Gap");
        colGap.setMinWidth(200);

        TableColumn<Void, String> colCategory = new TableColumn<>("Category");
        colCategory.setMinWidth(200);

        table.getColumns().addAll(colNo, colName, colType, colDistance, colGap, colCategory);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        VBox.setVgrow(table, Priority.ALWAYS);
        wrapper.getChildren().addAll(lblTitle, buttons, table);

        AnchorPane.setTopAnchor(wrapper, 0.0);
        AnchorPane.setBottomAnchor(wrapper, 0.0);
        AnchorPane.setLeftAnchor(wrapper, 0.0);
        AnchorPane.setRightAnchor(wrapper, 0.0);

        anchor.getChildren().add(wrapper);

        btnAddSplit.setOnAction(e -> {
            AddSplit dialog = new AddSplit();
            String result = dialog.showAndWait();
            if (result != null && !result.isEmpty()) {
                System.out.println("Split ditambahkan: " + result);
            }
        });

        btnDeleteSplit.setOnAction(e -> {
            DeleteSplit dialog = new DeleteSplit();
            boolean confirmed = dialog.showAndWait();
            if (confirmed) {
                System.out.println("Split dihapus");
            }
        });

        return anchor;
    }

    private VBox createVBoxWithLabelAndField(String labelText, double width, double height, String promptText) {
        VBox vbox = new VBox(5);
        Label label = new Label(labelText);
        label.setFont(Font.font(15));

        TextField textField = new TextField();
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        textField.setPromptText(promptText);

        vbox.getChildren().addAll(label, textField);
        return vbox;
    }

    private VBox createVBoxWithDatePicker(String labelText, double width, double height) {
        VBox vbox = new VBox(5);
        Label label = new Label(labelText);
        label.setFont(Font.font(15));

        DatePicker datePicker = new DatePicker();
        datePicker.setPrefWidth(width);
        datePicker.setPrefHeight(height);

        vbox.getChildren().addAll(label, datePicker);
        return vbox;
    }

    private VBox createCodeField(String labelText, double width, double height, String userName) {
        VBox vbox = new VBox(5);

        Label label = new Label(labelText);
        label.setFont(Font.font(15));

        String code = generateCodeFromName(userName);

        TextField textField = new TextField(code);
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        textField.setEditable(false);

        Button btnCopy = new Button();
        btnCopy.setGraphic(new FontIcon("fas-copy"));
        btnCopy.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(textField.getText());
            clipboard.setContent(content);
        });

        HBox hbox = new HBox(textField, btnCopy);
        HBox.setHgrow(textField, Priority.ALWAYS);
        btnCopy.setPrefHeight(height);

        vbox.getChildren().addAll(label, hbox);
        return vbox;
    }

    private String generateCodeFromName(String name) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(name.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(String.format("%02x", hash[i]));
            }
            return sb.toString().substring(0, 8);
        } catch (Exception e) {
            return "UserCode";
        }
    }
}
