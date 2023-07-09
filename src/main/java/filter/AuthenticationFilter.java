package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().startsWith("/HPlusSample_war_exploded/orderHistory") || request.getRequestURI().startsWith("/HPlusSample_war_exploded/viewProfile")){
            HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("/html/login.jsp").forward(request, servletResponse);
            }
        }
        filterChain.doFilter(request, servletResponse);
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
