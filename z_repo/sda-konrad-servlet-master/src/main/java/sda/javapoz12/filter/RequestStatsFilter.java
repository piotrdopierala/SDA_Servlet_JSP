package sda.javapoz12.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter(filterName = "RequestStatsFilter", urlPatterns = "/task/*")
public class RequestStatsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if (req instanceof HttpServletRequest) {
            HttpServletRequest request;
            request = (HttpServletRequest) req;
            System.out.println("Request from address IP:"+request.getRemoteAddr());
            System.out.println("Filter request at time:"+ LocalDateTime.now());
            System.out.println("Filter requested URL:"+request.getMethod()+" - "+request.getRequestURL());

        }

        if (resp instanceof HttpServletResponse) {
            HttpServletResponse response;
            response = (HttpServletResponse) resp;
        }

        chain.doFilter(req, resp);
        System.out.println("END OF REQUEST STATS FILER");
    }

    public void init(FilterConfig config) throws ServletException {

    }
}