package sda.javapoz12;

import sda.javapoz12.dal.UsersRepo;
import sda.javapoz12.dal.UsersRepoInitializer;
import sda.javapoz12.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;


@WebServlet("/task/doList")
public class DoShowUsers extends HttpServlet {

    private UsersRepo repo;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("--- doList init ---");
        repo = UsersRepoInitializer.getInstnace();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String title = "List of Users:";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><meta charset=\"UTF-8\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><title>" + title + "</title></head>\n" +
                "<body>\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n");

        Collection<User> users = repo.getUsers();

        users.forEach(u -> out.println("<li><a href=\"/servletWar/task/doShowUser?id="+u.getId()+"\"> ID:"+u.getId()+" imie i nazwisko:"+ u.getName() + " " + u.getLastName()+"</a> <a style=\"color:red\" href=\"/servletWar/task/doDelete?id="+u.getId()+"\">Usuń</a></li>"));
        out.println("<br><a href=\"/servletWar/task\">Dodaj nowego użytkownika</a>");
        out.println("</ul>\n" +
                "</body>" +
                "</html>");
}
}