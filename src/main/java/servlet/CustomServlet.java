package servlet;

import jpa.EntityManagerHelper;
import jpa.JpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "customServelet", urlPatterns = {"/users"})
public class CustomServlet extends HttpServlet {

    EntityManager manager = EntityManagerHelper.getEntityManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("myForm.html");
        requestDispatcher.forward(req, resp);
    }

    //TODO: Make it works in Postman
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        String fName = req.getParameter("first_name");
        String lName = req.getParameter("last_name");
        JpaTest.createUser(fName, lName, manager);

        tx.commit();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    }
}
