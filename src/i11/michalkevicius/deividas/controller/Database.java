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

    public static void createProduct(Product product) throws SQLException
    {
        executeTransaction((c) ->
        {
            PreparedStatement stmt = c.prepareStatement("INSERT OR REPLACE INTO MaistinesVertesLentele (Pavadinimas, \"Energija (kcal)\", \"Energija (kJ)\", \"Angliavandeniai (g)\", \"Riebalai (g)\", \"Baltymai (g)\", \"Skaidulinės medžiagos(g)\", \"Vanduo (g)\", \"Alkoholis (g)\", \"Pelenai (g)\", \"Monosacharidai (g)\", \"Disacharidai (g)\", \"Saharozė (g)\", \"Viso grūdo, viso (g)\", \"Cukrus, viso (g)\", \"Sočiosios riebiosios rūgštys (g)\", \"Riebiosios rūgštys 4:0-10:0 (g)\", \"Riebiosios rūgštys 12:0 (g)\", \"Riebiosios rūgštys 14:0 (g)\", \"Riebiosios rūgštys 16:0 (g)\", \"Riebiosios rūgštys 18:0 (g)\", \"Riebiosios rūgštys 20:0 (g)\", \"Mononesočiosios riebiosios rūgštys(g)\", \"Riebiosios rūgštys 16:1 (g)\", \"Riebiosios rūgštys 18:1 (g)\", \"Polinesočiosios riebiosios rūgštys (g)\", \"Riebiosios rūgštys 18:2 (g)\", \"Riebiosios rūgštys 18:3 (g)\", \"Riebiosios rūgštys 20:4 (g)\", \"EPA (Riebiosios rūgštys 20:5) (g)\", \"DPA (Riebiosios rūgštys 22:5) (g)\", \"DHA (Riebiosios rūgštys 22:6) (g)\", \"Cholesterolis (mg)\", \"Retinolis (µg)\", \"Retinol ekvivalentas (µg)\", \"Beta-karotenas (µg)\", \"Vitaminas D (µg)\", \"Vitaminas E (mg)\", \"Vitaminas K (µg)\", \"Tiaminas (mg)\", \"Riboflavinas (mg)\", \"Vitaminas C (mg)\", \"Niacinas (mg)\", \"Niacino ekvivalentas (mg)\", \"Vitaminas B-6 (mg)\", \"Vitaminas B-12 (µg)\", \"Folatas (µg)\", \"Fosforas (mg)\", \"Jodas (µg)\", \"Geležis (mg)\", \"Kalcis (mg)\", \"Kalis (mg)\", \"Magnis (mg)\", \"Natris (mg)\", \"Druska (g)\", \"Selenas (µg)\", \"Cinkas (mg)\", \"Atliekos (pvz. lupenos) (%)\", \"Valgomosios dalies koeficientas\", \"Sausųjų medžiagų (g)\", \"Gyvūninių baltymų (g)\", \"Augalinių baltymų (g)\", \"Krakmolo (g)\", \"id\")" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?, ?)");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getKcal());
            stmt.setString(3, product.getKj());
            stmt.setString(4, product.getCarbohydrates());
            stmt.setString(5, product.getFat());
            stmt.setString(6, product.getProtein());
            stmt.setString(7, product.getFiber());
            stmt.setString(8, product.getWater());
            stmt.setString(9, product.getAlcohol());
            stmt.setString(10, product.getAsh());
            stmt.setString(11, product.getMonosacharids());
            stmt.setString(12, product.getDisacharids());
            stmt.setString(13, product.getFakeSuger());
            stmt.setString(14, product.getGrain());
            stmt.setString(15, product.getSugar());
            stmt.setString(16, product.getSuperFat());
            stmt.setString(17, product.getFat4_10());
            stmt.setString(18, product.getFat12());
            stmt.setString(19, product.getFat14());
            stmt.setString(20, product.getFat16());
            stmt.setString(21, product.getFat18());
            stmt.setString(22, product.getFat20());
            stmt.setString(23, product.getMonoSuperFat());
            stmt.setString(24, product.getFat16_1());
            stmt.setString(25, product.getFat18_1());
            stmt.setString(26, product.getPoliSuperFat());
            stmt.setString(27, product.getFat18_2());
            stmt.setString(28, product.getFat18_3());
            stmt.setString(29, product.getFat20_4());
            stmt.setString(30, product.getEpa());
            stmt.setString(31, product.getDpa());
            stmt.setString(32, product.getDha());
            stmt.setString(33, product.getCholesterol());
            stmt.setString(34, product.getRetinol());
            stmt.setString(35, product.getRetinolequivalent());
            stmt.setString(36, product.getBetakaroten());
            stmt.setString(37, product.getVit_d());
            stmt.setString(38, product.getVit_e());
            stmt.setString(39, product.getVit_k());
            stmt.setString(40, product.getTiamin());
            stmt.setString(41, product.getRiboflavin());
            stmt.setString(42, product.getVit_c());
            stmt.setString(43, product.getNiacin());
            stmt.setString(44, product.getNiacinequivalent());
            stmt.setString(45, product.getVit_b_6());
            stmt.setString(46, product.getVit_b_12());
            stmt.setString(47, product.getFolate());
            stmt.setString(48, product.getPhosphorus());
            stmt.setString(49, product.getIodine());
            stmt.setString(50, product.getIron());
            stmt.setString(51, product.getCalcium());
            stmt.setString(52, product.getPotassium());
            stmt.setString(53, product.getMagnesium());
            stmt.setString(54, product.getSodium());
            stmt.setString(55, product.getSalt());
            stmt.setString(56, product.getSelenium());
            stmt.setString(57, product.getZinc());
            stmt.setString(58, product.getRest());
            stmt.setString(59, product.getEdible_coefficient());
            stmt.setString(60, product.getDry_material());
            stmt.setString(61, product.getAnimal_protein());
            stmt.setString(62, product.getNatural_protein());
            stmt.setString(63, product.getStarch());
            stmt.setString(64, product.getId());
            stmt.executeUpdate();
            if (product.getId() == null)
            {
                Statement idStatement = c.createStatement();
                ResultSet rs = idStatement.executeQuery("SELECT id FROM MaistinesVertesLentele ORDER BY id DESC LIMIT 1");
                rs.next();
                product.setId(rs.getString("id"));
            }
            return null;
        });
    }

    public static void removeProduct(Product p) throws SQLException
    {
        executeTransaction((c) ->
        {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM MaistinesVertesLentele WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(p.getId()));
            stmt.executeUpdate();
            return null;
        });
    }

    public static void updateProduct(Product p) throws SQLException
    {
        //removeProduct(p);
        createProduct(p);
    }

    interface Transaction<T>
    {
        T executeTransaction(Connection c) throws SQLException;
    }
}

