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
import java.util.Arrays;
import java.util.Collection;

import static sda.javapoz12.dal.UsersDB.USERS;

@WebServlet("/task/doList")
public class DoShowUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "List of Users:";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><meta charset=\"UTF-8\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n");


        Collection<User> users = USERS.getUsers();

        users.forEach(u -> out.println("<li> ID:"+u.getId()+" imie i nazwisko:"+ u.getName() + " " + u.getLastName() + ", wiek: " + u.getAge() + ".</li>"));
        out.println("<br><a href=\"/servletWar/task\">Dodaj nowego użytkownika</a>");
        out.println("</ul>\n" +
                "</body>" +
                "</html>");
}
}