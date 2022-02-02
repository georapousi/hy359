/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import database.tables.EditSimpleUserTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "getUsers", urlPatterns = {"/getUsers"})
public class getUsers extends HttpServlet {

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

            System.out.println("mphka get process request Users");
            /* TODO output your page here. You may use following sample code. */
            JsonArray x = getUsersFromTable();
            response.setContentType("application/json");

            // Get the printwriter object from response to write the required json object to the output stream
            // Assuming your json object is jsonObject, perform the following, it will return your json object
            out.print(x.toString());
            out.flush();
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

    public JsonArray getUsersFromTable() {

        System.out.println("mphka get users from table");

        EditSimpleUserTable edt = new EditSimpleUserTable();
        ArrayList<SimpleUser> users = null;

        try {
            users = edt.databaseToSimpleUsers();

        } catch (SQLException ex) {

            Logger.getLogger(getUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        Gson gson = new Gson();
        JsonArray jsonUser = gson.toJsonTree(users).getAsJsonArray();
        System.out.println("Users\n" + jsonUser + "\n");
        return jsonUser;
    }

}
