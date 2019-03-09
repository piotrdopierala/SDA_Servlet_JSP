package sda.javapoz12.sandbox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(value = "/actionServlet")
public class ActionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using POST Method to Read Form Data";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n");
        out.println("<b>Twój adres IP:"+request.getRemoteAddr()+"</b><br>");

        request.getParameterMap().forEach((name, values) -> {
            out.println(" <li><strong>" + name + "</strong>: " + Arrays.toString(values));
        });
        out.println("</ul>\n" +
                "</body>" +
                "</html>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n");
        out.println("<b>Twój adres IP:"+request.getRemoteAddr()+"</b><br>");

        request.getParameterMap().forEach((name, values) -> {
            out.println(" <li><strong>" + name + "</strong>: " + Arrays.toString(values));
        });
        out.println("</ul>\n" +
                "</body>" +
                "</html>");
    }


}
