package gui;

import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import logic.*;

import java.util.Map;

public class UpdateWorkerBox {


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
        Label positionLabel = new Label("Stanowisko");
        GridPane.setConstraints(positionLabel, 0, 6);

        Label phoneNumberLabel = new Label("Numer telefonu:");
        GridPane.setConstraints(phoneNumberLabel, 0, 7);

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
        GridPane.setConstraints(phoneNumberTextField, 1, 7);

        Label blankLabel1 = new Label("Dodatek służbowy:");
        GridPane.setConstraints(blankLabel1, 0, 8);
        Label blankLabel2 = new Label("Karta służbowa:");
        GridPane.setConstraints(blankLabel2, 0, 9);
        Label blankLabel3 = new Label("Limit kosztów/miesiąc:");
        GridPane.setConstraints(blankLabel3, 0, 10);

        TextField blankTextField1 = new TextField();
        GridPane.setConstraints(blankTextField1, 1, 8);
        TextField blankTextField2 = new TextField("");
        GridPane.setConstraints(blankTextField2, 1, 9);
        TextField blankTextField3 = new TextField("");
        GridPane.setConstraints(blankTextField3, 1, 10);

        Label workerIdLabel= new Label("Podaj identyfikator:");
        GridPane.setConstraints(workerIdLabel, 0, 1);

        TextField workerIdTextField = new TextField();
        GridPane.setConstraints(workerIdTextField, 1, 1);

        Button showWorkerButton = new Button("Pokaż dane");
        GridPane.setConstraints(showWorkerButton,3,1);

        ChoiceBox<String> positionChoiceBox = new ChoiceBox<>();
        positionChoiceBox.getItems().addAll("Handlowiec","Dyrektor");
        GridPane.setConstraints(positionChoiceBox,1,6);
        positionChoiceBox.setOnAction(event -> {
            if (positionChoiceBox.getValue() == "Dyrektor") {
                blankLabel1.setText("Dodatek służbowy:");
                blankLabel2.setText("Karta służbowa:");
                blankLabel3.setText("Limit kosztów/miesiąc:");
                blankTextField3.setVisible(true);

                blankTextField1.setText("");
                blankTextField2.setText("");
                blankTextField3.setText("");

            } else {
                blankLabel1.setText("Prowizja:");
                blankLabel2.setText("Limit prowizji/miesiąc:");
                blankLabel3.setText("");
                blankTextField3.setVisible(false);

                blankTextField1.setText("");
                blankTextField2.setText("");
                blankTextField3.setText("");
            }
        });

        showWorkerButton.setOnAction(event -> {
            Map<String, String> workerData = Logic.getWorker(loginData,Integer.parseInt(workerIdTextField.getText()));

            nameTextField.setText(workerData.get("imie"));
            surnameTextField.setText(workerData.get("nazwisko"));
            salaryTextField.setText(workerData.get("wynagrodzenie"));
            positionChoiceBox.setValue(workerData.get("stanowisko"));
            phoneNumberTextField.setText(workerData.get("telefon"));

            if (workerData.get("stanowisko").equals("Dyrektor")) {
                blankLabel1.setText("Dodatek służbowy:");
                blankLabel2.setText("Karta służbowa:");
                blankLabel3.setText("Limit kosztów/miesiąc:");
                blankTextField1.setText(workerData.get("dodatek_sluzbowy"));
                blankTextField2.setText(workerData.get("karta_sluzbowa"));
                blankTextField3.setText(workerData.get("limit_kosztow"));
                blankTextField3.setVisible(true);
            } else {
                blankLabel1.setText("Prowizja:");
                blankLabel2.setText("Limit prowizji/miesiąc:");
                blankLabel3.setText("");
                blankTextField1.setText(workerData.get("prowizja"));
                blankTextField2.setText(workerData.get("limit_prowizji"));
                blankTextField3.setText(workerData.get(""));
                blankTextField3.setVisible(false);
            }
        });

        Button editWorkerbutton = new Button("Zaktualizuj dane");
        GridPane.setConstraints(editWorkerbutton, 1, 11);
        editWorkerbutton.setOnAction(event -> {
            String[] inputData = {workerIdTextField.getText(), nameTextField.getText(), surnameTextField.getText(),
                    salaryTextField.getText(), positionChoiceBox.getValue(), phoneNumberTextField.getText(),
                    blankTextField1.getText(), blankTextField2.getText(), blankTextField3.getText()};

            boolean result = Logic.editWorker(loginData, inputData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            if (result) {
                alert.setContentText("Zaktualizowano dane pracownika");
            } else {
                alert.setContentText("Wystąpił błąd w trakcie aktualizacji danych pracownika!");
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

        grid.getChildren().addAll(menuNameLabel, nameLabel, surnameLabel,positionLabel, salaryLabel, phoneNumberLabel, closeButton,
                editWorkerbutton, nameTextField, surnameTextField, salaryTextField, phoneNumberTextField, blankLabel1,
                blankLabel2, blankLabel3, blankTextField1, blankTextField2, blankTextField3, workerIdLabel,
                workerIdTextField,showWorkerButton,positionChoiceBox);

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
}