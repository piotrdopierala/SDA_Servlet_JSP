package sda.javapoz12;

import sda.javapoz12.dal.UsersDB;
import sda.javapoz12.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import static sda.javapoz12.dal.UsersDB.USERS;

@WebServlet("/task/doShowUser")
public class DoShowUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = USERS.getUserByNo(id);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Informacja szczegółowa na temat użytkownika:";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><meta charset=\"UTF-8\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n");

        out.println("Imię:"+user.getName()+"<br>");
        out.println("Nazwisko:"+user.getLastName()+"<br>");
        out.println("Wiek:"+user.getAge()+"<br>");
        out.println("email:"+user.getEmail()+"<br>");


        out.println("<br><a href=\"/servletWar/task/doList\">Powrót do listy</a>");
        out.println("<br><a href=\"/servletWar/task\">Dodaj nowego użytkownika</a>");
        out.println("</ul>\n" +
                "</body>" +
                "</html>");
    }
}
