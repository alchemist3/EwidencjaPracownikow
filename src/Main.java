

import gui.MenuBox;
import javafx.application.Application;
import javafx.stage.Stage;
import server.WebService;

public class Main extends Application {

    public static void main(String[] args) {
        WebService ws = new WebService();
        ws.setDaemon(true);
        ws.start();


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MenuBox.display(primaryStage);
    }
}
