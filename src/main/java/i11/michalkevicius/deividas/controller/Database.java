package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.model.Product;
import i11.michalkevicius.deividas.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database
{
    private static final String DB_ADDRESS = "";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME =  DB_ADDRESS + ":" + DB_PORT + "/kvkmokym_maistskaic";
    private static final String DB_URL = "jdbc:mariadb://" + DB_NAME;
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";
    private static Connection c;

    static
    {
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
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
            PreparedStatement stmt = c.prepareStatement("call attempt_login_as_admin(?, ?)");
            prepareLoginStatement(stmt, username, password);
            stmt.execute();
            return checkLoginResult(stmt.getResultSet());
        });
    }
    public static boolean attemptLogin(String username, String password) throws SQLException
    {
        return executeTransaction((Connection c) ->
        {
            PreparedStatement stmt = c.prepareStatement("call attempt_login(?, ?)");
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
        createUser(name, lastName, email, telephone, email, password, false);
    }

    public static Integer createUser(String name, String lastName, String email, String telephone, String login, String password, boolean isAdmin) throws SQLException
    {
        return executeTransaction((c) ->
        {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO AdminInfoLogin (Vardas, Pavardė, Elpaštas, Telefonas, Slaptažodis, Prisijungimas, Adminas) VALUES (?, ?, ?, ?, SHA1(?), ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, telephone);
            stmt.setString(5, password);
            stmt.setString(6, login);
            stmt.setBoolean(7, isAdmin);
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
        int id = createUser(user.getName(), user.getLastname(), user.getEmail(), user.getTelephone(), user.getLogin(), password, user.isAdmin());
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
            PreparedStatement stmt = c.prepareStatement("INSERT INTO MaistinesVertesLentele (Pavadinimas, `Energija (kcal)`, `Energija (kJ)`, `Angliavandeniai (g)`, `Riebalai (g)`, `Baltymai (g)`, `Skaidulinės medžiagos(g)`, `Vanduo (g)`, `Alkoholis (g)`, `Pelenai (g)`, `Monosacharidai (g)`, `Disacharidai (g)`, `Saharozė (g)`, `Viso grūdo, viso (g)`, `Cukrus, viso (g)`, `Sočiosios riebiosios rūgštys (g)`, `Riebiosios rūgštys 4:0-10:0 (g)`, `Riebiosios rūgštys 12:0 (g)`, `Riebiosios rūgštys 14:0 (g)`, `Riebiosios rūgštys 16:0 (g)`, `Riebiosios rūgštys 18:0 (g)`, `Riebiosios rūgštys 20:0 (g)`, `Mononesočiosios riebiosios rūgštys(g)`, `Riebiosios rūgštys 16:1 (g)`, `Riebiosios rūgštys 18:1 (g)`, `Polinesočiosios riebiosios rūgštys (g)`, `Riebiosios rūgštys 18:2 (g)`, `Riebiosios rūgštys 18:3 (g)`, `Riebiosios rūgštys 20:4 (g)`, `EPA (Riebiosios rūgštys 20:5) (g)`, `DPA (Riebiosios rūgštys 22:5) (g)`, `DHA (Riebiosios rūgštys 22:6) (g)`, `Cholesterolis (mg)`, `Retinolis (µg)`, `Retinol ekvivalentas (µg)`, `Beta-karotenas (µg)`, `Vitaminas D (µg)`, `Vitaminas E (mg)`, `Vitaminas K (µg)`, `Tiaminas (mg)`, `Riboflavinas (mg)`, `Vitaminas C (mg)`, `Niacinas (mg)`, `Niacino ekvivalentas (mg)`, `Vitaminas B-6 (mg)`, `Vitaminas B-12 (µg)`, `Folatas (µg)`, `Fosforas (mg)`, `Jodas (µg)`, `Geležis (mg)`, `Kalcis (mg)`, `Kalis (mg)`, `Magnis (mg)`, `Natris (mg)`, `Druska (g)`, `Selenas (µg)`, `Cinkas (mg)`, `Atliekos (pvz. lupenos) (%)`, `Valgomosios dalies koeficientas`, `Sausųjų medžiagų (g)`, `Gyvūninių baltymų (g)`, `Augalinių baltymų (g)`, `Krakmolo (g)`, `id`)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?, ?)");
            stmt.setString(1, product.getProperty("Name"));
            stmt.setString(2, product.getProperty("Kcal"));
            stmt.setString(3, product.getProperty("Kj"));
            stmt.setString(4, product.getProperty("Carbohydrates"));
            stmt.setString(5, product.getProperty("Fat"));
            stmt.setString(6, product.getProperty("Protein"));
            stmt.setString(7, product.getProperty("Fiber"));
            stmt.setString(8, product.getProperty("Water"));
            stmt.setString(9, product.getProperty("Alcohol"));
            stmt.setString(10, product.getProperty("Ash"));
            stmt.setString(11, product.getProperty("Monosacharids"));
            stmt.setString(12, product.getProperty("Disacharids"));
            stmt.setString(13, product.getProperty("FakeSuger"));
            stmt.setString(14, product.getProperty("Grain"));
            stmt.setString(15, product.getProperty("Sugar"));
            stmt.setString(16, product.getProperty("SuperFat"));
            stmt.setString(17, product.getProperty("Fat4_10"));
            stmt.setString(18, product.getProperty("Fat12"));
            stmt.setString(19, product.getProperty("Fat14"));
            stmt.setString(20, product.getProperty("Fat16"));
            stmt.setString(21, product.getProperty("Fat18"));
            stmt.setString(22, product.getProperty("Fat20"));
            stmt.setString(23, product.getProperty("MonoSuperFat"));
            stmt.setString(24, product.getProperty("Fat16_1"));
            stmt.setString(25, product.getProperty("Fat18_1"));
            stmt.setString(26, product.getProperty("PoliSuperFat"));
            stmt.setString(27, product.getProperty("Fat18_2"));
            stmt.setString(28, product.getProperty("Fat18_3"));
            stmt.setString(29, product.getProperty("Fat20_4"));
            stmt.setString(30, product.getProperty("Epa"));
            stmt.setString(31, product.getProperty("Dpa"));
            stmt.setString(32, product.getProperty("Dha"));
            stmt.setString(33, product.getProperty("Cholesterol"));
            stmt.setString(34, product.getProperty("Retinol"));
            stmt.setString(35, product.getProperty("Retinolequivalent"));
            stmt.setString(36, product.getProperty("Betakaroten"));
            stmt.setString(37, product.getProperty("Vit_d"));
            stmt.setString(38, product.getProperty("Vit_e"));
            stmt.setString(39, product.getProperty("Vit_k"));
            stmt.setString(40, product.getProperty("Tiamin"));
            stmt.setString(41, product.getProperty("Riboflavin"));
            stmt.setString(42, product.getProperty("Vit_c"));
            stmt.setString(43, product.getProperty("Niacin"));
            stmt.setString(44, product.getProperty("Niacinequivalent"));
            stmt.setString(45, product.getProperty("Vit_b_6"));
            stmt.setString(46, product.getProperty("Vit_b_12"));
            stmt.setString(47, product.getProperty("Folate"));
            stmt.setString(48, product.getProperty("Phosphorus"));
            stmt.setString(49, product.getProperty("Iodine"));
            stmt.setString(50, product.getProperty("Iron"));
            stmt.setString(51, product.getProperty("Calcium"));
            stmt.setString(52, product.getProperty("Potassium"));
            stmt.setString(53, product.getProperty("Magnesium"));
            stmt.setString(54, product.getProperty("Sodium"));
            stmt.setString(55, product.getProperty("Salt"));
            stmt.setString(56, product.getProperty("Selenium"));
            stmt.setString(57, product.getProperty("Zinc"));
            stmt.setString(58, product.getProperty("Rest"));
            stmt.setString(59, product.getProperty("Edible_coefficient"));
            stmt.setString(60, product.getProperty("Dry_material"));
            stmt.setString(61, product.getProperty("Animal_protein"));
            stmt.setString(62, product.getProperty("Natural_protein"));
            stmt.setString(63, product.getProperty("Starch"));
            stmt.setString(64, product.getId());
            stmt.executeUpdate();
            if (product.getId() == null)
            {
                Statement idStatement = c.createStatement();
                ResultSet rs = idStatement.executeQuery("SELECT id FROM MaistinesVertesLentele ORDER BY id DESC LIMIT 1");
                rs.next();
                product.setId(rs.getString("id"));
                idStatement.close();
            }
            stmt.close();
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
            stmt.close();
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

