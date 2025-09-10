package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

public class Timing {

    public Parent getView() {
        BorderPane root = new BorderPane();

        VBox center = new VBox(10);
        center.setPadding(new Insets(10));
        root.setCenter(center);
        VBox.setVgrow(center, Priority.ALWAYS);

        HBox topRow = new HBox(10);
        HBox.setHgrow(topRow, Priority.ALWAYS);

        VBox bibBox = new VBox(10);
        bibBox.setPadding(new Insets(16));
        bibBox.setFillWidth(true);
        HBox.setHgrow(bibBox, Priority.SOMETIMES);

        Label bibTitle = new Label("Bib #");
        bibTitle.setFont(Font.font("Arial", 20));

        TextField bibField = new TextField();
        bibField.setPromptText("BIB Number");
        bibField.setAlignment(Pos.CENTER);
        bibField.setFont(Font.font("Arial", 24));
        bibField.setMaxWidth(Double.MAX_VALUE);

        HBox statsRow = new HBox(16);

        VBox statsLabels = new VBox(8);
        statsLabels.setAlignment(Pos.CENTER_LEFT);
        Label lblTagsPerSec = new Label("Tags / Second:");
        lblTagsPerSec.setFont(Font.font("Arial", 18));
        Label lblTagRead = new Label("Tag Read:");
        lblTagRead.setFont(Font.font("Arial", 18));
        statsLabels.getChildren().addAll(lblTagsPerSec, lblTagRead);

        VBox statsValues = new VBox(8);
        statsValues.setAlignment(Pos.CENTER_LEFT);
        Label valTagsPerSec = new Label("0");
        valTagsPerSec.setFont(Font.font("Arial", 18));
        Label valTagRead = new Label("0");
        valTagRead.setFont(Font.font("Arial", 18));
        statsValues.getChildren().addAll(valTagsPerSec, valTagRead);

        statsRow.getChildren().addAll(statsLabels, statsValues);
        bibBox.getChildren().addAll(bibTitle, bibField, statsRow);

        VBox startUploadBox = new VBox(10);
        startUploadBox.setFillWidth(true);
        startUploadBox.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(startUploadBox, Priority.ALWAYS);

        HBox buttonsRow = new HBox(12);
        buttonsRow.setAlignment(Pos.CENTER);
        buttonsRow.setPadding(new Insets(0, 10, 0, 10));

        FontIcon startIcon = new FontIcon(FontAwesomeSolid.PLAY);
        startIcon.setIconSize(18);
        startIcon.setIconColor(Color.WHITE);
        Button btnStart = new Button("Start", startIcon);
        btnStart.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnStart.setFont(Font.font("Arial", 18));
        btnStart.setPadding(new Insets(10, 20, 10, 20));

        FontIcon uploadIcon = new FontIcon(FontAwesomeSolid.UPLOAD);
        uploadIcon.setIconSize(18);
        uploadIcon.setIconColor(Color.WHITE);
        Button btnUpload = new Button("Upload", uploadIcon);
        btnUpload.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnUpload.setFont(Font.font("Arial", 18));
        btnUpload.setPadding(new Insets(10, 20, 10, 20));

        buttonsRow.getChildren().addAll(btnStart, btnUpload);

        VBox rectCol = new VBox(10);
        rectCol.setFillWidth(true);
        rectCol.setAlignment(Pos.TOP_CENTER);

        Rectangle r1 = new Rectangle();
        r1.setArcWidth(5);
        r1.setArcHeight(5);
        r1.setFill(Color.WHITE);
        r1.setStroke(Color.BLACK);
        r1.setStrokeWidth(1);
        r1.widthProperty().bind(startUploadBox.widthProperty().subtract(40));
        r1.setHeight(48);

        Rectangle r2 = new Rectangle();
        r2.setArcWidth(5);
        r2.setArcHeight(5);
        r2.setFill(Color.WHITE);
        r2.setStroke(Color.BLACK);
        r2.setStrokeWidth(1);
        r2.widthProperty().bind(startUploadBox.widthProperty().subtract(40));
        r2.setHeight(140);

        rectCol.getChildren().addAll(r1, r2);
        startUploadBox.getChildren().addAll(buttonsRow, rectCol);

        TableView<Void> tvLeft = new TableView<>();
        tvLeft.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvLeft.setPlaceholder(new Label("No data"));
        HBox.setHgrow(tvLeft, Priority.SOMETIMES);

        TableColumn<Void, String> c_l1 = new TableColumn<>("#");
        TableColumn<Void, String> c_l2 = new TableColumn<>("Name");
        TableColumn<Void, String> c_l3 = new TableColumn<>("Model");
        TableColumn<Void, String> c_l4 = new TableColumn<>("Connected?");
        tvLeft.getColumns().addAll(c_l1, c_l2, c_l3, c_l4);

        TableView<Void> tvRight = new TableView<>();
        tvRight.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvRight.setPlaceholder(new Label("No data"));
        HBox.setHgrow(tvRight, Priority.SOMETIMES);

        TableColumn<Void, String> r_c1 = new TableColumn<>("#");
        TableColumn<Void, String> r_c2 = new TableColumn<>("Unique Tag");
        TableColumn<Void, String> r_c3 = new TableColumn<>("Total Reads");
        TableColumn<Void, String> r_c4 = new TableColumn<>("Peak");
        TableColumn<Void, String> r_c5 = new TableColumn<>("Con");
        TableColumn<Void, String> r_c6 = new TableColumn<>("Power");
        tvRight.getColumns().addAll(r_c1, r_c2, r_c3, r_c4, r_c5, r_c6);

        topRow.getChildren().addAll(bibBox, startUploadBox, tvLeft, tvRight);

        SplitPane split = new SplitPane();
        split.setDividerPositions(0.22);
        VBox.setVgrow(split, Priority.ALWAYS);

        Pane leftPane = new Pane();
        ScrollBar sb = new ScrollBar();
        sb.setOrientation(javafx.geometry.Orientation.VERTICAL);
        sb.prefHeightProperty().bind(leftPane.heightProperty().subtract(2));
        sb.setVisibleAmount(90.0);
        sb.layoutXProperty().bind(leftPane.widthProperty().subtract(sb.widthProperty()));
        sb.setLayoutY(0);
        leftPane.getChildren().add(sb);

        BorderPane right = new BorderPane();

        VBox topRightBox = new VBox(10);
        topRightBox.setPadding(new Insets(6, 10, 6, 10));

        HBox filtersRow = new HBox(10);
        filtersRow.setAlignment(Pos.CENTER_LEFT);

        HBox filtersLeft = new HBox(10);
        filtersLeft.setAlignment(Pos.CENTER_LEFT);

        FontIcon searchIcon = new FontIcon(FontAwesomeSolid.SEARCH);
        searchIcon.setIconSize(16);

        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Search...");
        tfSearch.setPrefWidth(160);

        ChoiceBox<String> cb1 = new ChoiceBox<>();
        ChoiceBox<String> cb2 = new ChoiceBox<>();

        filtersLeft.getChildren().addAll(searchIcon, tfSearch, cb1, cb2);

        HBox filtersRight = new HBox(10);
        filtersRight.setAlignment(Pos.CENTER_RIGHT);

        FontIcon refreshIcon = new FontIcon(FontAwesomeSolid.SYNC_ALT); 
        refreshIcon.setIconSize(16);
        Button btnRefresh = new Button("Refresh", refreshIcon);
        btnRefresh.setFont(Font.font("Arial", 14));

        FontIcon stopIcon = new FontIcon(FontAwesomeSolid.STOP);
        stopIcon.setIconSize(14);
        stopIcon.setIconColor(Color.WHITE);
        Button btnII = new Button("Stop", stopIcon);
        btnII.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnII.setFont(Font.font("Arial", 12));

        Region spacerFR = new Region();
        HBox.setHgrow(spacerFR, Priority.ALWAYS);

        filtersRight.getChildren().addAll(spacerFR, btnRefresh, btnII);
        filtersRow.getChildren().addAll(filtersLeft, filtersRight);

        TableView<Void> tvMain = new TableView<>();
        tvMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvMain.setPlaceholder(new Label("No data"));
        VBox.setVgrow(tvMain, Priority.ALWAYS);

        TableColumn<Void, String> t1 = new TableColumn<>("BIB");
        TableColumn<Void, String> t2 = new TableColumn<>("Read");
        TableColumn<Void, String> t3 = new TableColumn<>("Name");
        TableColumn<Void, String> t4 = new TableColumn<>("Category");

        TableColumn<Void, String> t5 = new TableColumn<>();
        MenuButton mbTime = new MenuButton("Time");
        mbTime.getItems().addAll(new MenuItem("Action 1"), new MenuItem("Action 2"));
        t5.setGraphic(mbTime);

        TableColumn<Void, String> t6 = new TableColumn<>("Split");
        TableColumn<Void, String> t7 = new TableColumn<>("Locked");
        TableColumn<Void, String> t8 = new TableColumn<>("Upload");

        tvMain.getColumns().addAll(t1, t2, t3, t4, t5, t6, t7, t8);

        topRightBox.getChildren().addAll(filtersRow, tvMain);
        right.setTop(topRightBox);

        Pagination pagination = new Pagination(1, 0);
        pagination.setMaxPageIndicatorCount(7);
        right.setBottom(pagination);

        split.getItems().addAll(leftPane, right);
        SplitPane.setResizableWithParent(right, true);
        SplitPane.setResizableWithParent(leftPane, true);

        center.getChildren().addAll(topRow, split);
        VBox.setVgrow(split, Priority.ALWAYS);

        return root;
    }
}
