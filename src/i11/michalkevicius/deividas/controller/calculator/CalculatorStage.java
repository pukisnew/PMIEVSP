package i11.michalkevicius.deividas.controller.calculator;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorStage extends Stage
{
    public CalculatorStage() throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(R.FXML.CALCULATOR));
        Scene scene = new Scene(root);
        setScene(scene);
    }
}
