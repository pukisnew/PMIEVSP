package i11.michalkevicius.deividas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User
{
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty id = new SimpleStringProperty("");
    private StringProperty lastname = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty telephone = new SimpleStringProperty("");
    private StringProperty login = new SimpleStringProperty("");
    private StringProperty password = new SimpleStringProperty("");

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getId()
    {
        return id.get();
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

    public String getPassword()
    {
        return password.get();
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }
}
