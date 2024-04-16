package mk.ukim.finki.vebaud.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.vebaud.model.User;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.logging.LogRecord;

//@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
//        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
//        initParams = @WebInitParam(name = "ignore-path", value = "/login"))
//
//public class loginfilter implements Filter {
//    private String ignorePath;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//        ignorePath = filterConfig.getInitParameter("ignore-path");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        User user = (User) request.getSession().getAttribute("user");
//        String path = request.getServletPath();
//        if (!path.equals("/login") && user == null) {
//            response.sendRedirect("/login");
//        } else {
//            if (ignorePath.startsWith(path) || user != null) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            }else {
//                response.sendRedirect("/login");
//            }
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}

@WebFilter
@Profile("servlet")
public class loginfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User)request.getSession().getAttribute("user");

        String path = request.getServletPath();

        if (!"/login".equals(path) &&
                !"/h2".equals(path) &&
                !"/register".equals(path) &&
                !"/main.css".equals(path) && user==null) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}

