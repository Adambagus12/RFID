package src;

import java.util.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import org.kordamp.ikonli.javafx.FontIcon;

public class Participant {

    public Parent getView() {
        return createMainContent();
    }

    // Data model lengkap sesuai kolom tabel
    public static class ParticipantRecord {
        private final IntegerProperty no = new SimpleIntegerProperty();
        private final StringProperty bib = new SimpleStringProperty();
        private final StringProperty name = new SimpleStringProperty(); // Tambahkan property name
        private final StringProperty gender = new SimpleStringProperty();
        private final StringProperty email = new SimpleStringProperty();
        private final StringProperty phone = new SimpleStringProperty();
        private final StringProperty team = new SimpleStringProperty();
        private final StringProperty category = new SimpleStringProperty();
        private final StringProperty wave = new SimpleStringProperty();
        private final StringProperty status = new SimpleStringProperty();

        public ParticipantRecord(int no, String bib, String name, String gender, String email, String phone, String team, String category, String wave, String status) {
            this.no.set(no);
            this.bib.set(bib);
            this.name.set(name); // Inisialisasi name
            this.gender.set(gender);
            this.email.set(email);
            this.phone.set(phone);
            this.team.set(team);
            this.category.set(category);
            this.wave.set(wave);
            this.status.set(status);
        }

        public int getNo() { return no.get(); }
        public String getBib() { return bib.get(); }
        public String getName() { return name.get(); } // Tambahkan getter name
        public String getGender() { return gender.get(); }
        public String getEmail() { return email.get(); }
        public String getPhone() { return phone.get(); }
        public String getTeam() { return team.get(); }
        public String getCategory() { return category.get(); }
        public String getWave() { return wave.get(); }
        public String getStatus() { return status.get(); }

        public IntegerProperty noProperty() { return no; }
        public StringProperty bibProperty() { return bib; }
        public StringProperty nameProperty() { return name; } // Tambahkan property name
        public StringProperty genderProperty() { return gender; }
        public StringProperty emailProperty() { return email; }
        public StringProperty phoneProperty() { return phone; }
        public StringProperty teamProperty() { return team; }
        public StringProperty categoryProperty() { return category; }
        public StringProperty waveProperty() { return wave; }
        public StringProperty statusProperty() { return status; }
    }

    // Tampilan utama
    public static Parent createMainContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #f4f4f4;");

        Label title = new Label("Data Participant");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Tombol-tombol utama
        Button addButton = new Button("Add", new FontIcon("fas-plus"));
        Button editButton = new Button("Edit", new FontIcon("fas-edit"));
        Button deleteButton = new Button("Delete", new FontIcon("fas-trash"));
        Button deleteAllButton = new Button("Delete All", new FontIcon("fas-trash-alt"));
        deleteAllButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        Button writeTagButton = new Button("Write Tag", new FontIcon("fas-pen"));
        Button importButton = new Button("Import", new FontIcon("fas-file-import"));
        Button downloadButton = new Button("Download", new FontIcon("fas-download"));

        HBox buttonBar = new HBox(5, addButton, editButton, deleteButton, deleteAllButton, writeTagButton, importButton, downloadButton);
        buttonBar.setAlignment(Pos.CENTER_LEFT);

        // Filter dan info jumlah
        TextField filterField = new TextField();
        filterField.setPromptText("Name & Bib Filter");
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Category ...", "Category A", "Category B", "Category C");
        categoryBox.getSelectionModel().selectFirst();

        ComboBox<String> waveBox = new ComboBox<>();
        waveBox.getItems().addAll("Semua ...", "Wave 1", "Wave 2");
        waveBox.getSelectionModel().selectFirst();

        Label participantCount = new Label("Number of Participants : 0");

        HBox filterBar = new HBox(5, filterField, categoryBox, waveBox, participantCount);
        filterBar.setAlignment(Pos.CENTER_LEFT);

        // TableView dengan kolom lengkap
        TableView<ParticipantRecord> table = new TableView<>();
        table.setPlaceholder(new Label("No content in table"));

        TableColumn<ParticipantRecord, Number> noCol = new TableColumn<>("#");
        noCol.setCellValueFactory(data -> data.getValue().noProperty());

        TableColumn<ParticipantRecord, String> bibCol = new TableColumn<>("BIB");
        bibCol.setCellValueFactory(data -> data.getValue().bibProperty());

        TableColumn<ParticipantRecord, String> nameCol = new TableColumn<>("Name"); // Kolom Name
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<ParticipantRecord, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(data -> data.getValue().genderProperty());

        TableColumn<ParticipantRecord, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> data.getValue().emailProperty());

        TableColumn<ParticipantRecord, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(data -> data.getValue().phoneProperty());

        TableColumn<ParticipantRecord, String> teamCol = new TableColumn<>("Team");
        teamCol.setCellValueFactory(data -> data.getValue().teamProperty());

        TableColumn<ParticipantRecord, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data -> data.getValue().categoryProperty());

        TableColumn<ParticipantRecord, String> waveCol = new TableColumn<>("Wave");
        waveCol.setCellValueFactory(data -> data.getValue().waveProperty());

        TableColumn<ParticipantRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> data.getValue().statusProperty());

        table.getColumns().addAll(noCol, bibCol, nameCol, genderCol, emailCol, phoneCol, teamCol, categoryCol, waveCol, statusCol);

        // Sample data (kosong dulu)
        ObservableList<ParticipantRecord> data = FXCollections.observableArrayList();

        // Filtering dan sorting
        FilteredList<ParticipantRecord> filteredData = new FilteredList<>(data, p -> true);
        filterField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(participant -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lower = newVal.toLowerCase();
                return participant.getBib().toLowerCase().contains(lower) || participant.getName().toLowerCase().contains(lower);
            });
        });

        categoryBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(participant -> {
                if (newVal.equals("Category ...")) return true;
                return participant.getCategory().equals(newVal);
            });
        });

        waveBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(participant -> {
                if (newVal.equals("Semua ...")) return true;
                return participant.getWave().equals(newVal);
            });
        });

        SortedList<ParticipantRecord> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

        // Update jumlah peserta
        participantCount.textProperty().bind(Bindings.createStringBinding(
            () -> "Number of Participants : " + sortedData.size(), sortedData
        ));

        // Pagination
        Pagination pagination = new Pagination(1, 0);
        pagination.setPageFactory(pageIndex -> table);

        VBox.setVgrow(table, Priority.ALWAYS);
        root.getChildren().addAll(title, buttonBar, filterBar, table, pagination);
        return root;
    }
}