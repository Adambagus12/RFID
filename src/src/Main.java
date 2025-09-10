package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane mainPane;

    public static void main(String[] args) {
        launch(args); 
    }

    @Override
    public void start(Stage stage) {
        HBox rootRow = new HBox();
        rootRow.setFillHeight(true);

        VBox sidebarHost = new VBox();
        sidebarHost.setPrefWidth(80);
        sidebarHost.setMinWidth(80);   
        sidebarHost.setMaxWidth(80);   
        HBox.setHgrow(sidebarHost, Priority.NEVER);

        mainPane = new BorderPane();
        mainPane.setPadding(new Insets(0, 0, 0, 0));
        HBox.setHgrow(mainPane, Priority.ALWAYS);

        Navigator.init(mainPane);
        Navigator.setHomeSupplier(Home::createMainContent);

        mainPane.setTop(TopBarFactory.createTopBar());
        TopBarFactory.setNavigator(Navigator::navigate);
        sidebarHost.getChildren().setAll(
            SidebarFactory.createSidebar(Navigator::navigate)
        );

        rootRow.getChildren().addAll(sidebarHost, mainPane);

        Scene scene = new Scene(rootRow, 1366, 768);
        stage.setTitle("Event");
        stage.setScene(scene);
        stage.show();
        Navigator.navigate("CloseEvent");
    }
}
