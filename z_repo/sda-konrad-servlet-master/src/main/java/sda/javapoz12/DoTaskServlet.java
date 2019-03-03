package sda.javapoz12;

import sda.javapoz12.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static sda.javapoz12.dal.UsersDB.USERS;

@WebServlet("/task/doTask")
public class DoTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User(request.getParameter("firstName"),
                request.getParameter("lastName"),
                Integer.parseInt(request.getParameter("age")));

        System.out.println("DoTaskServlet post from " + request.getRemoteAddr());
        System.out.println("User created: "+ user);

        USERS.saveUser(user);

        response.sendRedirect("/servletWar/task/index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String lastName = request.getParameter("lastName");
        if (lastName != null) {
            User user = USERS.getUserByLastName(lastName);

            if (user != null) {
                writer.println("DoTaskServlet GET from " + request.getRemoteAddr());
                writer.println("User read: "+ user);
            } else{
                writer.println("User not found.");
            }
            return;
        }

        String userNo = request.getParameter("userNo");
        if (userNo != null) {
            User user = USERS.getUserByNo(Integer.valueOf(userNo));

            if (user != null) {
                writer.println("DoTaskServlet GET from " + request.getRemoteAddr());
                writer.println("User read: "+ user);
            } else{
                writer.println("User not found.");
            }
            return;
        }


    }
}