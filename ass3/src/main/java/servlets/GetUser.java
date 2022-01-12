package servlets;

import com.google.gson.Gson;
import database.DB_Connection;
import database.tables.EditDoctorTable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.*;
import database.tables.EditSimpleUserTable;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @WebServlet(name = "Register", urlPatterns = {"/GetUser"}
 * @author USER
 */
public class GetUser extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        //
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

        response.setContentType("text/html;charset=UTF-8");

        int type = parseInt(request.getParameter("type"));

        EditDoctorTable tableDoc = new EditDoctorTable();

        EditSimpleUserTable tableUser = new EditSimpleUserTable();

        if (type == 0) {
            try {
                String amka = request.getParameter("amka");

                if (AmkaExistsUser(amka) == null && AmkaExistsDoctor(amka) == null) {

                    response.setStatus(200);
                } else {
                    response.setStatus(409);
                }

            } catch (SQLException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (type == 1) {

            try {
                String username = request.getParameter("username");

                if (tableUser.SimpleUserExistsUsername(username) == null && tableDoc.DoctorExistsUsername(username) == null) {
                    response.setStatus(200);

                } else {
                    response.setStatus(409);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            try {
                String email = request.getParameter("email");

                if (EmailExistsUser(email) == null && EmailExistsDoctor(email) == null) {
                    response.setStatus(200);
                } else {
                    response.setStatus(409);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String specialty = request.getParameter("specialty");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String birthdate = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        String amka = request.getParameter("amka");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String telephone = request.getParameter("telephone");
        int height = parseInt(request.getParameter("height"));
        int weight = parseInt(request.getParameter("weight"));
        int blooddonor = parseInt(request.getParameter("blooddonor"));
        String bloodtype = request.getParameter("bloodtype");
        double lat = parseDouble(request.getParameter("lat"));
        double lon = parseDouble(request.getParameter("lon"));
        String doctor_info = request.getParameter("doctor_info");

        Doctor doc = new Doctor();
        EditDoctorTable tableDoc = new EditDoctorTable();

        EditSimpleUserTable tableUser = new EditSimpleUserTable();
        SimpleUser SimUser = new SimpleUser();

        if (user.equals("Doctor")) {

            try {
                doc.setUsername(username);
                doc.setEmail(email);
                doc.setPassword(password);
                doc.setDoctor_info(doctor_info);
                doc.setBloodtype(bloodtype);
                doc.setFirstname(firstname);
                doc.setLastname(lastname);
                doc.setBlooddonor(blooddonor);
                doc.setAddress(address);
                doc.setAmka(amka);
                doc.setCity(city);
                doc.setBirthdate(birthdate);
                doc.setCountry(country);
                doc.setHeight(height);
                doc.setLat(lat);
                doc.setLon(lon);
                doc.setTelephone(telephone);
                doc.setWeight(weight);
                doc.setSpecialty(specialty);
                doc.setGender(gender);

                if (tableUser.SimpleUserExistsUsername(username) == null && tableDoc.DoctorExistsUsername(username) == null
                        && AmkaExistsUser(amka) == null && AmkaExistsDoctor(amka) == null
                        && EmailExistsUser(email) == null && EmailExistsDoctor(email) == null) {

                    tableDoc.addNewDoctor(doc);
                } else {

                    response.setStatus(409);
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else {

            try {

                SimUser.setUsername(username);
                SimUser.setEmail(email);
                SimUser.setPassword(password);

                SimUser.setBloodtype(bloodtype);
                SimUser.setFirstname(firstname);
                SimUser.setLastname(lastname);
                SimUser.setBlooddonor(blooddonor);
                SimUser.setAddress(address);
                SimUser.setAmka(amka);
                SimUser.setCity(city);
                SimUser.setBirthdate(birthdate);
                SimUser.setCountry(country);
                SimUser.setHeight(height);
                SimUser.setLat(lat);
                SimUser.setLon(lon);
                SimUser.setTelephone(telephone);
                SimUser.setWeight(weight);
                SimUser.setGender(gender);

                if (tableUser.SimpleUserExistsUsername(username) == null && tableDoc.DoctorExistsUsername(username) == null
                        && AmkaExistsUser(amka) == null && AmkaExistsDoctor(amka) == null
                        && EmailExistsUser(email) == null && EmailExistsDoctor(email) == null) {
                    tableUser.addNewSimpleUser(SimUser);

                    } else {

                        response.setStatus(409);
                    }



            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("username=" + username);
        System.out.println("user=" + user);
        System.out.println("email=" + email);
        System.out.println("lat=" + lat);
        System.out.println("lon=" + lon);
        System.out.println("blooddonor=" + blooddonor);
        System.out.println("bloodtype=" + bloodtype);
        System.out.println("gender=" + gender);
        System.out.println("password=" + password);
        System.out.println("lastname=" + lastname);
        System.out.println("firstname=" + firstname);
        System.out.println("country=" + country);
        System.out.println("city=" + city);
        System.out.println("telephone=" + telephone);
        System.out.println("address=" + address);
        System.out.println("weight=" + weight);
        System.out.println("height=" + height);
        System.out.println("amka=" + amka);
        System.out.println("birthdate=" + birthdate);
        System.out.println("specialty=" + specialty);
        System.out.println("doctor_info=" + doctor_info);

    }

    public String AmkaExistsUser(String amka) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM  users  WHERE amka = '" + amka + "'");
            if (rs != null) {
                rs.next();
            } else {
                return null;
            }
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Doctor doc = gson.fromJson(json, Doctor.class);
            System.out.println(json);
            return amka;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String AmkaExistsDoctor(String amka) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM  doctors  WHERE amka = '" + amka + "'");
            if (rs != null) {
                rs.next();
            } else {
                return null;
            }
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Doctor doc = gson.fromJson(json, Doctor.class);
            System.out.println(json);
            return amka;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String EmailExistsUser(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
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

    public String EmailExistsDoctor(String email) throws SQLException, ClassNotFoundException {
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
