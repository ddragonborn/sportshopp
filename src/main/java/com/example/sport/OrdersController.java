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

public class OrdersController {

    @FXML
    private Button btnBack;

    @FXML
    private GridPane gridOrder;

    ConnectDB dbHandler = new ConnectDB();
    private List<Order> orders = new ArrayList<>();

    @FXML
    void initialize() throws SQLException {
        showOrder("Select fioClient, adress,nameProduct,idStatus,price,data from sport.order");
    }

    int row = 1;
    public void showOrder(String select) throws SQLException {
        //Формирование продуктов
        orders.addAll(getDataOrder(select));

        try {
            for (int i = 0; i < orders.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemOrders.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemOrderController itemOrderController = fxmlLoader.getController();
                itemOrderController.setData(orders.get(i));


                gridOrder.add(anchorPane, 0, row++); //(child, column, row)

                //set item drid width
                gridOrder.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridOrder.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridOrder.setMaxWidth(Region.USE_PREF_SIZE);

                //set item drid height
                gridOrder.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridOrder.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridOrder.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public List<Order> getDataOrder(String select) throws SQLException {
        List<Order> orders1 = new ArrayList<>();
        Order order;
        ResultSet result = dbHandler.getOrder(select);

        while (result.next()) {
            order = new Order();
            order.setFioClient(result.getString(1));
            order.setAdress(result.getString(2));
            order.setNameProduct(result.getString(3));
            order.setIdStatus(result.getInt(4));
            order.setPrice(result.getString(5));
            order.setData(result.getString(6));
            orders1.add(order);
        }
        return orders1;
    }




    public void goBack() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminScreen.fxml")));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }
}
