package gui;

import client.ValidatorClient;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import logic.*;
import server.Validator;
import workers.User;
import workers.WorkerList;
import xmlbinding.XMLBinding;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DownloadDataBox{


    public static void display(String title, String[] loginData) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label menuNameLabel = new Label(title);
        GridPane.setConstraints(menuNameLabel, 0, 0);

        Label addressLabel = new Label("Adres:");
        GridPane.setConstraints(addressLabel, 0, 3);
        Label portLabel = new Label("Port:");
        GridPane.setConstraints(portLabel, 0, 4);
        TextField addressTextField = new TextField("localhost");
        GridPane.setConstraints(addressTextField, 2, 3);
        TextField portTextField = new TextField("1234");
        GridPane.setConstraints(portTextField, 2, 4);
        Label userLabel = new Label("Użytkownik:");
        GridPane.setConstraints(userLabel, 0, 5);

        ChoiceBox<String> userChoiceBox = new ChoiceBox<>();
        for (User user:MenuBox.users){
            userChoiceBox.getItems().add(user.getUsername());
        }
        GridPane.setConstraints(userChoiceBox,2,5);

        Button closeButton = new Button("Powrót");
        GridPane.setConstraints(closeButton, 0, 11);
        closeButton.setOnAction(event -> window.close());

        Button downloadDataButton = new Button("Pobierz dane");
        GridPane.setConstraints(downloadDataButton,2,11);
        downloadDataButton.setOnAction(event -> {

            try {
                List<User> result = MenuBox.users.stream()
                        .filter(item -> item.getUsername().equals(userChoiceBox.getValue()))
                        .collect(Collectors.toList());
                String password = result.get(0).getPassword();

                ValidatorClient.run(userChoiceBox.getValue(),password,addressTextField.getText(),portTextField.getText());
                WorkerList workers = XMLBinding.getDataFromXml ("D:\\response.xml");
                Logic.addWorkers(workers,loginData);

                File file = new File("D:\\response.xml");
                file.delete();


            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setContentText("Błąd z pobraniem danych");
                alert.showAndWait();

            }






        });




        grid.getChildren().addAll(downloadDataButton, userChoiceBox, userLabel, addressLabel, portLabel,
                addressTextField, portTextField, menuNameLabel,closeButton);

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
}