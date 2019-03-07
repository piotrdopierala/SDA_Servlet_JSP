package sda.javapoz12;

import sda.javapoz12.dal.UsersRepo;
import sda.javapoz12.dal.UsersRepoInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task/doDelete")
public class DoDeleteUser extends HttpServlet {

    private UsersRepo repo;

    @Override
    public void init() throws ServletException {
        super.init();
        repo = UsersRepoInitializer.getInstnace();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUserToDelete = Integer.parseInt(req.getParameter("id"));
        repo.deleteUserByNo(idUserToDelete);
        resp.sendRedirect("/servletWar/task/doList");
    }
}
