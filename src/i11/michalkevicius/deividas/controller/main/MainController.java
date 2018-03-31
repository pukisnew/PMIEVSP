package i11.michalkevicius.deividas.controller.main;

import i11.michalkevicius.deividas.controller.Database;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainController
{
    public void onControlPanelClick(ActionEvent e) throws IOException
    {
        SpreadsheetApp.launchAdminLoginStage();
    }

    public void onDataTableViewClick(javafx.event.ActionEvent actionEvent) throws SQLException, IOException
    {
        List<Product> products = Database.getProducts();
        SpreadsheetApp.launchViewerStage(products);

    }

    public void onCalculatorClick(ActionEvent actionEvent)
    {

    }
}
