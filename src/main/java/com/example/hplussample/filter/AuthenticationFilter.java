package com.example.hplussample.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/orderHistory", "/viewProfile"})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //pre-processing
        System.out.println("In Authentication Filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().startsWith("/HPlusSample_war_exploded/orderHistory") || request.getRequestURI().startsWith("/HPlusSample_war_exploded/viewProfile")){
            HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("/html/login.jsp").forward(request, servletResponse);
            }
        }
        filterChain.doFilter(request, servletResponse);
        //post-processing
        System.out.println("After Authentication filter chain ");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
