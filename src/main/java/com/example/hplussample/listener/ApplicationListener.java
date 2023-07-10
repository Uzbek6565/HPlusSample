package com.example.hplussample.listener;

import com.example.hplussample.dao.DBConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("in contextInitialized method");
        Connection connection = DBConnection.getConnectionToDatabase();
        sce.getServletContext().setAttribute("dbconnection", connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("in contextDestroyed method");
        Connection dbconnection = (Connection) sce.getServletContext().getAttribute("dbconnection");
        try {
            dbconnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
