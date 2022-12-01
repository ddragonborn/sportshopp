package com.example.sport;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.MalformedURLException;

public class ItemOrderController {
    @FXML
    private Label adressField;

    @FXML
    private Label dateField;

    @FXML
    private Label nameField;

    @FXML
    private Label priceField;

    @FXML
    private Label productField;

    @FXML
    private Label statusField;

    private Order order;

    public void setData(Order order) throws MalformedURLException {
        this.order = order;
        nameField.setText(order.getFioClient());
        adressField.setText(order.getAdress());
        priceField.setText(order.getNameProduct());
        if (order.getIdStatus() == 1)
            statusField.setText("В обработке");
        else if (order.getIdStatus() == 2) {
            statusField.setText("В доставке");
        }else
            statusField.setText("Готов");

        priceField.setText(order.getPrice());
        dateField.setText(order.getData());

    }
}
