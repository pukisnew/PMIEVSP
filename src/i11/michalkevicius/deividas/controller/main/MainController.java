package i11.michalkevicius.deividas.controller.main;

import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController
{
    public void onControlPanelClick(ActionEvent e) throws IOException
    {
        SpreadsheetApp.launchAdminLoginStage();
    }

    public void onDataTableViewClick(javafx.event.ActionEvent actionEvent)
    {

    }

    public void onCalculatorClick(ActionEvent actionEvent)
    {

    }
}
