package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database
{
    private static final String DB_NAME = "baigDB.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_NAME;
    private static Connection c;

    static
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(DB_URL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(500);
        }
    }

    public static <T> T executeTransaction(Transaction<T> t) throws SQLException
    {
        return t.executeTransaction(c);
    }

    public static boolean attemptLoggingIn(String username, String password) throws SQLException
    {
        return executeTransaction((Connection c) ->
        {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM InfoLogin WHERE Elpastas = ? AND Password = ?");
            prepareLoginStatement(stmt, username, password);
            return checkLoginResult(stmt.getResultSet());
        });
    }

    public static boolean attemptAdminLogin(String username, String password) throws SQLException
    {
        return executeTransaction((Connection c) ->
        {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM AdminInfoLogin WHERE Prisijungimas = ? AND Slaptažodis = ?");
            prepareLoginStatement(stmt, username, password);
            stmt.execute();
            return checkLoginResult(stmt.getResultSet());
        });
    }

    private static void prepareLoginStatement(PreparedStatement stmt, String username, String password) throws SQLException
    {
        stmt.setString(1, username);
        stmt.setString(2, password);
    }

    private static boolean checkLoginResult(ResultSet rs) throws SQLException
    {
        boolean result = rs.next();
        rs.close();
        return result;
    }

    public static void removeUser(String id) throws SQLException
    {
        executeTransaction((c) ->
        {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM InfoLogin WHERE Nr = ?");
            stmt.setInt(1, new Integer(id));
            stmt.executeUpdate();
            return null;
        });
    }

    public static List<User> getUsers() throws SQLException
    {
        try (ResultSet users = executeTransaction((c) -> c.createStatement().executeQuery("SELECT * FROM InfoLogin")))
        {
            ArrayList<User> returnable = new ArrayList<>();
            while (users.next())
            {
                returnable.add(new User(users));
            }
            return returnable;
        }
    }

    interface Transaction<T>
    {
        T executeTransaction(Connection c) throws SQLException;
    }
}

