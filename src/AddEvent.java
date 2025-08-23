package src;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class AddEvent extends Dialog<String> {

    private TextField eventNameField;

    public AddEvent() {
        setTitle("Add Event");
        initStyle(StageStyle.UTILITY);

        // Tombol OK / Cancel
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Layout Grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        eventNameField = new TextField();
        eventNameField.setPromptText("Event name");

        grid.add(new Label("Event Name:"), 0, 0);
        grid.add(eventNameField, 1, 0);

        getDialogPane().setContent(grid);

        // Validasi input kosong
        getDialogPane().lookupButton(okButtonType).disableProperty()
            .bind(eventNameField.textProperty().isEmpty());

        // Result converter
        setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return eventNameField.getText().trim();
            }
            return null;
        });
    }
}
