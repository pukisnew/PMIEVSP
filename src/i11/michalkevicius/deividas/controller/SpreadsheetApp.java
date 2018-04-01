package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.controller.admin.AdminLoginStage;
import i11.michalkevicius.deividas.controller.adminpanel.AdminPanelStage;
import i11.michalkevicius.deividas.controller.main.MainStage;
import i11.michalkevicius.deividas.controller.productmanagement.ProductManagementStage;
import i11.michalkevicius.deividas.controller.usermanagement.UserManagementStage;
import i11.michalkevicius.deividas.controller.viewer.ViewerStage;
import i11.michalkevicius.deividas.model.Product;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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

    public static void launchMainStage() throws IOException
    {
        switchStages(new MainStage());
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SpreadsheetApp.primaryStage = primaryStage;
        launchMainStage();
    }

    public static void launchUserManagement() throws IOException
    {
        switchStages(new UserManagementStage());
    }

    public static void launchAdminPanelStage() throws IOException
    {
        switchStages(new AdminPanelStage());
    }

    public static void launchViewerStage(List<Product> products) throws IOException
    {
        switchStages(new ViewerStage(products));
    }

    public static void launchProductManagementStage() throws IOException
    {
        switchStages(new ProductManagementStage());
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
