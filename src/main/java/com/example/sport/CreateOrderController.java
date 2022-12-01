package com.example.sport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CreateOrderController {
    @FXML
    private TextField adressLabel;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnOrder;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TextField fioLabel;

    @FXML
    private Label infoLabel;

    @FXML
    private Label priceLabel;

    ConnectDB connectDB = new ConnectDB();

    public static ObservableList<String> productBox = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {
      setBox("Select name from product");
      priceLabel.setText("0");
      combobox.setItems(productBox);
    }

    public void setBox(String select) throws SQLException {
        ResultSet result = connectDB.getProduct(select);

        while (result.next()) {
            productBox.add(result.getString(1));
        }

    }

    public void createOrder() throws SQLException {
        if (!fioLabel.getText().equals("") && !adressLabel.getText().equals("") && combobox.getValue() != null){
            priceLabel.setText(getPrice("Select price from product where name = '"+combobox.getValue()+"';"));
            connectDB.sendOrder(fioLabel.getText(), adressLabel.getText(),combobox.getValue(),priceLabel.getText());
            infoLabel.setText("Заказ оформлен");
        }else
            infoLabel.setText("Не все поля заполнены");
    }

    private String getPrice(String select) throws SQLException {
        String price = "0";
        ResultSet result = connectDB.getProduct(select);
        while (result.next()) {
           price = String.valueOf(result.getInt(1));
        }
        return price;
    }
    public void btnbac() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuScreen.fxml")));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }


}
