/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fall22.pe.controllers;

import fall22.pe.user.UserDAO;
import fall22.pe.user.UserDTO;
import fall22.pe.user.UserErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String LOGIN_PAGE = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            HttpSession session = null;
            
            UserErrorDTO error = null;
            String url = LOGIN_PAGE;
            
            String userId = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            
            try {
                UserDAO dao = new UserDAO();
                UserDTO dto = dao.login(userId, password);
                if (dto == null) {
                    error = new UserErrorDTO();
                    error.setUserIDAndPasswordInvalid("Incorrect UserID or Password");
                    request.setAttribute("ERROR_LOGIN", error);
                    url = LOGIN_PAGE;
                    return;
                }
                if (!dto.getRoleID().equalsIgnoreCase("ADMIN")) {
                    url = LOGIN_PAGE;
                    error = new UserErrorDTO();
                    error.setNotAdminRole("Need login with role admin");
                    request.setAttribute("ERROR_LOGIN", error);
                    return ;
                }
                session = request.getSession(true);
                session.setAttribute("USER", dto);
                url = ADMIN_PAGE;
            } catch (SQLException ex) {
                ex.toString();
            } catch (ClassNotFoundException ex) {
                ex.toString();
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
