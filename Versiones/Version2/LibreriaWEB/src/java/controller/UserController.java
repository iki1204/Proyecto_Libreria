package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClientUser;
import model.AdminUser;
import model.SalesAgent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("register")) {
            try {
                registerUser(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("login")) {
            try {
                loginUser(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int number = Integer.parseInt(request.getParameter("number"));
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        ClientUser user = new ClientUser();
        user.setUsername(username);
        user.setName(name);
        user.setLastname(lastname);
        user.setNumber(number);
        user.setMail(mail);
        user.setPassword(password);

        connectionDB.registerUser(user);

        request.getRequestDispatcher("HomeClient.jsp").forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int securitycode = Integer.parseInt(request.getParameter("securitycode"));

        if (securitycode == 1) {
            AdminUser user = connectionDB.loginAdminUser(username, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("HomeAdmin.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else if (securitycode == 2) {
            SalesAgent user = connectionDB.loginSalesAgent(username, password);if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("HomeSalesAgent.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else {
            ClientUser user = connectionDB.loginClientUser(username, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("HomeClient.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
    }
}