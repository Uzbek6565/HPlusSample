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

//    Connection connection = null;
    @Override
    public void init() throws ServletException {
        // initialization activity - set up DB connection object
        System.out.println("in init method of Home servlet");
//        connection = DBConnection.getConnectionToDatabase(); //in real projects, you can set up a complete connection pool,
                                                            // which has got a lot of db objects and that can be reused by the users for your application

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doGet method of Home servlet");

        req.getRequestDispatcher("/html/index.html").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("in destroy method of Home servlet");
        // clean up activity - close DB connection object
//        try {
//
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
