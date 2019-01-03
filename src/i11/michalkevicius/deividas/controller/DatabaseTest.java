package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.model.User;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class DatabaseTest {

    @org.junit.Test
    public void createUser() throws SQLException {
        User u = new User();
        u.setEmail(new Date().toString());
        String password = "default";
        Database.createUser(u, password);
        assertNotEquals(0, u.getId());
        //new File("baigdb").delete();
    }


}