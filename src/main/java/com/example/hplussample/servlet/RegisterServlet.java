package com.example.hplussample.servlet;

import com.example.hplussample.bean.User;
import com.example.hplussample.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlString = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), "");
        resp.getWriter().write(htmlString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String activity = req.getParameter("activity");
        int age = Integer.parseInt(req.getParameter("age"));

        User user = new User(password, username, fname, lname, activity, age);

        ApplicationDao applicationDao = new ApplicationDao();
        int affectedRows = applicationDao.registerUser(user);

        String message = null;
        if (affectedRows == 0) {
            message = "Error occured while registering!";
        }else {
            message = "User registered successfully";
        }

        String htmlString = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), message);
        resp.getWriter().write(htmlString);
    }

    public String getHTMLString(String filePath, String message) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page,message);
        return page;
    }
}
