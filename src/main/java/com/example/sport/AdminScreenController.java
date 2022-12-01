package com.example.sport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminScreenController {
    @FXML
    private Button btnHistory;

    @FXML
    private Button btnOrders;

    @FXML
    private Button exit;

    @FXML
    private Label fioField;

    @FXML
    private Label phoneField;

    @FXML
    private Label roleField;

    @FXML
    private Button dobavTov;

    ConnectDB dbHandler = new ConnectDB();
    @FXML
    void initialize() throws SQLException {
        getData();
    }

    public void getData() throws SQLException {
        ResultSet result = dbHandler.getAdminData();
        while (result.next()){
            fioField.setText(result.getString(1));
            phoneField.setText(result.getString(2));
        }
        if (startScreenController.IDsotrud == 3){
            roleField.setText("Администратор");
        }else
            roleField.setText("Продавец");
    }

    public void goExit() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startScreen.fxml")));
        Stage window = (Stage) exit.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }

    public void goHistory() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("historyScreen.fxml")));
        Stage window = (Stage) exit.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }

    public void goOrders() throws IOException {
        Parent wLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Orders.fxml")));
        Stage window = (Stage) exit.getScene().getWindow();
        window.setScene(new Scene(wLogin, 960, 540));
        window.show();
    }
    public void clot(ActionEvent event) throws IOException, DocumentException, SQLException, ClassNotFoundException {
        com.itextpdf.text.Document document1 = new Document();
        PdfWriter.getInstance(document1, new FileOutputStream("file.pdf"));
        document1.open();
        ArrayList<String> othet = dbHandler.getOthet();

        for (int i = 0; i < othet.size(); i++){

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph paragraphOne = new Paragraph("Name " + othet.get(i), font);
            Paragraph paragraphOne1 = new Paragraph("Price: " + othet.get(++i), font);
            Paragraph paragraphOne2= new Paragraph("Client: " + othet.get(++i), font);
            Paragraph paragraphOne3 = new Paragraph("Adress: " + othet.get(++i), font);
            Paragraph paragraphOne4 = new Paragraph("Date: " + othet.get(++i), font);


            document1.add(paragraphOne);
            document1.add(paragraphOne1);
            document1.add(paragraphOne2);
            document1.add(paragraphOne3);
            document1.add(paragraphOne4);

    }
        document1.close();


    }

    public void tov(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DobTOv.fxml"));
        stage.getIcons().add(new Image("file:src\\main\\resources\\img\\icon.png"));
        Scene scene = new Scene(fxmlLoader.load(), 866, 551);
        stage.setTitle("SPORTSSHOP");
        stage.setScene(scene);
        stage.show();
        Stage stageNow = (Stage) dobavTov.getScene().getWindow();
        stageNow.close();

    }




}
