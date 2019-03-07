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



/*
TODO:
W ramach rozbudowy umiejętności polecam:
- sformatować listę użytkowników aby każdy z użytkowników był linkiem kierującym do  informacji użytkowniku `doTask?surname=xxx` (zamiast xxx każdy użytkownik ma swoje nazwisko w linku)
- dodać link kierujący z listy do zakładania nowego użytkownika `index.html`
- rozbudować klasę użytkownik o pole email, które może być lepszym kluczem w mapie
- można dodać style i inne upiększenia
- dodać usuwanie użytkownika (link z listy) poprzez osobny servlet i metodę `GET`
doDelete?surname=xxx
 */

@WebServlet("/task/doTask")
public class DoTaskServlet extends HttpServlet {

    private UsersRepo repo;

    @Override
    public void init() throws ServletException {
        super.init();
        repo = UsersRepoInitializer.getInstnace();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User(request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                Integer.parseInt(request.getParameter("age")));

        System.out.println("DoTaskServlet post from " + request.getRemoteAddr());
        System.out.println("User created: "+ user);

        repo.saveUser(user);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect("/servletWar/task/index.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String lastName = request.getParameter("lastName");
        if (lastName != null) {
            User user = repo.getUserByLastName(lastName);

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
            User user = repo.getUserByNo(Integer.valueOf(userNo));

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