package i11.michalkevicius.deividas.controller.main;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStage extends Stage
{
    public MainStage() throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(R.FXML.MAIN));
        Scene scene = new Scene(root);
        setScene(scene);
    }
}
