package main.java.dao;

import main.java.db.ConnectionProvider;
import main.java.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlProductDao implements ProductDao {

    private static final String CREATE = "INSERT INTO products(name, quantity, price, available) VALUES(?, ?, ?, ?);";
    private static final String READ = "SELECT * FROM products;";
    private static final String UPDATE = "UPDATE products SET quantity=?, price=?, available=? WHERE name=?;";
    private static final String DELETE = "DELETE FROM products WHERE name=?;";

    @Override
    public void create(Product product) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setBoolean(4, product.isAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ)) {
            ResultSet resultSet = statement.executeQuery();
            String name;
            int quantity;
            double price;
            boolean isAvailable;
            while (resultSet.next()) {
                Product product = new Product();
                name = resultSet.getString("name");
                quantity = resultSet.getInt("quantity");
                price = resultSet.getDouble("price");
                isAvailable = resultSet.getBoolean("available");
                product.setName(name);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setAvailable(isAvailable);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(Product product) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            if (isExists(product.getName())) {
                statement.setInt(1, product.getQuantity());
                statement.setDouble(2, product.getPrice());
                statement.setBoolean(3, product.isAvailable());
                statement.setString(4, product.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            if (isExists(product.getName())) {
                statement.setString(1, product.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isExists(String wantedName) {
        final String QUERY_NAME = "SELECT name FROM products;";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_NAME)) {
            ResultSet resultSet = statement.executeQuery();
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                if (wantedName.equals(name)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
