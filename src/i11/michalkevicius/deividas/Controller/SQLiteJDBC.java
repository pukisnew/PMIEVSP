package i11.michalkevicius.deividas.Controller;

import java.sql.*;

public class SQLiteJDBC{

    public static void main(String args[]) {
        SQLiteJDBC test = new SQLiteJDBC();

//        test.createTable();
//        test.insertIntoTable();
        //     test.selectFromDB();
//        test.updateIntoTable();
//        test.deleteFromTable();
//        test.selectFromDB();
    }

    private void openDB() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/baigDB.db");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}

