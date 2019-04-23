package i11.michalkevicius.deividas.controller.viewer;

import i11.michalkevicius.deividas.ProductValueFactory;
import i11.michalkevicius.deividas.controller.R;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import i11.michalkevicius.deividas.model.Templatable;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
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
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        table.setItems(data);
        ObservableList<TableColumn<Product, ?>> columns = table.getColumns();
        List<String> propertyNames = Product.propertyNames();
        List<PropertyValueFactory<Product, String>> mappedColumns = propertyNames.parallelStream().map((Function<String, PropertyValueFactory<Product, String>>) ProductValueFactory::new).collect(Collectors.toList());
        IntStream.range(0, columns.size())
                .forEach((it) ->
                {
                    PropertyValueFactory<Product, String> factory = mappedColumns.get(it);
                    TableColumn<Product, String> column = (TableColumn<Product, String>) columns.get(it);
                    column.setCellValueFactory(factory);
                });
    }

    public void onBackPress(Event actionEvent)
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

    public void onSavePress(Event actionEvent) throws NumberFormatException, FileNotFoundException {
        TextInputDialog dialog = new TextInputDialog("result");
        dialog.setTitle("Išsaugoti");
        dialog.setHeaderText("Įrašykite failo pavadinimą");

        Optional<String> filanemResult = dialog.showAndWait();

        if (!filanemResult.isPresent())
            return;
        String filename = filanemResult.get() + ".doc";

        List<Templatable> rows = this
                .table.getItems()
                .stream()
                .map(Product::toPropertyMap)
                .reduce(new HashMap<String, String>(), (first, second) -> {
                    Map<String, String> result = new HashMap<>();
                    float coefficient = Float.parseFloat(second.computeIfAbsent("coefficient", (it) -> "1"));
                    second.remove("coefficient");
                    second.remove("name");
                    for (Map.Entry<String, String> it : second.entrySet()) {
                        float firstValue = new Float(first.computeIfAbsent(it.getKey(), this::getDefaultTemplateFloatValue));
                        float secondValue = 0;
                        try {
                            secondValue = new Float(it.getValue()) / coefficient;
                        } catch (NumberFormatException err) {
                            // probably a ?, treating as 9
                        }
                        result.put(it.getKey(), (firstValue + secondValue) + "");

                    }
                    return result;
                })
        .entrySet()
        .parallelStream()
        .map((it) -> {
            String translated = Product.getTranslation(it.getKey());
            float value = Float.parseFloat(it.getValue());
            int start = translated.lastIndexOf("(");
            int end = translated.lastIndexOf(")");
            Templatable template = new Templatable(R.DOCUMENT.ROW_RIGHT);
            template.set(Templatable.HEADER, translated);
            if(start >= 0 && end >= 0)
                template.set(Templatable.UNIT, translated.substring(start + 1, end));
            template.set(Templatable.KILOGRAM, String.format("%d", (int)(value * 1000)));
            template.set(Templatable.REGULAR, String.format("%d", (int)(value * 100)));
            template.set(Templatable.HALF, String.format("%d", (int)(value * 50)));
            template.set(Templatable.LAST_ROW, "no");
            return template;
        })
        .sequential()
        .sorted()
        .collect(Collectors.toList());
        for (int i = 0; i < rows.size(); i++) {
            Templatable row = rows.get(i);
            row.set(Templatable.ROW_INT, i+"");
            if(i == rows.size() - 1)
                row.set(Templatable.LAST_ROW, "yes");
        }
        Templatable wholetable = new Templatable(R.DOCUMENT.DOC_TABLE_TEMPLATE);
        Templatable rightTable = new Templatable(R.DOCUMENT.TABLE_RIGHT);
        rightTable.set(Templatable.ROWS, rows.stream().map(Templatable::toTemplate).collect(Collectors.joining(System.lineSeparator())));
        wholetable.set(Templatable.TABLE_RIGHT, rightTable.toTemplate());
        File f = new File(filename);
        PrintWriter pw = new PrintWriter(f);
        pw.println(wholetable.toTemplate());
        pw.close();
    }

    private String getDefaultTemplateFloatValue(String key) {
        return "0";
    }
}
