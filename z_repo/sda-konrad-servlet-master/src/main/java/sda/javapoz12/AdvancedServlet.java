package sda.javapoz12;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdvancedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            resp.setStatus(HttpServletResponse.SC_OK);
            outputStream.println("HAHAHA I'M SO ADVANCED");
        }
    }
}
