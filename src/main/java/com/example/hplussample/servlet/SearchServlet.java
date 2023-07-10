package com.example.hplussample.servlet;

import com.example.hplussample.bean.Product;
import com.example.hplussample.dao.ApplicationDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // collect search string from the form
        String parameter = req.getParameter("search");
        Connection connection = (Connection) req.getServletContext().getAttribute("dbconnection");

        req.getSession().setAttribute("search", parameter);
        // call DAO layer and get all products for search criteria
        ApplicationDao applicationDao = new ApplicationDao();

        List<Product> products = applicationDao.searchProduct(parameter, connection);

        // write the products data back to the client browser
//        if (products.size() != 0){
//            String htmlString = getHTMLString(req.getServletContext().getRealPath("/html/search.html"), products);
//            resp.getWriter().write(htmlString);
//        }else {
//            resp.getWriter().write("No products found");
//        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("/html/search.jsp").forward(req, resp);
    }

    public String getHTMLString(String filePath, List<Product> products) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, products.get(0).getProductImgPath(),
                products.get(1).getProductImgPath(), products.get(2).getProductImgPath(),
                products.get(0).getProductName(), products.get(1).getProductName(),
                products.get(2).getProductName(), 0);
        return page;
    }
}
