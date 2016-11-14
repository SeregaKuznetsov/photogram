package servlets;

import dao.TokenDAOInterface;
import dao.UserDAOInterface;
import dao.factory.DAOFactory;
import dao.impl.TokenDAO;
import mail.GreetingEmail;
import model.Token;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Created by Ilya Evlampiev on 31.10.2015.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    static Logger log = Logger.getLogger(LoginServlet.class);
    static UserDAOInterface userDao = DAOFactory.getDAOFactory(1).getUserDAOInterface();
    static TokenDAOInterface tokenDao = DAOFactory.getDAOFactory(1).getTokenDAOInterface();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Starting registering user");
        User newUser = new User();
        log.debug("Retrieving user name from session");
        newUser.setName(req.getParameter("username"));
        try {
            newUser.setPassword(req.getParameter("password"));
        log.debug("Calculating and setting password for the user");
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5 algorithm not fount");
            e.printStackTrace();
        }
        newUser.setEmail(req.getParameter("email"));
        log.debug("Retrieving user email from request");

        /*  //jquery submit disable demonstration
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        log.debug("Saving user "+newUser.getName());
        try {
            userDao.addUser(newUser);
            newUser=userDao.getUserByName(newUser.getName());
            log.info("Saving user "+newUser.getName()+" succeed");
            Token tk=new Token();
            tk.setUuid(java.util.UUID.randomUUID().toString());
            Calendar c = Calendar.getInstance();
            c.setTime(new java.util.Date()); // Now use today date.
            c.add(Calendar.DATE, 5);
            java.util.Date now_plus_5_days=c.getTime();
            tk.setDeleteDate(now_plus_5_days);
            tk.setUser(newUser);
            tokenDao.create(tk);
            //req.setAttribute("passwordhash", newUser.getPasswordHash());
            getServletContext().getRequestDispatcher("/views/mainPage.jsp").forward(req, resp);
            //getServletContext().getRequestDispatcher("/logon.jsp").forward(req, resp);
            GreetingEmail ge=new GreetingEmail(newUser.getEmail(), newUser.getName(), req.getParameter("password"),tk.getUuid());
            ge.send();
            //MailUtil mu=new MailUtil(newUser.getEmail(), newUser.getUsername(), req.getParameter("password"));
            //mu.start();
            log.info("Sending email to " + newUser.getEmail());
        }
        catch (Exception ex)
        {
            log.error("Saving user "+newUser.getName()+" failed");
            getServletContext().getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }


    }
    // Переопределим стандартные методы
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }
}
