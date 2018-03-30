package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.controller.admin.AdminLoginStage;
import i11.michalkevicius.deividas.controller.adminpanel.AdminPanelStage;
import i11.michalkevicius.deividas.controller.usermanagement.UserManagementStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SpreadsheetApp extends Application
{
    private static Stage primaryStage;
    private static Stage currentStage;

    public static void main(String[] args)
    {
        launch(args);
    }

    public static void launchAdminLoginStage() throws IOException
    {
        switchStages(new AdminLoginStage());
    }

    private static void switchStages(Stage stage)
    {
        if (currentStage != null)
            currentStage.close();
        currentStage = stage;
        currentStage.initOwner(primaryStage);
        currentStage.show();
    }

    @Override
    public void init() throws Exception
    {
        Database.executeTransaction((Database.Transaction<Void>) c -> null);
        //app = this;
    }

    @Override
    public void stop() throws Exception
    {
        Database.executeTransaction((Database.Transaction<Void>) c ->
        {
            c.close();
            return null;
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SpreadsheetApp.primaryStage = primaryStage;
        launchAdminLoginStage();
    }

    public static void launchUserManagement() throws IOException
    {
        switchStages(new UserManagementStage());
    }

    public static void launchAdminPanelStage() throws IOException
    {
        switchStages(new AdminPanelStage());
    }

/*    public static void launchAdminUserManagement()
    {
        switchStages(new AdminUserManagemenetStage());
    }

    public static void launchDataManagement()
    {
        switchStages(new DataManagementStage());
    }*/
}
