package com.example.hplussample.dao;

import com.example.hplussample.bean.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {
    public List<Product> searchProduct(String searchString) {
        List<Product> products = new ArrayList<>();

        Connection connection = DBConnection.getConnectionToDatabase();
        String query = "SELECT * FROM product WHERE name like '%" + searchString + "%'";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setProductImgPath(resultSet.getString("img_path"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
