package ru.croc.task18;


import ru.croc.task17.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductDao {

    private Connection connection;

    public ProductDao(Connection connection){
        this.connection = connection;
    }

    public Product findProduct(String productCode) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Products WHERE articleId = ?")){
            statement.setString(1, productCode);
            try (ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    return new Product(result.getString("articleId"),
                            result.getString("name"),
                            result.getBigDecimal("cost"));
                }
                else{
                    return null;
                }
            }
        }
    }

    public Product createProduct(Product product) throws SQLException, ProductAlreadyExist {
        if (findProduct(product.getArticleId()) != null)
            throw new ProductAlreadyExist();
        else {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Products VALUES(?,?,?)")) {
                statement.setString(1, product.getArticleId());
                statement.setString(2, product.getName());
                statement.setBigDecimal(3, product.getCost());
                statement.execute();
            }
        }
        return product;
    }

    public Product updateProduct(Product product) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement
                ("UPDATE Products SET name = ?, cost = ? WHERE articleId = ?")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getCost());
            statement.setString(3, product.getArticleId());
            statement.execute();
        }
        return product;
    }


    public void deleteProduct(String productCode) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Products WHERE articleId = ?")) {
            statement.setString(1, productCode);
            statement.execute();
        }
    }


}
