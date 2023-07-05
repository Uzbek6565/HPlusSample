package com.example.hplussample.servlet;

import com.example.hplussample.dao.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet {

    Connection connection = null;
    @Override
    public void init() throws ServletException {
        // initialization activity - set up DB connection object
        System.out.println("in init method");
        connection = DBConnection.getConnectionToDatabase();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doGet method");

        req.getRequestDispatcher("/html/index.html").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("in destroy method");
        // clean up activity - close DB connection object
        try {

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
