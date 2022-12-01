package com.example.sport;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryScreenController {
    @FXML
    private Button btnBack;


    @FXML
    private GridPane gridEmploee;
    ConnectDB dbHandler = new ConnectDB();
    private List<Emploee> emploees = new ArrayList<>();

    @FXML
    void initialize() throws SQLException {
        createEmploee("SELECT fio,age, adress,phone,login,last_login FROM employee");
    }

    int row = 1;
    public void createEmploee(String select) throws SQLException {
        //Формирование продуктов
        emploees.addAll(getDataEmploee(select));

        try {
            for (int i = 0; i < emploees.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemEmploee.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEmploeeController itemEmploeeController = fxmlLoader.getController();
                itemEmploeeController.setData(emploees.get(i));


                gridEmploee.add(anchorPane, 0, row++); //(child, column, row)


                gridEmploee.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEmploee.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEmploee.setMaxWidth(Region.USE_PREF_SIZE);


                gridEmploee.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEmploee.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridEmploee.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public List<Emploee> getDataEmploee(String select) throws SQLException {
        List<Emploee> emploees1 = new ArrayList<>();
        Emploee emploee;
        ResultSet result = dbHandler.getEmploee(select);

        while (result.next()) {
            emploee = new Emploee();
            emploee.setFio(result.getString(1));
            emploee.setAge(result.getString(2));
            emploee.setAdress(result.getString(3));
            emploee.setPhone(result.getString(4));
            emploee.setLogin(result.getString(5));
            emploee.setLast_login(result.getString(6));
            emploees1.add(emploee);
       }
        return emploees1;
    }



    public void goBack() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminScreen.fxml")));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }
}
