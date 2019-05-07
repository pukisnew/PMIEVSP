package i11.michalkevicius.deividas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User
{
    private StringProperty id = new SimpleStringProperty("");
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty lastname = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty telephone = new SimpleStringProperty("");
    private StringProperty login = new SimpleStringProperty("");
    private boolean isAdmin = false;
    //private StringProperty password = new SimpleStringProperty("");

    public User() throws SQLException
    {

    }

    public User(ResultSet users) throws SQLException
    {
        if (users == null)
            return;
        setId(users.getString("Nr"));
        setName(users.getString("Vardas"));
        setLastname(users.getString("Pavardė"));
        setEmail(users.getString("Elpaštas"));
        setTelephone(users.getString("Telefonas"));
        setLogin(users.getString("Prisijungimas"));
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public void setId(String id)
    {
        this.id.set(id);
    }

    public String getLastname()
    {
        return lastname.get();
    }

    public void setLastname(String lastname)
    {
        this.lastname.set(lastname);
    }

    public String getEmail()
    {
        return email.get();
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }

    public String getTelephone()
    {
        return telephone.get();
    }

    public void setTelephone(String telephone)
    {
        this.telephone.set(telephone);
    }

    public String getLogin()
    {
        return login.get();
    }

    public void setLogin(String login)
    {
        this.login.set(login);
    }

    public int getId()
    {
        return new Integer(this.id.getValue());
    }

    public void setId(int nr)
    {
        setId(nr + "");
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
