package src;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class LoadEvent extends Dialog<String> {

    private ListView<String> eventList;

    public LoadEvent() {
        setTitle("Load Event");
        initStyle(StageStyle.UTILITY);

        // Tombol OK / Cancel
        ButtonType okButtonType = new ButtonType("Load", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Dummy list event
        eventList = new ListView<>();
        eventList.setItems(FXCollections.observableArrayList(
            "Event A", "Event B", "Event C"
        ));
        eventList.setPrefHeight(150);

        VBox box = new VBox(10, new Label("Choose event to load:"), eventList);
        box.setPadding(new Insets(15));

        getDialogPane().setContent(box);

        // Validasi: tidak bisa OK kalau belum pilih
        getDialogPane().lookupButton(okButtonType).disableProperty()
            .bind(eventList.getSelectionModel().selectedItemProperty().isNull());

        // Result converter
        setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return eventList.getSelectionModel().getSelectedItem();
            }
            return null;
        });
    }
}
