package i11.michalkevicius.deividas.controller.admin;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginStage extends Stage
{
    public AdminLoginStage() throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(R.FXML.ADMIN_LOGIN));
        Scene scene = new Scene(root);
        setScene(scene);
    }
}
