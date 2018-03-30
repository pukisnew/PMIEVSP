package i11.michalkevicius.deividas.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import i11.michalkevicius.deividas.controller.Database;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

public class AdminLoginController
{

    public JFXTextField username;
    public JFXPasswordField password;
    public JFXButton login_button;

    public void onLoginButtonClick(ActionEvent actionEvent) throws SQLException
    {
        if (Database.attemptAdminLogin(username.getText(), password.getText()))
        {
            System.out.println("yay");
        }
        else
        {
            new Alert(Alert.AlertType.ERROR, "Neteisingas prisijungimas", ButtonType.OK).show();
        }
    }

    public void onBackButtonPressed(ActionEvent actionEvent)
    {

    }

    public void onKeyTyped(KeyEvent keyEvent) throws SQLException
    {
        if (keyEvent.getCharacter().contains("\r") || keyEvent.getCharacter().contains("\n"))
            onLoginButtonClick(null);
        //keyEvent.consume();
    }
}
