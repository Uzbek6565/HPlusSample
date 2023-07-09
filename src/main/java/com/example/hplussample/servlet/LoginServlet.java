package com.example.hplussample.servlet;

import com.example.hplussample.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getSession().getId());
        req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ApplicationDao applicationDao = new ApplicationDao();
        boolean isValidUser = applicationDao.validateUser(username, password);

        if (isValidUser) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(3600); //in seconds or you can set in web.xml
            req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
        }else {
            String message = "Invalid credentials, pleasi login again!";
            req.setAttribute("error", message);
            req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
        }



    }
}
