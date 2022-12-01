package com.example.sport;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//Класс для взаимодействия с БД
public class ConnectDB {

    Connection dbConnect;

    //IP адресс сервера
    private final String host = "localhost";

    //Порт
    private final String port ="3306";

    //Название схемы БД
    private  final String nameDB = "sport";

    //Имя пользователя в БД
    private  final String userDB = "root";

    //Пароль пользователя от БД
    private  final String passDB = "123456";

    //Метод подключения к базе
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + host + ":"
                + port + "/" + nameDB;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnect = DriverManager.getConnection(connectionString,
                userDB , passDB);
        return dbConnect;
    }

    //Метод Авторизации(проверка наличия логина и пароля в БД)
    public ResultSet getUser(String login, String password){
        ResultSet resSet = null;

        //Формирование запроса
        String select = "SELECT idRole, fio FROM "+nameDB+".employee WHERE login = ? and password = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            prSt.setString(2, password);
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
    public void signUpUser(String fio, String age, String adress, String phone, String login, String password) {
        //SQL запрос
        String insert = "INSERT INTO employee(fio, age, adress, phone, login,password, idRole) VALUES ('"+fio+"','"+age+"','"+adress+"','"+phone+"','"+login+"','"+password+"','1')";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.executeUpdate(insert);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getAdminData(){
        ResultSet resSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement("Select fio, phone from "+nameDB+".employee where idRole = "+startScreenController.IDsotrud+"");
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }


    public ResultSet getProduct(String select){
        ResultSet resSet = null;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;

    }

    public ResultSet getEmploee(String select){
        ResultSet resSet = null;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;

    }


    public void updateTime(String login) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String update = "update "+nameDB+".employee set last_login = '"+simpleDateFormat.format(date)+"' where login = '"+login+"';";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate(update);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrder(String name, String adress, String value, String price) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String insert = "INSERT INTO "+nameDB+".order(price, data, idStatus, nameProduct, fioClient,adress) VALUES ('"+price+"','"+simpleDateFormat.format(date)+"','1','"+value+"','"+name+"','"+adress+"')";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.executeUpdate(insert);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getOrder(String select) {
        ResultSet resSet = null;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    public ArrayList<String> getOthet() throws SQLException, ClassNotFoundException {
        String zapros = " SELECT nameProduct, price, Client, adress, data FROM sport.order";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(zapros);
        ArrayList<String> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(resultSet.getString(1));
            result.add(resultSet.getString(2));
            result.add(resultSet.getString(3));
            result.add(resultSet.getString(4));
            result.add(resultSet.getString(5));


        }
        statement.close();
        return result;
    }
    public ArrayList<String> getTovarType() throws SQLException, ClassNotFoundException {
        String zapros = "SELECT name FROM category";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(zapros);
        ArrayList<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString(1));

        }
        statement.close();
        return result;
    }

    public Integer getId() throws SQLException, ClassNotFoundException {
        String zapros = "select max(id) from "+nameDB+".product";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(zapros);
        int result = 0;

        while (resultSet.next()){
            result = resultSet.getInt(1);
        }
        statement.close();
        return result;
    }

    public void getInsert(Integer id, String name, String cost, String dop, String img ,String type) throws SQLException, ClassNotFoundException {
        int typeId = 1;
        if (type.equals("Женские кроссовки")){
            typeId = 1;
        }else if (type.equals("Мужские ботинки")){
            typeId = 2;
        }else if (type.equals("Женские футболки")){
            typeId = 3;
        }else if (type.equals("Мужские футболки")) {
            typeId = 4;
        }
        String sql2 = "INSERT INTO `product`(`id`, `name`, `price`, `discription`, `photo`, `idCategory`) VALUES (?,?,?,?,?,?)";

        PreparedStatement pst;
        pst = dbConnect.prepareStatement(sql2);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setString(3, cost);
        pst.setString(4, dop);
        pst.setInt(6, typeId);
        pst.setString(5, img);
        pst.executeUpdate();

    }


}
