package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{

    private final String COOKIE_BACKGROUND = "profile_background";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        putBackgroundSettings(req);

        getServletConfig().getServletContext()
                .getRequestDispatcher("/profile.ftl")
                .forward(req,resp);
    }

    private void putBackgroundSettings(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(COOKIE_BACKGROUND.equals(cookie.getName())){
                req.setAttribute(COOKIE_BACKGROUND, cookie.getValue());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newBackground = req.getParameter("background");
        resp.addCookie(new Cookie(COOKIE_BACKGROUND, newBackground));
        resp.sendRedirect("/profile");

    }
}
