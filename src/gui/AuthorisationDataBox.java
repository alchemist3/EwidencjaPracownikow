package gui;

        import javafx.geometry.Insets;
        import javafx.stage.*;
        import javafx.scene.*;
        import javafx.scene.layout.*;
        import javafx.scene.control.*;
        import logic.*;
        import workers.User;
        import java.util.List;
        import java.util.stream.Collectors;

        import java.util.Map;

public class AuthorisationDataBox{


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

        Label usernameLabel = new Label("Użytkownik:");
        GridPane.setConstraints(usernameLabel, 0, 3);
        Label passwordLabel = new Label("Hasło:");
        GridPane.setConstraints(passwordLabel, 0, 4);
        TextField usernameTextField = new TextField();
        GridPane.setConstraints(usernameTextField, 2, 3);
        TextField passwordTextField = new TextField();
        GridPane.setConstraints(passwordTextField, 2, 4);


        Label userLabel = new Label("Wybór uzytkownika:");
        GridPane.setConstraints(userLabel, 0, 5);

        ChoiceBox<String> userChoiceBox = new ChoiceBox<>();
        for (User user:MenuBox.users){
            userChoiceBox.getItems().add(user.getUsername());
        }
        GridPane.setConstraints(userChoiceBox,2,5);


        Button closeButton = new Button("Powrót");
        GridPane.setConstraints(closeButton, 2, 10);
        closeButton.setMinWidth(130);
        closeButton.setOnAction(event -> window.close());

        Button showUserButton = new Button("Pokaż dane");
        GridPane.setConstraints(showUserButton,2,11);
        showUserButton.setMinWidth(130);
        showUserButton.setOnAction(event -> {
            usernameTextField.setText(userChoiceBox.getValue());

            List<User> result = MenuBox.users.stream()
                    .filter(item -> item.getUsername().equals(userChoiceBox.getValue()))
                    .collect(Collectors.toList());

            passwordTextField.setText(result.get(0).getPassword() );
        });

        userChoiceBox.setOnAction(event -> {
            showUserButton.fire();
        });

        Button addUserbutton = new Button("Dodaj użytkownika");
        GridPane.setConstraints(addUserbutton, 2, 12);
        addUserbutton.setMinWidth(130);
        addUserbutton.setOnAction(event -> {
            User user = new User(usernameTextField.getText(), passwordTextField.getText());
            MenuBox.users.add(user);
            userChoiceBox.getItems().add(user.getUsername());
        });


        Button deleteUserButton = new Button("Usuń użytkownika");
        GridPane.setConstraints(deleteUserButton, 2, 13);
        deleteUserButton.setMinWidth(130);
        deleteUserButton.setOnAction(event -> {

            List<User> result = MenuBox.users.stream()
                    .filter(item -> !item.getUsername().equals(usernameTextField.getText()))
                    .collect(Collectors.toList());

            MenuBox.users = result;
            userChoiceBox.getItems().remove(usernameTextField.getText());
            usernameTextField.setText("");
            passwordTextField.setText("");

        });




        grid.getChildren().addAll(showUserButton, addUserbutton, deleteUserButton, userChoiceBox, userLabel, usernameLabel,usernameTextField,passwordLabel,passwordTextField, menuNameLabel,closeButton);

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
}