package com.example.sport;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;

public class ItemEmploeeController {

    @FXML
    private Label adressField;

    @FXML
    private Label dateField;

    @FXML
    private Label loginField;

    @FXML
    private Label nameField;

    @FXML
    private Label phoneField;

    private Emploee emploee;


    public void setData(Emploee emploee) throws MalformedURLException {
        this.emploee = emploee;
        nameField.setText(emploee.getFio());
        adressField.setText(emploee.getAdress());
        phoneField.setText(emploee.getPhone());
        loginField.setText(emploee.getLogin());
        dateField.setText(emploee.getLast_login());
    }


}
