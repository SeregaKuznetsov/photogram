package ru.itis2016;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet({"/test"})
public class ServletCalculator extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession();
        String historyDigit = (String) session.getAttribute("historyDigit");
        String historyAction = (String) session.getAttribute("historyAction");

        System.out.println("GET - histOfDigit " + historyDigit);
        System.out.println("GET - histOfAction " + historyAction);

        String digit = req.getParameter("digit");
        String action = req.getParameter("action");

        System.out.println("GET - digit " + digit);
        System.out.println("GET - action" + action);

        if ((historyDigit == null) && digit.matches("^-?[0-9]+([.,]?)[0-9]*$")) {

            session.setAttribute("historyDigit", digit);
        }
        else if (!(digit.matches("^-?[0-9]+([.,]?)[0-9]*$"))) {
            session.setAttribute("historyDigit", null);
            digit = " ";
        }

        if ((historyAction == null) && !(action.equals("equally"))) {
            session.setAttribute("historyAction", action);
        }
        else if ((historyAction == null) && (action.equals("equally"))) {
            session.setAttribute("historyDigit", null);
            session.setAttribute("historyAction", null);
        }

        if ((historyDigit != null) && (digit.matches("^-?[0-9]+([.,]?)[0-9]*$")) && (historyAction != null)) {
            double firstOperand = 0;
            double secondOperand = 0;
            try {
                firstOperand = Double.valueOf((String) session.getAttribute("historyDigit"));
                secondOperand = Double.valueOf(digit);
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }
            String mathaction = (String) session.getAttribute("historyAction");
            if (mathaction.equals("division") && secondOperand == 0) {
                session.setAttribute("historyDigit", null);
                session.setAttribute("historyAction", null);
                digit = " ";
                action = " ";
            }
            else {
                if (mathaction.equals("addition")) {
                    digit = Double.toString(firstOperand + secondOperand);
                }
                else if (mathaction.equals("subtraction")) {
                    digit = Double.toString(firstOperand - secondOperand);
                }
                else if (mathaction.equals("multiplication")) {
                    digit = Double.toString(firstOperand * secondOperand);
                }
                else {
                    digit = Double.toString(firstOperand / secondOperand);
                }
                if (action.equals("equally")) {
                    session.setAttribute("historyDigit", null);
                    session.setAttribute("historyAction", null);
                }
                else {
                    session.setAttribute("historyDigit", digit);
                    session.setAttribute("historyAction", action);
                }
            }
        }

        resp.getWriter().write("Число: " + digit + "<br>" + "Операция: " + action);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("ISO-8859-1");
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession();
        String historyDigit = (String) session.getAttribute("historyDigit");
        String historyAction = (String) session.getAttribute("historyAction");

        String digit = req.getParameter("digit");
        String action = req.getParameter("mathaction");

        if ((historyDigit == null) && digit.matches("^-?[0-9]+([.,]?)[0-9]*$")) {
            session.setAttribute("historyDigit", digit);
        }
        else if (!(digit.matches("^-?[0-9]+([.,]?)[0-9]*$"))) {
            session.setAttribute("historyDigit", null);
            digit = " ";
        }

        String error = "";

        if ((historyAction == null) && !(action.equals("="))) {
            session.setAttribute("historyAction", action);
        }
        else if ((historyAction == null) && (action.equals("="))) {
            error = "<BR>Нужно ввести число и выбрать операцию!";
            session.setAttribute("historyDigit", null);
            session.setAttribute("historyAction", null);
        }

        if ((historyDigit != null) && (digit.matches("^-?[0-9]+([.,]?)[0-9]*$")) && (historyAction != null)) {
            double firstOperand = 0;
            double secondOperand = 0;
            try {
                firstOperand = Double.valueOf((String) session.getAttribute("historyDigit"));
                secondOperand = Double.valueOf(digit);
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }
            String mathaction = (String) session.getAttribute("historyAction");
            if (mathaction.equals("/") && secondOperand == 0) {
                error = "<BR>Нельзя делить на ноль!";
                session.setAttribute("historyDigit", null);
                session.setAttribute("historyAction", null);
                digit = " ";
                action = " ";
            }
            else {
                if (mathaction.equals("+")) {
                    digit = Double.toString(firstOperand + secondOperand);
                }
                else if (mathaction.equals("-")) {
                    digit = Double.toString(firstOperand - secondOperand);
                }
                else if (mathaction.equals("*")) {
                    digit = Double.toString(firstOperand * secondOperand);
                }
                else {
                    digit = Double.toString(firstOperand / secondOperand);
                }
                if (action.equals("=")) {
                    session.setAttribute("historyDigit", null);
                    session.setAttribute("historyAction", null);
                }
                else {
                    session.setAttribute("historyDigit", digit);
                    session.setAttribute("historyAction", action);
                }
            }
        }

        resp.getWriter().write("Число: " + digit + "<br>" + "Операция: " + action);
    }
}
