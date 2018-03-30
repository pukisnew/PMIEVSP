package i11.michalkevicius.deividas.controller;

import i11.michalkevicius.deividas.controller.admin.AdminLoginStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class SpreadsheetApp extends Application
{
    private static Stage primaryStage;
    private static Stage currentStage;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SpreadsheetApp.primaryStage = primaryStage;
        switchStages(new AdminLoginStage());
    }

    private void switchStages(Stage stage)
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
}
