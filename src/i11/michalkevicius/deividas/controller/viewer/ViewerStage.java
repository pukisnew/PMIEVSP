package i11.michalkevicius.deividas.controller.viewer;

import i11.michalkevicius.deividas.controller.R;
import i11.michalkevicius.deividas.model.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ViewerStage extends Stage
{
    public ViewerStage(List<Product> data) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(R.FXML.VIEWER_TABLE));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        ViewerController ctrl = loader.getController();
        ctrl.setResizeables(widthProperty(), heightProperty());
        ctrl.setData(data);
        setScene(scene);
    }
}
