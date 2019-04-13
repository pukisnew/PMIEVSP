package i11.michalkevicius.deividas.controller.usermanagement;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserManagementStage extends Stage
{
    public UserManagementStage() throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(R.FXML.USER_REG));
        Scene scene = new Scene(root);
        setScene(scene);
    }
}
