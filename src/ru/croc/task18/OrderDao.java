package ru.croc.task18;

import ru.croc.task17.Order;
import ru.croc.task17.Product;

import java.sql.*;

import java.util.List;

public class OrderDao {

    private Connection connection;

    public OrderDao(Connection connection){
        this.connection = connection;
    }


    public Order createOrder(String userLogin, List<Product> products) throws SQLException {

        int orderId;

        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery("SELECT orderId FROM Orders o ORDER BY o.orderId DESC")) {
                result.next();
                orderId = result.getInt("orderId") + 1;
            }
        }

        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO Orders VALUES(?,?)") ){
            statement.setInt(1, orderId);
            statement.setString(2, userLogin);
            statement.execute();

        }
        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO OrderProduct VALUES(?,?)")) {
            for (Product product : products) {
                statement.setString(1, product.getArticleId());
                statement.setInt(2,orderId);
                statement.execute();
            }
        }
        return new Order(orderId, userLogin);
    }
}
