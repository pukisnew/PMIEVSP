package i11.michalkevicius.deividas.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import i11.michalkevicius.deividas.controller.Database;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginController
{

    public JFXTextField username;
    public JFXPasswordField password;
    public JFXButton login_button;

    public void onLoginButtonClick(ActionEvent actionEvent) throws SQLException, IOException
    {
        if (Database.attemptAdminLogin(username.getText(), password.getText()))
        {
            SpreadsheetApp.launchAdminPanelStage();
        }
        else
        {
            new Alert(Alert.AlertType.ERROR, "Neteisingas prisijungimas", ButtonType.OK).show();
        }
    }

    public void onBackButtonPressed(ActionEvent actionEvent) throws IOException
    {
        SpreadsheetApp.launchMainStage();
    }

    public void onKeyTyped(KeyEvent keyEvent) throws SQLException, IOException
    {
        if (keyEvent.getCharacter().contains("\r") || keyEvent.getCharacter().contains("\n"))
            onLoginButtonClick(null);
        //keyEvent.consume();
    }
}
