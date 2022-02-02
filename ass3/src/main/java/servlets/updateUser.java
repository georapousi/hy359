/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditDoctorTable;
import database.tables.EditSimpleUserTable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.SimpleUser;

/**
 *
 * @author USER
 */
@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class updateUser extends HttpServlet {

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
            out.println("<title>Servlet updateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUser at " + request.getContextPath() + "</h1>");
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

        BufferedReader bf = request.getReader();
        String firstname = bf.readLine();
        String lastname = bf.readLine();
        String telephone = bf.readLine();
        String username = bf.readLine();
        String country = bf.readLine();
        String city = bf.readLine();
        String address = bf.readLine();
        String email = bf.readLine();
        String password = bf.readLine();
        String birthdate = bf.readLine();
        String sex = bf.readLine();
        String amka = bf.readLine();
        int height = parseInt(bf.readLine());
        double weight = parseDouble(bf.readLine());
        int blooddonor = parseInt(bf.readLine());
        String bloodtype = bf.readLine();

        System.out.println("!!!!!!!!!!!!" + email + " " + birthdate + " " + blooddonor);

        EditSimpleUserTable tableUser = new EditSimpleUserTable();

        EditDoctorTable tableDoc = new EditDoctorTable();
        SimpleUser SimUser = new SimpleUser();

        try {

            if (tableUser.databaseToSimpleUserEmail(username, email) == null) {

                response.setStatus(409);
                System.out.println("error user email");

            } else if (tableDoc.databaseToDoctorEmail(username, email) == null) {

                response.setStatus(409);
                System.out.println("error doctor email");

            } else {
                tableUser.updateSimpleUser(username, password, email, firstname, telephone, lastname, amka, sex, country, city, address, height, weight, blooddonor, bloodtype, height, sex);

                System.out.println("ola ok");
                response.setStatus(200);
            }
        } catch (SQLException ex) {
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //tableUser.updateSimpleUser(username, weight);



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
