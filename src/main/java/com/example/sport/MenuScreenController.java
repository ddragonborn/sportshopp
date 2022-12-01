package com.example.sport;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class MenuScreenController {

    ConnectDB dbHandler = new ConnectDB();

    @FXML
    private TextField searchField;


    @FXML
    private GridPane gridProduct;

    @FXML
    private Label fioLabel;

    @FXML
    private Button btnOrder;
    @FXML
    private Button btnBack;


    private List<Product> products = new ArrayList<>();
    int row = 1;

    @FXML
    void initialize() throws SQLException {
        fioLabel.setText(startScreenController.NameFooter);

        createProduct("SELECT name,price, discription, photo FROM product");
    }

    public void goCreateOrder() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createOrder.fxml")));
        Stage window = (Stage) btnOrder.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }

    public void WC() throws SQLException, InterruptedException {
        clearProduct();
        products.clear();
       createProduct("SELECT name,price, discription, photo FROM Product where idCategory = 1;");

    }

    public void MC() throws SQLException, InterruptedException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM Product where idCategory = 2;");

    }

    public void WF() throws SQLException, InterruptedException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM Product where idCategory = 3;");
    }

    public void MF() throws SQLException, InterruptedException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM Product where idCategory = 4;");

    }
    public void main() throws SQLException, InterruptedException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM product");
    }
    public void search(KeyEvent even) throws SQLException, InterruptedException {
        if (even.getCode() == KeyCode.ENTER){
            clearProduct();
            products.clear();
            createProduct("SELECT name,price, discription, photo FROM product WHERE name LIKE '%"+searchField.getText()+"%';");
        }
    }

    public void sortUp() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM product order by price asc;");
    }
    public void sortDown() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM product order by price desc;");
    }

    public void AZ() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM product order by name asc;");
    }

    public void ZA() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, discription, photo FROM product order by name desc;");
    }



    public void createProduct(String select) throws SQLException {
        //Формирование продуктов
        products.addAll(getDataProduct(select));

        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemProduct.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProductController itemProductController = fxmlLoader.getController();
                itemProductController.setData(products.get(i));


                gridProduct.add(anchorPane, 0, row++); //(child, column, row)

                //set item drid width
                gridProduct.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridProduct.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridProduct.setMaxWidth(Region.USE_PREF_SIZE);

                //set item drid height
                gridProduct.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridProduct.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridProduct.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearProduct(){
        int i = 1;
        while (i <= products.size()){
                gridProduct.getChildren().remove(0);
                    i++;

        }
    }


    public List<Product> getDataProduct(String select) throws SQLException {
        List<Product> productos = new ArrayList<>();
        Product product;
        ResultSet result = dbHandler.getProduct(select);

        while (result.next()) {
            product = new Product();
            product.setName(result.getString(1));
            product.setPrice(result.getInt(2));
            product.setDiscription(result.getString(3));
            product.setPhoto(result.getString(4));
            productos.add(product);
        }
        return productos;
    }
    public void btnbac() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startScreen.fxml")));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }
}
