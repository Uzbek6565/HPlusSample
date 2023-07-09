package com.example.hplussample.dao;

import com.example.hplussample.bean.Product;
import com.example.hplussample.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

    public boolean validateUser(String username, String password) {
        boolean isValidUser = false;
        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                isValidUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidUser;
    }

    public List<Product> searchProduct(String searchString) {
        List<Product> products = new ArrayList<>();

        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM h_products WHERE product_name like '%" + searchString + "%'";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductImgPath(resultSet.getString("image_path"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public int registerUser(User user) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String query = "INSERT INTO h_users values(?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getActivity());
            preparedStatement.setInt(6, user.getAge());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }
}
