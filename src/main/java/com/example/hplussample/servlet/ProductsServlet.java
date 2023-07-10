package com.example.hplussample.servlet;

import com.example.hplussample.bean.Product;
import com.example.hplussample.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addProducts")
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection dbconnection = (Connection) req.getServletContext().getAttribute("dbconnection");
        List<String> cart = (List<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        if (req.getParameter("product") != null) {
            cart.add(req.getParameter("product"));
        }

        session.setAttribute("cart", cart);

        // get search criteria from search servlet
        String search = (String) session.getAttribute("search");

        //get the search results from dao
        ApplicationDao dao = new ApplicationDao();
        List<Product> products = dao.searchProduct(search, dbconnection);

        req.setAttribute("products", products);
        req.getRequestDispatcher("/html/search.jsp").forward(req, resp);
    }
}
