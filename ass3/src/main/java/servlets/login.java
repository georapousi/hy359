/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import database.DB_Connection;
import database.tables.EditDoctorTable;
import database.tables.EditSimpleUserTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.Doctor;
import mainClasses.SimpleUser;

/**
 *
 * @author USER
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
            System.out.println("username="+username);
            System.out.println("password="+password);

        try(PrintWriter out = response.getWriter()){
           

            
            EditDoctorTable tableDoc = new EditDoctorTable();
            Doctor doc = tableDoc.databaseToDoctor(username, password);
            EditSimpleUserTable tableUser = new EditSimpleUserTable();
            SimpleUser user = tableUser.databaseToSimpleUser(username, password);
            System.out.println("Before " + username + " " + password + "\n" + user);

            
            if (doc == null && user == null) {
                
                //System.out.println(tableUser.databaseToSimpleUser(username, password));
                //System.out.println(tableDoc.databaseToDoctor(username, password));
                response.setStatus(409);

            } else {
                if (doc != null) {
                    String json = tableDoc.doctorToJSON(tableDoc.databaseToDoctor(username, password));
                    out.print(json);
                    response.setStatus(201);
                } else {
                    String json = tableUser.simpleUserToJSON(tableUser.databaseToSimpleUser(username, password));
                    out.print(json);
                }
                out.flush();
                session.setAttribute("login", username);
                response.setStatus(200);
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public String UsernameExistsDoctor(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM doctors WHERE email = '" + email + "'");
            if (rs != null) {
                rs.next();
            } else {
                return null;
            }
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Doctor doc = gson.fromJson(json, Doctor.class);
            System.out.println(json);
            return email;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
