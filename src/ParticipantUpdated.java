package src;

import java.util.*;
import java.util.stream.Collectors;
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

    public static class ParticipantRecord {
        private final IntegerProperty no = new SimpleIntegerProperty();
        private final StringProperty  bib = new SimpleStringProperty();
        private final StringProperty  name = new SimpleStringProperty();
        private final StringProperty  sex = new SimpleStringProperty();
        private final StringProperty  email = new SimpleStringProperty();
        private final StringProperty  phone = new SimpleStringProperty();
        private final StringProperty  team = new SimpleStringProperty();
        private final StringProperty  category = new SimpleStringProperty();
        private final StringProperty  wave = new SimpleStringProperty();
        private final StringProperty  status = new SimpleStringProperty();

        public ParticipantRecord(int no, String bib, String name, String sex, String email, String phone,
                                 String team, String category, String wave, String status) {
            this.no.set(no);
            this.bib.set(bib);
            this.name.set(name);
            this.sex.set(sex);
            this.email.set(email);
            this.phone.set(phone);
            this.team.set(team);
            this.category.set(category);
            this.wave.set(wave);
            this.status.set(status);
        }
        public IntegerProperty noProperty(){ return no; }
        public StringProperty  bibProperty(){ return bib; }
        public StringProperty  nameProperty(){ return name; }
        public StringProperty  sexProperty(){ return sex; }
        public StringProperty  emailProperty(){ return email; }
        public StringProperty  phoneProperty(){ return phone; }
        public StringProperty  teamProperty(){ return team; }
        public StringProperty  categoryProperty(){ return category; }
        public StringProperty  waveProperty(){ return wave; }
        public StringProperty  statusProperty(){ return status; }
    }

    public Parent getView() {
        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(10));
        mainContent.setStyle("-fx-background-color: #f3f3f3;");

        Label title = new Label("Data Participant");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        HBox actionBar = new HBox(10);
        actionBar.setAlignment(Pos.CENTER_LEFT);

        Button addBtn = new Button("+ Add");
        Button editBtn = new Button("Edit");
        Button deleteBtn = new Button("Delete");
        Button deleteAllBtn = new Button("Delete All");
        deleteAllBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        Region spacerAction = new Region();
        HBox.setHgrow(spacerAction, Priority.ALWAYS);

        Label writeTag = new Label("Write Tag");
        Label importBtn = new Label("Import");
        Label downloadBtn = new Label("Download");

        for (Label l : new Label[]{writeTag, importBtn, downloadBtn}) {
            l.setStyle("-fx-border-color: #ccc; -fx-padding: 4 8;");
        }

        HBox rightActions = new HBox(10, writeTag, importBtn, downloadBtn);
        rightActions.setAlignment(Pos.CENTER_RIGHT);

        actionBar.getChildren().addAll(addBtn, editBtn, deleteBtn, deleteAllBtn, spacerAction, rightActions);
        HBox filterRow = new HBox(10);
        filterRow.setAlignment(Pos.CENTER_LEFT);

        TextField searchField = new TextField();
        searchField.setPromptText("Name & Bib Filter");
        searchField.setPrefWidth(250);

        ComboBox<String> categoryFilter = new ComboBox<>();
        categoryFilter.setPromptText("Category Filter");

        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setPromptText("Status Filter");

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        Label countLabel = new Label("Number of Participants: 0");
        countLabel.setFont(Font.font("Arial", 10));

        filterRow.getChildren().addAll(searchField, categoryFilter, statusFilter, spacer2, countLabel);

        TableView<ParticipantRecord> table = new TableView<>();
        table.setPlaceholder(new Label("No content in table"));
        table.setPrefHeight(600);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ParticipantRecord, Number> cNo = new TableColumn<>("#");
        TableColumn<ParticipantRecord, String> cBib = new TableColumn<>("Bib");
        TableColumn<ParticipantRecord, String> cName = new TableColumn<>("Name");
        TableColumn<ParticipantRecord, String> cSex = new TableColumn<>("Sex");
        TableColumn<ParticipantRecord, String> cEmail = new TableColumn<>("Email");
        TableColumn<ParticipantRecord, String> cPhone = new TableColumn<>("Phone");
        TableColumn<ParticipantRecord, String> cTeam = new TableColumn<>("Team");
        TableColumn<ParticipantRecord, String> cCategory = new TableColumn<>("Category");
        TableColumn<ParticipantRecord, String> cWave = new TableColumn<>("Wave");
        TableColumn<ParticipantRecord, String> cStatus = new TableColumn<>("Status");

        cNo.setCellValueFactory(d -> d.getValue().noProperty());
        cBib.setCellValueFactory(d -> d.getValue().bibProperty());
        cName.setCellValueFactory(d -> d.getValue().nameProperty());
        cSex.setCellValueFactory(d -> d.getValue().sexProperty());
        cEmail.setCellValueFactory(d -> d.getValue().emailProperty());
        cPhone.setCellValueFactory(d -> d.getValue().phoneProperty());
        cTeam.setCellValueFactory(d -> d.getValue().teamProperty());
        cCategory.setCellValueFactory(d -> d.getValue().categoryProperty());
        cWave.setCellValueFactory(d -> d.getValue().waveProperty());
        cStatus.setCellValueFactory(d -> d.getValue().statusProperty());

        table.getColumns().addAll(cNo, cBib, cName, cSex, cEmail, cPhone, cTeam, cCategory, cWave, cStatus);

        ObservableList<ParticipantRecord> master = FXCollections.observableArrayList();
        boolean addDummy = false; 
        if (addDummy) {
            master.addAll(
                new ParticipantRecord(1, "A001", "Ani", "F", "ani@mail.com", "0812", "Team X", "10K", "Wave 1", "Paid"),
                new ParticipantRecord(2, "B015", "Budi", "M", "budi@mail.com", "0813", "Team Y", "21K", "Wave 2", "Unpaid"),
                new ParticipantRecord(3, "C033", "Caca", "F", "caca@mail.com", "0814", "Team X", "5K", "Wave 1", "Paid")
            );
        }

        refreshFilterItems(master, categoryFilter, statusFilter);

        FilteredList<ParticipantRecord> filtered = new FilteredList<>(master, r -> true);
        searchField.textProperty().addListener((obs, old, v) -> applyPredicate(filtered, searchField, categoryFilter, statusFilter));
        categoryFilter.valueProperty().addListener((obs, o, v) -> applyPredicate(filtered, searchField, categoryFilter, statusFilter));
        statusFilter.valueProperty().addListener((obs, o, v) -> applyPredicate(filtered, searchField, categoryFilter, statusFilter));

        SortedList<ParticipantRecord> sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());

        Pagination pagination = new Pagination(1, 0);
        pagination.setMaxPageIndicatorCount(7);
        final int rowsPerPage = 25;

        pagination.setPageFactory(pageIndex -> {
            int from = pageIndex * rowsPerPage;
            int to = Math.min(from + rowsPerPage, sorted.size());
            if (from > to) {
                table.setItems(FXCollections.observableArrayList());
            } else {
                table.setItems(FXCollections.observableArrayList(sorted.subList(from, to)));
            }
            return new VBox(); 
        });

        ListChangeListener<ParticipantRecord> recalcPages = c -> {
            int pageCount = Math.max(1, (int) Math.ceil((double) sorted.size() / rowsPerPage));
            pagination.setPageCount(pageCount);
            int current = Math.min(pagination.getCurrentPageIndex(), pageCount - 1);
            pagination.setCurrentPageIndex(Math.max(current, 0));
        };
        sorted.addListener(recalcPages);
        recalcPages.onChanged(null);
        countLabel.textProperty().bind(Bindings.size(filtered).asString("Number of Participants: %d"));

        addBtn.setOnAction(e -> {
            int nextNo = master.size() + 1;
            master.add(new ParticipantRecord(nextNo, "BIB"+nextNo, "New Person "+nextNo, "M", "", "", "", "", "", "Unpaid"));
            renumber(master);
            refreshFilterItems(master, categoryFilter, statusFilter);
        });

        editBtn.setOnAction(e -> {
            ParticipantRecord sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) {
                new Alert(Alert.AlertType.INFORMATION, "Pilih satu baris untuk di-edit").showAndWait();
                return;
            }
            sel.statusProperty().set("Paid".equals(sel.statusProperty().get()) ? "Unpaid" : "Paid");
            table.refresh();
        });

        deleteBtn.setOnAction(e -> {
            ParticipantRecord sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) {
                new Alert(Alert.AlertType.INFORMATION, "Pilih satu baris untuk dihapus").showAndWait();
                return;
            }
            master.remove(sel);
            renumber(master);
            refreshFilterItems(master, categoryFilter, statusFilter);
        });

        deleteAllBtn.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete ALL participants?", ButtonType.OK, ButtonType.CANCEL);
            confirm.showAndWait().filter(btn -> btn == ButtonType.OK).ifPresent(btn -> {
                master.clear();
                refreshFilterItems(master, categoryFilter, statusFilter);
            });
        });

        mainContent.getChildren().addAll(title, actionBar, filterRow, table, pagination);
        return mainContent;
    }

    private static void applyPredicate(FilteredList<ParticipantRecord> filtered,
                                       TextField searchField,
                                       ComboBox<String> categoryFilter,
                                       ComboBox<String> statusFilter) {
        String q = Optional.ofNullable(searchField.getText()).orElse("").trim().toLowerCase();
        String cat = categoryFilter.getValue();
        String sts = statusFilter.getValue();

        filtered.setPredicate(rec -> {
            boolean matchSearch = q.isEmpty()
                    || (rec.nameProperty().get() != null && rec.nameProperty().get().toLowerCase().contains(q))
                    || (rec.bibProperty().get() != null && rec.bibProperty().get().toLowerCase().contains(q));
            boolean matchCat = (cat == null || cat.isEmpty()) || cat.equals(rec.categoryProperty().get());
            boolean matchSts = (sts == null || sts.isEmpty()) || sts.equals(rec.statusProperty().get());
            return matchSearch && matchCat && matchSts;
        });
    }

    private static void refreshFilterItems(ObservableList<ParticipantRecord> master,
                                           ComboBox<String> categoryFilter,
                                           ComboBox<String> statusFilter) {
        Set<String> cat = master.stream()
                .map(r -> r.categoryProperty().get())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toCollection(TreeSet::new));
        Set<String> sts = master.stream()
                .map(r -> r.statusProperty().get())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toCollection(TreeSet::new));
        categoryFilter.getItems().setAll(cat);
        statusFilter.getItems().setAll(sts);
    }

    private static void renumber(ObservableList<ParticipantRecord> master) {
        for (int i = 0; i < master.size(); i++) {
            master.get(i).noProperty().set(i + 1);
        }
    }
}
