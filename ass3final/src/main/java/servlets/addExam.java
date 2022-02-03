/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import database.tables.EditBloodTestTable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.BloodTest;

/**
 *
 * @author USER
 */
@WebServlet(name = "addExam", urlPatterns = {"/addExam"})
public class addExam extends HttpServlet {

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
            out.println("<title>Servlet addExam</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addExam at " + request.getContextPath() + "</h1>");
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
        System.out.println("In do get!");
        EditBloodTestTable bt = new EditBloodTestTable();
        BloodTest bloo = new BloodTest();
        ArrayList<BloodTest> bs = new ArrayList<>();
        String amka = request.getParameter("amka");
        System.out.println("AMKA=" + amka);
        Gson gson = new Gson();
        try ( PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            bs = bt.databaseToBloodAmka(amka);
            if (bs == null) {
                response.setStatus(201);
            } else {
                String json = gson.toJson(bs);
                out.print(json);
                out.flush();
                out.close();
            }
            response.setStatus(200);

        } catch (SQLException ex) {
            Logger.getLogger(addExam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addExam.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        EditBloodTestTable bt = new EditBloodTestTable();

        BloodTest bloo = new BloodTest();

        BufferedReader bf = request.getReader();
        System.out.println("ABU DHABI");

        String test_date = bf.readLine();
        String medical_center = bf.readLine();
        double vitamin_d3 = parseDouble(bf.readLine());

        double vitamin_b12 = parseDouble(bf.readLine());
        double blood_sugar = parseDouble(bf.readLine());
        double cholesterol = parseDouble(bf.readLine());

        double iron = parseDouble(bf.readLine());
        String amka = bf.readLine();

        System.out.println("test_date=" + test_date + "&medical_center=" + medical_center //+ '&vitamin_d3=' + vitamin_d3
        // +'&vitamin_b12=' + vitamin_b12 + '&cholesterol=' +cholesterol +
        // +'&blood_sugar=' + blood_sugar + '&iron=' + iron + '&amka1=' + amka1
        );

        bloo.setAmka(amka);
        bloo.setBlood_sugar(blood_sugar);
        bloo.setCholesterol(cholesterol);
        bloo.setIron(iron);
        bloo.setMedical_center(medical_center);
        bloo.setTest_date(test_date);
        bloo.setVitamin_b12(vitamin_b12);
        bloo.setVitamin_d3(vitamin_d3);
        bloo.setValues();

        try {
            bt.createNewBloodTest(bloo);
            System.out.println("add bloodtest user");
            response.setStatus(200);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addExam.class.getName()).log(Level.SEVERE, null, ex);
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

}
