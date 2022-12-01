package com.example.sport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tov implements Initializable {

    @FXML
    public TextField name;

    @FXML
    public TextArea address;

    @FXML
    public TextField razmer;
    @FXML
    public TextField cost;
    @FXML
    public TextArea dop;
    @FXML
    public TextField prodavez;
    @FXML
    public TextField foto;
    @FXML
    public ComboBox type;
    @FXML
    public Button newObyvlenie ;
    @FXML
    public Button btnBack;
    @FXML
    private Label infoLabel;

    ConnectDB db = new ConnectDB();
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> tovarType= null;
        try {
            tovarType = db.getTovarType();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        type.getItems().addAll(tovarType);
        Date dateNow = new Date();

// SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
// String dataS1 = formatForDateNow.format(dateNow);
// System.out.println(dataS1);
    }


    public void onNewObyavleniy(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, ParseException {

        Integer id = db.getId()+1;
        String nameText = name.getText();
        String costText = cost.getText();
        String dopText = dop.getText();
        String typeText = (String) type.getValue();
        String imgText = foto.getText();
        infoLabel.setText("Товар успешно добавлен");

        db.getInsert(id, nameText, costText, dopText, imgText,typeText);
    }

    public void btnbac() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminScreen.fxml")));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }





}


