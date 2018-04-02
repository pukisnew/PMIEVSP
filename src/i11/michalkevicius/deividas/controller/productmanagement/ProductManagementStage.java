package i11.michalkevicius.deividas.controller.productmanagement;

import i11.michalkevicius.deividas.controller.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductManagementStage extends Stage
{
    public ProductManagementStage() throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(R.FXML.PRODUCT_REG));
        Scene scene = new Scene(root);
        setScene(scene);
    }
}
