package i11.michalkevicius.deividas.controller.calculator;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorStage extends Stage
{
    private final FXMLLoader loader;

    public CalculatorStage() throws IOException
    {
        this.loader = new FXMLLoader(getClass().getResource(R.FXML.CALCULATOR));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
    }

    @Override
    public void close() {
        CalculatorController controller = loader.getController();
        controller.stop();
        super.close();
    }
}
