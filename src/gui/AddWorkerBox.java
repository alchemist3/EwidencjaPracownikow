package gui;

import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import logic.*;

public class AddWorkerBox {

    static String position = "Dyrektor";

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


        Label nameLabel = new Label("Imię:");
        GridPane.setConstraints(nameLabel, 0, 3);
        Label surnameLabel = new Label("Nazwisko:");
        GridPane.setConstraints(surnameLabel, 0, 4);
        Label salaryLabel = new Label("Wynagrodzenie:");
        GridPane.setConstraints(salaryLabel, 0, 5);

        Label phoneNumberLabel = new Label("Numer telefonu:");
        GridPane.setConstraints(phoneNumberLabel, 0, 6);

        Button closeButton = new Button("Powrót");
        GridPane.setConstraints(closeButton, 0, 11);
        closeButton.setOnAction(event -> window.close());


        TextField nameTextField = new TextField();
        GridPane.setConstraints(nameTextField, 1, 3);
        TextField surnameTextField = new TextField();
        GridPane.setConstraints(surnameTextField, 1, 4);
        TextField salaryTextField = new TextField();
        GridPane.setConstraints(salaryTextField, 1, 5);

        TextField phoneNumberTextField = new TextField();
        GridPane.setConstraints(phoneNumberTextField, 1, 6);

        Label blankLabel1 = new Label("Dodatek służbowy:");
        GridPane.setConstraints(blankLabel1, 0, 7);
        Label blankLabel2 = new Label("Karta służbowa:");
        GridPane.setConstraints(blankLabel2, 0, 8);
        Label blankLabel3 = new Label("Limit kosztów/miesiąc:");
        GridPane.setConstraints(blankLabel3, 0, 9);

        TextField blankTextField1 = new TextField();
        GridPane.setConstraints(blankTextField1, 1, 7);
        TextField blankTextField2 = new TextField("");
        GridPane.setConstraints(blankTextField2, 1, 8);
        TextField blankTextField3 = new TextField("");
        GridPane.setConstraints(blankTextField3, 1, 9);

        Button workerDirectorButton = new Button("Dyrektor");
        GridPane.setConstraints(workerDirectorButton, 0, 1);
        workerDirectorButton.setOnAction(event -> {
            blankLabel1.setText("Dodatek służbowy:");
            blankLabel2.setText("Karta służbowa:");
            blankLabel3.setText("Limit kosztów/miesiąc:");
            blankTextField3.setVisible(true);
            position = "Dyrektor";
        });

        Button workerTraderButton = new Button("Handlowiec");
        GridPane.setConstraints(workerTraderButton, 1, 1);
        workerTraderButton.setOnAction(event -> {

            Logic.getWorker(loginData,100);

            blankLabel1.setText("Prowizja:");
            blankLabel2.setText("Limit prowizji/miesiąć:");
            blankLabel3.setText("");
            blankTextField3.setVisible(false);
            position = "Handlowiec";
        });


        Button addWorkerButton = new Button("Zapisz");
        GridPane.setConstraints(addWorkerButton, 1, 11);
        addWorkerButton.setOnAction(event -> {
            String[] inputData = {nameTextField.getText(), surnameTextField.getText(), salaryTextField.getText(),
                    position, phoneNumberTextField.getText(), blankTextField1.getText(), blankTextField2.getText(),
                    blankTextField3.getText()};
            boolean result = Logic.addWorker(loginData, inputData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            if (result) {
                alert.setContentText("Dodano pracownika");
            } else {
                alert.setContentText("Wystąpił błąd w trakcie dodawania pracownika!");
            }
            nameTextField.setText("");
            surnameTextField.setText("");
            salaryTextField.setText("");
            phoneNumberTextField.setText("");
            blankTextField1.setText("");
            blankTextField2.setText("");
            blankTextField3.setText("");

            alert.showAndWait();
        });


        grid.getChildren().addAll(menuNameLabel, nameLabel, surnameLabel, salaryLabel, phoneNumberLabel, closeButton,
                addWorkerButton, nameTextField, surnameTextField, salaryTextField, phoneNumberTextField, blankLabel1,
                blankLabel2, blankLabel3, blankTextField1, blankTextField2, blankTextField3, workerDirectorButton,
                workerTraderButton);

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
}
