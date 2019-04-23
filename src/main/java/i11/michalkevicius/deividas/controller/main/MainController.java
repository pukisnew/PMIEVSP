package i11.michalkevicius.deividas.controller.main;

import i11.michalkevicius.deividas.controller.Database;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public class MainController
{
    public void onControlPanelClick(ActionEvent e) throws IOException
    {
        SpreadsheetApp.launchAdminLoginStage();
    }

    public void onDataTableViewClick(javafx.event.ActionEvent actionEvent) throws SQLException, IOException
    {
        ObservableList<Product> products = FXCollections.observableArrayList(Database.getProducts());
        SpreadsheetApp.launchViewerStage(products);

    }

    public void onCalculatorClick(ActionEvent actionEvent) throws IOException
    {
        SpreadsheetApp.launchCalculator();
    }

    public void onLeaveClick(ActionEvent actionEvent) throws IOException {
        SpreadsheetApp.launchLoginStage();
    }
}
