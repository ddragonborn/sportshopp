package com.example.sport;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

public class ItemProductController {

    @FXML
    private Label disLabel;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private Product product;


    public void setData(Product product) throws MalformedURLException {
        this.product = product;

        nameLabel.setText(product.getName());
        priceLabel.setText(String.valueOf(product.getPrice()+ " руб."));
        disLabel.setWrapText(true);
        disLabel.setText(product.getDiscription());

        File file = new File("src/main/resources/img/"+product.getPhoto()+".jpg");
        String urlImage = file.toURI().toURL().toString();
        Image image = new Image(urlImage);
        img.setImage(image);

    }
}
