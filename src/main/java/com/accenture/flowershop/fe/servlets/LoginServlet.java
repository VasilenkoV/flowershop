package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.be.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;

    public LoginServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserDTO user = null;
        boolean hasError = false;
        String errorString = null;

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            try{
                user = userBusinessService.getUserDTO(login, password);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            }catch (NullPointerException e){
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            user = new UserDTO();
            user.setLogin(login);
            user.setPassword(password);


            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);


            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp");

            dispatcher.forward(request, response);
        }

        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }

            else {
                MyUtils.deleteUserCookie(response);
            }

            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp");
        dispatcher.forward(request, response);
    }
}
