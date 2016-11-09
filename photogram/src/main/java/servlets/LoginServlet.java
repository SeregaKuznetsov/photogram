package servlets;

/**
 * Created by Сергей on 09.11.2016.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("/login.ftl").forward(request, response);

    }

    @Override
    public void doPost (HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("123")){
            request.setAttribute("error", false);
            request.getSession().setAttribute("session_uname", username);
            response.addCookie(new Cookie("cookie_uname", username));
            response.sendRedirect("/profile");
        } else {
            request.setAttribute("error", true);
            getServletConfig().getServletContext().getRequestDispatcher(
                    "/login.ftl").forward(request, response);
        }

    }
}