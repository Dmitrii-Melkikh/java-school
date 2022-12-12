package ru.croc.task17;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.*;
import java.sql.*;
public class ImporterToDatabase {

    private static final String sqlCreateTableProducts = "CREATE TABLE Products" +
            "(articleId VARCHAR(255) NOT NULL PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "cost DECIMAL NOT NULL);";
    private static final String sqlCreateTableOrders = "CREATE TABLE Orders" +
            "(orderId INT NOT NULL PRIMARY KEY, " +
            "UserName VARCHAR(255) NOT NULL); ";

    private static final String sqlCreateTableOrderProduct = "CREATE TABLE OrderProduct" +
            "(articleId VARCHAR(255) NOT NULL, " +
            "FOREIGN KEY (articleId) REFERENCES Products(articleId) ON DELETE CASCADE, " +
            "orderId INT NOT NULL, "  +
            "FOREIGN KEY (orderId) REFERENCES Orders(orderId) ON DELETE CASCADE);";
    private String connectionUrl;
    private String pathToFile;
    private String user;
    private String password;

    public ImporterToDatabase(String pathToFile, String connectionUrl, String user, String password) {
        this.connectionUrl = connectionUrl;
        this.pathToFile = pathToFile;
        this.user =user;
        this.password = password;

    }

    public void startImport(){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Не установлен драйвер работы с БД");
            System.exit(0);
        }

        List<Order> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<OrderProduct> orderProducts = new ArrayList<>();
        readFile(pathToFile, orders, products, orderProducts); //Читаем файл
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
            createTables(connection);
            importOrdersToDB(connection, orders);
            importProductsToDB(connection,products);
            importOrderProductsToDB(connection, orderProducts);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createTables(Connection connection) throws SQLException{
        try (Statement statement = connection.createStatement()){
            statement.execute(ImporterToDatabase.sqlCreateTableProducts);
            statement.execute(ImporterToDatabase.sqlCreateTableOrders);
            statement.execute(ImporterToDatabase.sqlCreateTableOrderProduct);
        }
    }
    private void readFile(String path,List<Order> orders, List<Product> products, List<OrderProduct> orderProducts) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл");
            return;
        }

        Set<String> articleIds = new HashSet<>();
        Set<Integer> orderIds = new HashSet<>();
        String[] tmp;
        while (scanner.hasNextLine()) {
            tmp = scanner.nextLine().split(",");

            orderProducts.add(new OrderProduct(tmp[2],Integer.parseInt(tmp[0])));

            if (orderIds.add(Integer.parseInt(tmp[0])))
                orders.add(new Order(Integer.parseInt(tmp[0]), tmp[1]));

            if (articleIds.add(tmp[2]))
                products.add(new Product(tmp[2], tmp[3], new BigDecimal(tmp[4])));
        }
    }

    private void importProductsToDB(Connection connection, List<Product> products) throws SQLException{
        String sql = "INSERT INTO Products VALUES(?,?,?)";
        for (Product product : products) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getArticleId());
                statement.setString(2, product.getName());
                statement.setBigDecimal(3, product.getCost());
                statement.execute();
            }
        }
    }


    private void importOrdersToDB(Connection connection, List<Order> orders) throws SQLException {
        String sql = "INSERT INTO Orders VALUES(?,?)";
        for (Order order : orders) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getOrderId());
                statement.setString(2, order.getUserName());
                statement.execute();
            }
        }
    }
    private void importOrderProductsToDB(Connection connection, List<OrderProduct> orderProducts) throws SQLException {
        String sql = "INSERT INTO OrderProduct VALUES(?,?)";
        for (OrderProduct orderProduct : orderProducts) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, orderProduct.getArticleId());
                statement.setInt(2, orderProduct.getOrderId());
                statement.execute();
            }
        }
    }
}
