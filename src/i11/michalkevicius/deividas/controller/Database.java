package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.model.Product;
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
            System.exit(255);
        }
    }

    public static <T> T executeTransaction(Transaction<T> t) throws SQLException
    {
        return t.executeTransaction(c);
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

    public static void removeUser(int id) throws SQLException
    {
        executeTransaction((c) ->
        {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM AdminInfoLogin WHERE Nr = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return null;
        });
    }

    public static void createUser(String name, String lastName, String email, String telephone, String password) throws SQLException
    {
        createUser(name, lastName, email, telephone, email, password);
    }

    public static Integer createUser(String name, String lastName, String email, String telephone, String login, String password) throws SQLException
    {
        return executeTransaction((c) ->
        {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO AdminInfoLogin (Vardas, Pavardė, Elpaštas, Telefonas, Slaptažodis, Prisijungimas) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, telephone);
            stmt.setString(5, password);
            stmt.setString(6, login);
            stmt.executeUpdate();
            Statement stmt2 = c.createStatement();
            stmt2.execute("SELECT Nr FROM AdminInfoLogin ORDER BY Nr DESC LIMIT 1");
            ResultSet rs = stmt2.getResultSet();
            rs.next();
            return rs.getInt("Nr");
        });
    }

    public static void createUser(User user, String password) throws SQLException
    {
        int id = createUser(user.getName(), user.getLastname(), user.getEmail(), user.getTelephone(), user.getLogin(), password);
        user.setId(id);
    }

    public static List<User> getUsers() throws SQLException
    {
        try (ResultSet users = executeTransaction((c) -> c.createStatement().executeQuery("SELECT * FROM AdminInfoLogin")))
        {
            ArrayList<User> returnable = new ArrayList<>();
            while (users.next())
            {
                returnable.add(new User(users));
            }
            return returnable;
        }
    }

    public static List<Product> getProducts() throws SQLException
    {
        try (ResultSet products = executeTransaction((c) -> c.createStatement().executeQuery("SELECT * FROM MaistinesVertesLentele")))
        {
            ArrayList<Product> returnable = new ArrayList<>();
            while (products.next())
            {
                returnable.add(new Product(products));
            }
            return returnable;
        }
    }

    interface Transaction<T>
    {
        T executeTransaction(Connection c) throws SQLException;
    }
}

