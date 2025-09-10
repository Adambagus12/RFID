package src;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import org.kordamp.ikonli.javafx.FontIcon;

public class CloseEvent {

    // 🔧 Helper untuk ambil nama file tanpa ekstensi apapun
    private String stripExtension(String filename) {
        if (filename == null) return null;
        int dotIndex = filename.indexOf('.');
        return (dotIndex > 0) ? filename.substring(0, dotIndex) : filename;
    }

    public Parent getView() {
        BorderPane root = new BorderPane();
        root.setPrefSize(900, 600);

        VBox topBox = new VBox();
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(15);
        topBox.setAlignment(Pos.TOP_LEFT);

        Label welcomeLabel = new Label("Welcome");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        VBox.setMargin(welcomeLabel, new Insets(10, 0, 0, 10));

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(centerBox, Priority.ALWAYS);

        Label label1 = new Label("Optimize Your Race Event");
        label1.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label label2 = new Label("Using This App");
        label2.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button addEventBtn = new Button("Add Event", new FontIcon("fas-folder-plus"));
        Button loadEventBtn = new Button("Load Event", new FontIcon("fas-download"));

        addEventBtn.setPrefSize(120, 40);
        loadEventBtn.setPrefSize(120, 40);

        addEventBtn.setOnAction(e -> showAddEventDialog(root.getScene().getWindow()));
        loadEventBtn.setOnAction(e -> showLoadEventDialog(root.getScene().getWindow()));

        buttonBox.getChildren().addAll(addEventBtn, loadEventBtn);

        centerBox.getChildren().addAll(label1, label2, buttonBox);

        Button findReadersBtn = new Button("Find Readers");
        findReadersBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        VBox.setMargin(findReadersBtn, new Insets(0, 0, 5, 10));

        TableView<Object> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No content in table"));
        VBox.setVgrow(table, Priority.ALWAYS);

        String[] cols = {"Ping", "Connected ?", "Name", "Model", "IP", "Port", "Antenna"};
        for (String colName : cols) {
            TableColumn<Object, String> col = new TableColumn<>(colName);
            table.getColumns().add(col);
        }

        topBox.getChildren().addAll(welcomeLabel, centerBox, findReadersBtn, table);

        root.setCenter(topBox);

        Navigator.setEventActive(false);
        Navigator.setActiveEventName("");

        return root;
    }

    public void showAddEventDialog(Window owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Create New Event");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Create New Event");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextField txtEventName = new TextField();
        txtEventName.setPromptText("Event Name");
        txtEventName.setPrefWidth(400);

        TextField txtPath = new TextField();
        txtPath.setPromptText("DB Path");
        txtPath.setPrefWidth(400);

        // 🔹 Icon putih untuk tombol Browse Path
        FontIcon pathIcon = new FontIcon("fas-folder-open");
        pathIcon.setIconColor(Color.WHITE);
        Button btnBrowse = new Button("Path", pathIcon);
        btnBrowse.setPrefWidth(80);
        btnBrowse.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white;");
        btnBrowse.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Database Location");
            chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Database Files", "*.db", "*.sqlite", "*.db3")
            );
            File file = chooser.showSaveDialog(dialog);
            if (file != null) {
                txtPath.setText(file.getAbsolutePath());
            }
        });

        HBox pathBox = new HBox(5, txtPath, btnBrowse);
        pathBox.setAlignment(Pos.CENTER);

        // 🔹 Save button with white icon
        FontIcon saveIcon = new FontIcon("fas-save");
        saveIcon.setIconColor(Color.WHITE);
        Button btnSave = new Button("Save", saveIcon);
        btnSave.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 14px;");
        btnSave.setPrefWidth(100);

        // 🔹 Cancel button with white icon
        FontIcon cancelIcon = new FontIcon("fas-times");
        cancelIcon.setIconColor(Color.WHITE);
        Button btnCancel = new Button("Cancel", cancelIcon);
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px;");
        btnCancel.setPrefWidth(100);

        btnSave.setOnAction(e -> {
            String eventName = txtEventName.getText().trim();
            String path = txtPath.getText().trim();
            if (!eventName.isEmpty() && !path.isEmpty()) {
                String cleanName = stripExtension(eventName);
                Navigator.setActiveEventName(cleanName);
                Navigator.setEventActive(true);
                dialog.close();
                Navigator.navigate("Home");
            } else {
                new Alert(Alert.AlertType.WARNING, "Event name and path cannot be empty!", ButtonType.OK).showAndWait();
            }
        });

        btnCancel.setOnAction(e -> dialog.close());

        HBox buttonBox = new HBox(15, btnSave, btnCancel);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, txtEventName, pathBox, buttonBox);

        Scene scene = new Scene(root, 500, 220);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    public void showLoadEventDialog(Window owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Load Event");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Load Your Event");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField txtPath = new TextField();
        txtPath.setPromptText("Pick Your DB File");
        txtPath.setPrefWidth(400);

        // 🔹 Icon putih untuk tombol Pick File
        FontIcon browseIcon = new FontIcon("fas-folder-open");
        browseIcon.setIconColor(Color.WHITE);
        Button btnBrowse = new Button("Pick File", browseIcon);
        btnBrowse.setPrefWidth(100);
        btnBrowse.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white;");
        btnBrowse.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select DB File");
            chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Database Files", "*.db", "*.sqlite", "*.db3")
            );
            File file = chooser.showOpenDialog(dialog);
            if (file != null) {
                txtPath.setText(file.getAbsolutePath());
            }
        });

        HBox pathBox = new HBox(5, txtPath, btnBrowse);
        pathBox.setAlignment(Pos.CENTER);
        pathBox.setMaxWidth(520);

        // 🔹 Load button with white icon
        FontIcon loadIcon = new FontIcon("fas-download");
        loadIcon.setIconColor(Color.WHITE);
        Button btnLoad = new Button("Load", loadIcon);
        btnLoad.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 14px;");
        btnLoad.setPrefWidth(100);

        // 🔹 Cancel button with white icon
        FontIcon cancelIcon = new FontIcon("fas-times");
        cancelIcon.setIconColor(Color.WHITE);
        Button btnCancel = new Button("Cancel", cancelIcon);
        btnCancel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px;");
        btnCancel.setPrefWidth(100);

        btnLoad.setOnAction(e -> {
            String path = txtPath.getText().trim();
            if (!path.isEmpty()) {
                File file = new File(path);
                String fileName = stripExtension(file.getName());

                Navigator.setActiveEventName(fileName);
                Navigator.setEventActive(true);
                dialog.close();
                Navigator.navigate("Home");
            }
        });

        btnCancel.setOnAction(e -> dialog.close());

        HBox buttonBox = new HBox(15, btnLoad, btnCancel);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, pathBox, buttonBox);

        Scene scene = new Scene(root, 500, 200);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
