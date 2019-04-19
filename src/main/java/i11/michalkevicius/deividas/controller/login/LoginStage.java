package i11.michalkevicius.deividas.controller.login;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginStage extends Stage
{
    public LoginStage() throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(R.FXML.LOGIN));
        Scene scene = new Scene(root);
        setScene(scene);
    }
}
