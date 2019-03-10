package sda.javapoz12.filter;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@WebFilter(filterName = "Redirect to start", urlPatterns = "/task/*")
public class RedirectStartFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        boolean visited=false;
        if (req instanceof HttpServletRequest) {

            HttpServletRequest request;
            request = (HttpServletRequest) req;

            Cookie[] cookies = request.getCookies();
            if(Objects.nonNull(cookies)) {
                visited = Arrays.stream(cookies).filter(c -> c.getName().equals("visited")).findFirst().isPresent();
            }
        }

        if (resp instanceof HttpServletResponse) {
            HttpServletResponse response;
            response = (HttpServletResponse) resp;
            if (!visited)
                response.sendRedirect("servletWar/index.jsp");
        }

        if(visited) {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}