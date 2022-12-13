package ru.croc.task18;


import ru.croc.task17.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String driverName = "org.postgresql.Driver";
        String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234";

        try{
            Class.forName(driverName);
        }
        catch (ClassNotFoundException e){
            System.out.println("Не установлен драйвер работы с БД");
            System.exit(0);
        }


        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
            ProductDao productDao = new ProductDao(connection);
            System.out.println(productDao.findProduct("Т5"));
            productDao.createProduct(new Product("Т8", "Видеокамера", new BigDecimal(1800)));
            productDao.deleteProduct("Т1");
            productDao.updateProduct(new Product("Т2", "Ведьмак", new BigDecimal(300)));
            OrderDao orderDao = new OrderDao(connection);
            ArrayList<Product> productsList = new ArrayList<>();
            productsList.add(new Product("Т3"));
            productsList.add(new Product("Т5"));
            orderDao.createOrder("dima", productsList );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ProductAlreadyExist e){
            System.out.println(e.getMessage());
        }
    }
}
