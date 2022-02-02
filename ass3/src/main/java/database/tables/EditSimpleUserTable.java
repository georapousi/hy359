/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.SimpleUser;
import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditSimpleUserTable {

 
    public void addSimpleUserFromJSON(String json) throws ClassNotFoundException{
         SimpleUser user=jsonToSimpleUser(json);
         addNewSimpleUser(user);
    }
    
     public SimpleUser jsonToSimpleUser(String json){
         Gson gson = new Gson();

        SimpleUser user = gson.fromJson(json, SimpleUser.class);
        return user;
    }
    
    public String simpleUserToJSON(SimpleUser user){
         Gson gson = new Gson();

        String json = gson.toJson(user, SimpleUser.class);
        return json;
    }
    

    public void updateSimpleUser(String username, String password, String email, String firstname, String telephone,
            String lastname, String amka, String sex, String country, String city, String address,
            int height, double weight, int blooddonor, String bloodtype, int birthdate, String gender) throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update = "UPDATE users SET height='" + height + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET lastname='" + lastname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET firstname='" + firstname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET address='" + address + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET country='" + country + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET telephone='" + telephone + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET blooddonor='" + blooddonor + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET bloodtype='" + bloodtype + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET password='" + password + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET weight='" + weight + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET email='" + email + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        update = "UPDATE users SET city='" + city + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

//        update = "UPDATE users SET birthdate='" + birthdate + "' WHERE username = '" + username + "'";
//        stmt.executeUpdate(update);

        update = "UPDATE users SET gender='" + gender + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);

        stmt.close();
        con.close();
    }



    /* public void updateSimpleUserNew(String username, String password, String email, String firstname, String telephone,
            String lastname, String amka, String sex, String country, String city, String address,
            int height, double weight, boolean blooddonor, String bloodtype) throws SQLException, ClassNotFoundException {

            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            StringBuilder update = new StringBuilder();

            update.append("UPDATE users SET")
                    .append("weight = ").append("'").append(weight).append("'")
                    .append(" height = ").append("'").append(Integer.toString(height)).append("',")
                    .append(" password = ").append("'").append(password).append("',")
                    .append(" email = ").append("'").append(email).append("',")
                    .append(" telephone = ").append("'").append(telephone).append("',")
                    .append(" country = ").append("'").append(country).append("',")
                    .append(" firstname = ").append("'").append(firstname).append("',")
                    .append(" lastname = ").append("'").append(lastname).append("',")
                    .append(" city = ").append("'").append(city).append("',")
                    .append(" address = ").append("'").append(address).append("',")
                    .append(" blooddonor = ").append("'").append(blooddonor).append("'")
                    .append(" WHERE username = ").append("'").append(username).append("';");

        stmt.executeUpdate(update.toString());
    }
*/
    public void printSimpleUserDetails(String username, String password) throws SQLException, ClassNotFoundException {
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
    
    public SimpleUser databaseToSimpleUser(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            SimpleUser user = gson.fromJson(json, SimpleUser.class);
            return user;
        } catch (Exception e) {
            System.err.println("IN EDITSIMPLEUSERTABLE Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    public SimpleUser databaseToSimpleUserEmail(String username, String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users  WHERE email = '" + email + "'");
            if (rs.next() == true) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                SimpleUser user = gson.fromJson(json, SimpleUser.class);
                return user;
            }
        } catch (Exception e) {
            System.err.println("IN EDITSIMPLEUSERTABLE Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    public SimpleUser SimpleUserExistsUsername(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            SimpleUser user = gson.fromJson(json, SimpleUser.class);
            System.out.println(json);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public String databaseUserToJSON(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


     public void createSimpleUserTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE users "
                + "(user_id INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(40) not null unique,	"
                + "    password VARCHAR(32) not null,"
                + "    firstname VARCHAR(20) not null,"
                + "    lastname VARCHAR(30) not null,"
                + "    birthdate DATE not null,"
                + "    gender  VARCHAR (7) not null,"
                + "    amka VARCHAR (11) not null,"
                + "    country VARCHAR(30) not null,"
                + "    city VARCHAR(50) not null,"
                + "    address VARCHAR(50) not null,"
                + "    lat DOUBLE,"
                + "    lon DOUBLE,"
                + "    telephone VARCHAR(14) not null,"
                + "    height INTEGER,"
                + "    weight DOUBLE,"
                + "   blooddonor BOOLEAN,"
                + "   bloodtype VARCHAR(7) not null,"
                + " PRIMARY KEY ( user_id))";
        stmt.execute(query);
        stmt.close();
    }
    
    
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewSimpleUser(SimpleUser user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " users (username,email,password,firstname,lastname,birthdate,gender,amka,country,city,address,"
                    + "lat,lon,telephone,height,weight,blooddonor,bloodtype)"
                    + " VALUES ("
                    + "'" + user.getUsername() + "',"
                    + "'" + user.getEmail() + "',"
                    + "'" + user.getPassword() + "',"
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getBirthdate() + "',"
                    + "'" + user.getGender() + "',"
                    + "'" + user.getAmka() + "',"
                    + "'" + user.getCountry() + "',"
                    + "'" + user.getCity() + "',"
                    + "'" + user.getAddress() + "',"
                    + "'" + user.getLat() + "',"
                    + "'" + user.getLon() + "',"
                    + "'" + user.getTelephone() + "',"
                    + "'" + user.getHeight() + "',"
                    + "'" + user.getWeight() + "',"
                    + "'" + user.isBloodDonor() + "',"
                    + "'" + user.getBloodtype() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The user was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditSimpleUserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
