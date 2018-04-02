package i11.michalkevicius.deividas.controller.adminpanel;

import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminPanelController
{
    public void onNaudotojaiClick(ActionEvent actionEvent) throws IOException
    {
        SpreadsheetApp.launchUserManagement();
    }

    public void onDataClick(ActionEvent actionEvent) throws IOException
    {
        SpreadsheetApp.launchProductManagementStage();
    }

    public void onIseitiClick(ActionEvent actionEvent) throws IOException
    {
        SpreadsheetApp.launchMainStage();
    }
}
