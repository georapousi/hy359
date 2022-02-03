package com.mycompany.ass3;

import database.*;
import database.tables.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class main {

    public static void main(String args[]) {
        try {
            initDatabase();
            initTables();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void initDatabase() throws SQLException, ClassNotFoundException {

        Connection conn = DB_Connection.getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE HY359");
        stmt.close();
        conn.close();

    }

    public static void initTables() throws SQLException, ClassNotFoundException {
        EditSimpleUserTable eut = new EditSimpleUserTable();
        eut.createSimpleUserTable();
        EditDoctorTable edt = new EditDoctorTable();
        edt.createDoctorTable();
        EditBloodTestTable ett = new EditBloodTestTable();
        ett.createBloodTestTable();
        EditMessageTable emt = new EditMessageTable();
        emt.createMessageTable();
        EditRandevouzTable er = new EditRandevouzTable();
        er.createRandevouzTable();
        EditTreatmentTable etr = new EditTreatmentTable();
        etr.createTreatmentTable();
    }

}
