package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import workers.Manager;
import workers.Tradesman;
import workers.Worker;
import logic.*;
import java.util.List;



public class WorkerListBox {

    static int counter;

    public static void display(String title, String[] loginData) {

        counter = 0;

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

        Label idLabel = new Label("Identyfikator:");
        GridPane.setConstraints(idLabel, 0, 2);
        Label nameLabel = new Label("Imię:");
        GridPane.setConstraints(nameLabel, 0, 3);
        Label surnameLabel = new Label("Nazwisko:");
        GridPane.setConstraints(surnameLabel, 0, 4);
        Label salaryLabel = new Label("Wynagrodzenie:");
        GridPane.setConstraints(salaryLabel, 0, 5);
        Label positionLabel = new Label("Stanowisko:");
        GridPane.setConstraints(positionLabel, 0, 6);
        Label phoneNumberLabel = new Label("Numer telefonu:");
        GridPane.setConstraints(phoneNumberLabel, 0, 7);

        Button closeButton = new Button("Powrót");
        GridPane.setConstraints(closeButton, 0, 11);
        closeButton.setOnAction(event -> window.close());

        Label idDataLabel = new Label();
        GridPane.setConstraints(idDataLabel, 1, 2);
        Label nameDataLabel = new Label();
        GridPane.setConstraints(nameDataLabel, 1, 3);
        Label surnameDataLabel = new Label();
        GridPane.setConstraints(surnameDataLabel, 1, 4);
        Label salaryDataLabel = new Label();
        GridPane.setConstraints(salaryDataLabel, 1, 5);
        Label positionDataLabel = new Label();
        GridPane.setConstraints(positionDataLabel, 1, 6);
        Label phoneNumberDataLabel = new Label();
        GridPane.setConstraints(phoneNumberDataLabel, 1, 7);

        Label blankLabel1 = new Label("");
        GridPane.setConstraints(blankLabel1, 0, 8);
        Label blankLabel2 = new Label("");
        GridPane.setConstraints(blankLabel2, 0, 9);
        Label blankLabel3 = new Label("");
        GridPane.setConstraints(blankLabel3, 0, 10);

        Label blankLabel4 = new Label("");
        GridPane.setConstraints(blankLabel4, 1, 8);
        Label blankLabel5 = new Label("");
        GridPane.setConstraints(blankLabel5, 1, 9);
        Label blankLabel6 = new Label("");
        GridPane.setConstraints(blankLabel6, 1, 10);


        List<Worker> workersList = Logic.getWorkers(loginData);

        int length = workersList.size();


        if (workersList.get(counter) instanceof Manager) {
            blankLabel1.setText("Dodatek służbowy:");
            blankLabel2.setText("Karta służbowa:");
            blankLabel3.setText("Limit kosztów/miesiąc:");

            Manager manager = (Manager) workersList.get(counter);

            blankLabel4.setText(Integer.toString(manager.getBonus()));
            blankLabel5.setText(manager.getCard());
            blankLabel6.setText(Integer.toString(manager.getCostLimit()));

        } else if (workersList.get(counter) instanceof Tradesman){
            blankLabel1.setText("Prowizja:");
            blankLabel2.setText("Limit prowizji/miesiąc:");
            blankLabel3.setText("");

            Tradesman tradesman = (Tradesman) workersList.get(counter);

            blankLabel4.setText(Integer.toString(tradesman.getProvision()));
            blankLabel5.setText(Integer.toString(tradesman.getProvisionLimit()));
            blankLabel6.setText("");
        }

        idDataLabel.setText(Integer.toString(workersList.get(counter).getId()));
        nameDataLabel.setText(workersList.get(counter).getName());
        surnameDataLabel.setText(workersList.get(counter).getSurname());
        salaryDataLabel.setText(Integer.toString(workersList.get(counter).getSalary()));
        positionDataLabel.setText(workersList.get(counter).getPosition());
        phoneNumberDataLabel.setText(workersList.get(counter).getPhoneNumber());

        Button nextWorkerButton = new Button("Następny");
        GridPane.setConstraints(nextWorkerButton, 1, 11);
        nextWorkerButton.setOnAction(event -> {
            counter++;
            if (counter >= length) {
                counter = 0;
            }

            if (workersList.get(counter) instanceof Manager) {
                blankLabel1.setText("Dodatek służbowy:");
                blankLabel2.setText("Karta służbowa:");
                blankLabel3.setText("Limit kosztów/miesiąc:");

                Manager manager = (Manager) workersList.get(counter);

                idDataLabel.setText(Integer.toString(manager.getId()));
                nameDataLabel.setText(manager.getName());
                surnameDataLabel.setText(manager.getSurname());
                salaryDataLabel.setText(Integer.toString(manager.getSalary()));
                positionDataLabel.setText(manager.getPosition());
                phoneNumberDataLabel.setText(manager.getPhoneNumber());
                blankLabel4.setText(Integer.toString(manager.getBonus()));
                blankLabel5.setText(manager.getCard());
                blankLabel6.setText(Integer.toString(manager.getCostLimit()));

            } else if (workersList.get(counter) instanceof Tradesman){
                blankLabel1.setText("Prowizja:");
                blankLabel2.setText("Limit prowizji/miesiąc:");
                blankLabel3.setText("");

                Tradesman tradesman = (Tradesman) workersList.get(counter);

                idDataLabel.setText(Integer.toString(tradesman.getId()));
                nameDataLabel.setText(tradesman.getName());
                surnameDataLabel.setText(tradesman.getSurname());
                salaryDataLabel.setText(Integer.toString(tradesman.getSalary()));
                positionDataLabel.setText(tradesman.getPosition());
                phoneNumberDataLabel.setText(tradesman.getPhoneNumber());
                blankLabel4.setText(Integer.toString(tradesman.getProvision()));
                blankLabel5.setText(Integer.toString(tradesman.getProvisionLimit()));
                blankLabel6.setText("");
            }
        });

        grid.getChildren().addAll(idLabel, menuNameLabel, nameLabel, surnameLabel, salaryLabel, positionLabel,
                phoneNumberLabel, closeButton, nextWorkerButton, idDataLabel, nameDataLabel, surnameDataLabel,
                salaryDataLabel, phoneNumberDataLabel, positionDataLabel, blankLabel1, blankLabel2, blankLabel3,
                blankLabel4, blankLabel5,blankLabel6);
        Scene scene = new Scene(grid, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}

