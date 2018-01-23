package gui;

import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import logic.*;

import java.util.Map;

public class DeleteWorkerBox {


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

        Label nameDataLabel = new Label();
        GridPane.setConstraints(nameDataLabel, 1, 3);
        Label surnameDataLabel = new Label();
        GridPane.setConstraints(surnameDataLabel, 1, 4);
        Label salaryDataLabel = new Label();
        GridPane.setConstraints(salaryDataLabel, 1, 5);
        Label positionDataLabel = new Label();
        GridPane.setConstraints(positionDataLabel,1,6);


        Label phoneNumberDataLabel = new Label();
        GridPane.setConstraints(phoneNumberDataLabel, 1, 7);

        Label blankLabel1 = new Label("Dodatek służbowy:");
        GridPane.setConstraints(blankLabel1, 0, 8);
        Label blankLabel2 = new Label("Karta służbowa:");
        GridPane.setConstraints(blankLabel2, 0, 9);
        Label blankLabel3 = new Label("Limit kosztów/miesiąc:");
        GridPane.setConstraints(blankLabel3, 0, 10);

        Label blankDataLabel1 = new Label();
        GridPane.setConstraints(blankDataLabel1, 1, 8);
        Label blankDataLabel2 = new Label();
        GridPane.setConstraints(blankDataLabel2, 1, 9);
        Label blankDataLabel3 = new Label();
        GridPane.setConstraints(blankDataLabel3, 1, 10);

        Label workerIdLabel= new Label("Podaj identyfikator:");
        GridPane.setConstraints(workerIdLabel, 0, 1);

        TextField workerIdTextField = new TextField();
        GridPane.setConstraints(workerIdTextField, 1, 1);

        Button showWorkerButton = new Button("Pokaż dane");
        GridPane.setConstraints(showWorkerButton,3,1);



        showWorkerButton.setOnAction(event -> {
            Map<String, String> workerData = Logic.getWorker(loginData,Integer.parseInt(workerIdTextField.getText()));

            nameDataLabel.setText(workerData.get("imie"));
            surnameDataLabel.setText(workerData.get("nazwisko"));
            salaryDataLabel.setText(workerData.get("wynagrodzenie"));
            positionDataLabel.setText(workerData.get("stanowisko"));
            phoneNumberDataLabel.setText(workerData.get("telefon"));



            if (workerData.get("stanowisko").equals("Dyrektor")) {
                blankLabel1.setText("Dodatek służbowy:");
                blankLabel2.setText("Karta służbowa:");
                blankLabel3.setText("Limit kosztów/miesiąc:");
                blankDataLabel1.setText(workerData.get("dodatek_sluzbowy"));
                blankDataLabel2.setText(workerData.get("karta_sluzbowa"));
                blankDataLabel3.setText(workerData.get("limit_kosztow"));
//                blankLabel3.setVisible(true);

            } else {
                blankLabel1.setText("Prowizja:");
                blankLabel2.setText("Limit prowizji/miesiąc:");
                blankLabel3.setText("");
                blankDataLabel1.setText(workerData.get("prowizja"));
                blankDataLabel2.setText(workerData.get("limit_prowizji"));
                blankDataLabel3.setText(workerData.get(""));
//                blankTextField3.setVisible(false);

            }

        });


        Button deleteWorkerbutton = new Button("Usuń pracownika");
        GridPane.setConstraints(deleteWorkerbutton, 1, 11);
        deleteWorkerbutton.setOnAction(event -> {
            String inputData = workerIdTextField.getText();

            boolean result = Logic.deleteWorker(loginData, inputData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            if (result) {
                alert.setContentText("Usunięto pracownika");
            } else {
                alert.setContentText("Wystąpił błąd w trakcie usuwania pracownika!");
            }
            nameDataLabel.setText("");
            surnameDataLabel.setText("");
            salaryDataLabel.setText("");
            positionDataLabel.setText("");
            phoneNumberDataLabel.setText("");
            blankDataLabel1.setText("");
            blankDataLabel2.setText("");
            blankDataLabel3.setText("");

            alert.showAndWait();
        });


        grid.getChildren().addAll(menuNameLabel, nameLabel, surnameLabel,positionLabel, salaryLabel, phoneNumberLabel, closeButton,
                nameDataLabel, surnameDataLabel, salaryDataLabel, phoneNumberDataLabel, blankLabel1,
                blankLabel2, blankLabel3, blankDataLabel1, blankDataLabel2, blankDataLabel3, workerIdLabel,
                workerIdTextField,showWorkerButton,positionDataLabel,deleteWorkerbutton);

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
}