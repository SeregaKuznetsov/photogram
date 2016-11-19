package servlets;

import dao.UserDAOInterface;
import dao.factory.DAOFactory;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ilya Evlampiev on 26.10.2015.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static Logger log = Logger.getLogger(LoginServlet.class);
    static UserDAOInterface userDao = DAOFactory.getDAOFactory(1).getUserDAOInterface();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String referer = req.getHeader("Referer");

            if (password != null && username !=null) {
                User sessionUser = userDao.read(username, password);
                if (sessionUser == null) {
                    req.setAttribute("error", "Incorrect username or password");
                    getServletContext().getRequestDispatcher("/login.ftl").forward(req, resp);
                } else {
                    req.getSession().setAttribute("user", sessionUser);
                    getServletContext().getRequestDispatcher("/views/mainPage.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Username or password was not passed");
                getServletContext().getRequestDispatcher("/login.ftl").forward(req, resp);
            }
    }

    // Переопределим стандартные методы
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
