package com.example.sport;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class regScreenController {
    @FXML
    private TextField adressField;

    @FXML
    private TextField ageField;

    @FXML
    private Button btnReg;

    @FXML
    private TextField fioField;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField logField;

    @FXML
    private TextField passField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button btnBack;

    @FXML
    void initialize() {
        btnReg.setOnAction(event -> {
            signUpNewUser();
        });
        btnBack.setOnAction(event ->{
            Parent wLogin = null;
            try {
                wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startScreen.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage window = (Stage) btnBack.getScene().getWindow();
            window.setScene(new Scene(wLogin, 960, 540));
            window.show();
        });

    }

    private void signUpNewUser() {
        ConnectDB dbHandler = new ConnectDB();


        String fio = fioField.getText();
        String age = ageField.getText();
        String adress = adressField.getText();
        String phone = phoneField.getText();
        String login = logField.getText();
        String password = passField.getText();

        if (!fio.isEmpty() && !age.isEmpty() && !adress.isEmpty() && !phone.isEmpty() && !login.isEmpty() && !password.isEmpty()) {
            dbHandler.signUpUser(fio,age,adress,phone,login,password);
            infoLabel.setText("Регистрация прошла успешно");
        }else
            infoLabel.setText("Не все поля заполнены");


    }
}
