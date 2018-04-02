package i11.michalkevicius.deividas.controller.viewer;

import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ViewerController implements Initializable
{
    public TableView<Product> table;
    public MenuBar bar;
    private ObservableList<Product> data = FXCollections.observableArrayList();

    public void setData(ObservableList<Product> data)
    {
        table.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        table.setItems(data);
        ObservableList<TableColumn<Product, ?>> columns = table.getColumns();
        List<String> propertyNames = Product.propertyNames();
        List<PropertyValueFactory<Product, String>> mappedColumns = propertyNames.parallelStream().map((Function<String, PropertyValueFactory<Product, String>>) PropertyValueFactory::new).collect(Collectors.toList());
        bar.getMenus().get(0).showingProperty().addListener(this::onMenuShown);
        IntStream.range(0, columns.size())
                .forEach((it) ->
                {
                    PropertyValueFactory<Product, String> factory = mappedColumns.get(it);
                    TableColumn<Product, String> column = (TableColumn<Product, String>) columns.get(it);
                    column.setCellValueFactory(factory);
                });
    }

    public void onBackPress(ActionEvent actionEvent)
    {
        try
        {
            data.clear();
            table.getColumns().clear();
            table = null;
            bar = null;
            SpreadsheetApp.launchMainStage();
        }
        catch (IOException err)
        {

        }
    }

    private void onMenuShown(Observable observable, boolean old, boolean newvalue)
    {
        if (newvalue)
            onBackPress(null);
    }

    public void setResizeables(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height)
    {
        width.addListener((obs, old, nu) -> table.setPrefWidth(nu.doubleValue() - 15));
        height.addListener((obs, old, nu) -> table.setPrefHeight(nu.doubleValue() - bar.getHeight() - 40));
    }
}
