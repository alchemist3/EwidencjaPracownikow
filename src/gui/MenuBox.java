package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import workers.User;

import java.util.ArrayList;
import java.util.List;

public class MenuBox {
    static public List<User> users = new ArrayList();


    public static void display(Stage primaryStage) {
        Stage window;
        window = primaryStage;
        window.setTitle("Ewidencja pracowników");
        window.setMinWidth(400);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //db url label
        Label dbUrlLabel = new Label("Database url:");
        GridPane.setConstraints(dbUrlLabel, 0, 0);

        //db url input
        TextField dbUrlInput = new TextField("jdbc:mysql://localhost:3306/ewidencja_pracownikow");
        dbUrlInput.setMinSize(300, 10);
        GridPane.setConstraints(dbUrlInput, 1, 0);

        //username label
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 1);

        //username input
        TextField usernameInput = new TextField("student");
        dbUrlInput.setMinSize(300, 10);
        GridPane.setConstraints(usernameInput, 1, 1);

        //Pass label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 2);

        //Pass input
        TextField passInput = new TextField("student");
        GridPane.setConstraints(passInput, 1, 2);

        //Menu label
        Label menuLabel = new Label("Menu:");
        GridPane.setConstraints(menuLabel, 1, 4);

        String[] loginData = {dbUrlInput.getText(), usernameInput.getText(), passInput.getText()};

        //Worker List button
        Button workerListButton = new Button("Lista pracowników");
        workerListButton.setMinWidth(220);
        workerListButton.setOnAction(event -> WorkerListBox.display("Lista pracowników", loginData));
        GridPane.setConstraints(workerListButton, 1, 5);

        //add worker button
        Button addWorkerButton = new Button("Dodaj pracownika");
        addWorkerButton.setMinWidth(220);
        addWorkerButton.setOnAction(event -> AddWorkerBox.display("Dodaj pracownika", loginData));
        GridPane.setConstraints(addWorkerButton, 1, 6);

        //update worker button
        Button updateWorkerButton = new Button("Edytuj dane pracownika");
        updateWorkerButton.setMinWidth(220);
        updateWorkerButton.setOnAction(event -> UpdateWorkerBox.display("Edytuj dane pracownika", loginData));
        GridPane.setConstraints(updateWorkerButton, 1, 7);

        //delete worker button
        Button deleteWorkerButton = new Button("Usuń pracownika");
        deleteWorkerButton.setMinWidth(220);
        deleteWorkerButton.setOnAction(event -> DeleteWorkerBox.display("Usuń pracownika", loginData));
        GridPane.setConstraints(deleteWorkerButton, 1, 8);

        //download data button
        Button downloadDataButton = new Button("Pobierz dane z sieci");
        downloadDataButton.setMinWidth(220);
        downloadDataButton.setOnAction(event -> DownloadDataBox.display("Pobierz dane z sieci", loginData));
        GridPane.setConstraints(downloadDataButton, 1, 9);

        //authorisation data button
        Button authorisationDataButton = new Button("Zarządzaj uprawnieniami transferu");
        authorisationDataButton.setMinWidth(220);
        authorisationDataButton.setOnAction(event -> AuthorisationDataBox.display("Zarządzaj uprawnieniami transferu", loginData));
        GridPane.setConstraints(authorisationDataButton, 1, 10);


        grid.getChildren().addAll(dbUrlLabel, dbUrlInput, usernameLabel, usernameInput, passLabel, passInput, menuLabel,
                workerListButton, addWorkerButton, updateWorkerButton, deleteWorkerButton,downloadDataButton, authorisationDataButton);

        Scene scene = new Scene(grid, 400, 350);

        window.setScene(scene);
        window.show();

    }
}
