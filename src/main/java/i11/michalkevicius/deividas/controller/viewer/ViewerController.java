package i11.michalkevicius.deividas.controller.viewer;

import i11.michalkevicius.deividas.ProductValueFactory;
import i11.michalkevicius.deividas.controller.R;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import i11.michalkevicius.deividas.model.Templatable;
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

public class ViewerController implements Initializable {
    public TableView<Product> table;
    public MenuBar bar;
    private ObservableList<Product> data = FXCollections.observableArrayList();

    public void setData(ObservableList<Product> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(data);
        ObservableList<TableColumn<Product, ?>> columns = table.getColumns();
        List<String> propertyNames = Product.propertyNames();
        List<PropertyValueFactory<Product, String>> mappedColumns = propertyNames
                .parallelStream()
                .map((Function<String, PropertyValueFactory<Product, String>>) ProductValueFactory::new)
                .collect(Collectors.toList());
        IntStream.range(0, columns.size())
                .forEach((it) ->
                {
                    PropertyValueFactory<Product, String> factory = mappedColumns.get(it);
                    TableColumn<Product, String> column = (TableColumn<Product, String>) columns.get(it);
                    column.setCellValueFactory(factory);
                });
    }

    public void onBackPress(Event actionEvent) {
        try {
            data.clear();
            table.getColumns().clear();
            table = null;
            bar = null;
            SpreadsheetApp.launchMainStage();
        } catch (IOException err) {

        }
    }

    private void onMenuShown(Observable observable, boolean old, boolean newvalue) {
        if (newvalue)
            onBackPress(null);
    }

    public void setResizeables(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height) {
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

        Map<String, String> mappedRows = this
                .table.getItems()
                .stream()
                .map(Product::toPropertyMap)
                .reduce(new LinkedHashMap<>(), (first, second) -> {
                    Map<String, String> result = new LinkedHashMap<>();
                    float coefficient = Float.parseFloat(second.computeIfAbsent("coefficient", (it) -> "1"));
                    second.remove("coefficient");
                    second.remove("name");
                    for (Map.Entry<String, String> it : second.entrySet()) {
                        float firstValue = new Float(first.computeIfAbsent(it.getKey(), this::getDefaultTemplateFloatValue));
                        float secondValue = new Float(it.getValue()) / coefficient;
                        result.put(it.getKey(), (firstValue + secondValue) + "");
                    }
                    return result;
                });
        List<Templatable> rows = mappedRows
                .entrySet()
                .stream()
                .map(new EntryToTemplateRowConsumer(R.DOCUMENT.ROW_RIGHT, true, false))
                .collect(Collectors.toList());
        for (int i = 0; i < rows.size(); i++) {
            Templatable row = rows.get(i);
            row.set(Templatable.ROW_INT, i + "");
        }
        rows.get(rows.size() - 1).set(Templatable.LAST_ROW, "yes");
        Map<String, Function<String, String>> requiredKeys = new LinkedHashMap<>();
        Function<String, String> defaultDataFunction = new SupplementaryDataFunction(mappedRows);
        Function<String, String> compositeSugarFunction = new CompositeSugarFunction(mappedRows);
        requiredKeys.put("kcal", defaultDataFunction);
        requiredKeys.put("kj", defaultDataFunction);
        requiredKeys.put("fat", defaultDataFunction);
        requiredKeys.put("superFat", defaultDataFunction);
        requiredKeys.put("carbohydrates", defaultDataFunction);
        requiredKeys.put("sugars", compositeSugarFunction);
        requiredKeys.put("fiber", defaultDataFunction);
        requiredKeys.put("protein", defaultDataFunction);
        requiredKeys.put("salt", defaultDataFunction);

        List<Templatable> leftRows = requiredKeys
                .entrySet()
                .stream()

                //.map((String key, Function<String, String> value) -> value.apply(key))
                .map(EntryImplementation::new)
                .map(new EntryToTemplateRowConsumer(R.DOCUMENT.ROW_LEFT, false, true))
                .collect(Collectors.toList());

        //Map<String, String> leftTableData = new HashMap<>();


        Templatable wholetable = new Templatable(R.DOCUMENT.DOC_TABLE_TEMPLATE);
        Templatable leftTable = new Templatable(R.DOCUMENT.TABLE_LEFT);
        Templatable rightTable = new Templatable(R.DOCUMENT.TABLE_RIGHT);
        leftTable.set(Templatable.ROWS, leftRows.stream().map(Templatable::toTemplate).collect(Collectors.joining(System.lineSeparator())));
        rightTable.set(Templatable.ROWS, rows.stream().map(Templatable::toTemplate).collect(Collectors.joining(System.lineSeparator())));
        wholetable.set(Templatable.TABLE_RIGHT, rightTable.toTemplate());
        wholetable.set(Templatable.TABLE_LEFT, leftTable.toTemplate());
        File f = new File(filename);
        PrintWriter pw = new PrintWriter(f);
        pw.println(wholetable.toTemplate());
        pw.close();
    }

    private static class EntryImplementation implements Map.Entry<String, String> {
        private final String key;
        private final String value;

        public EntryImplementation(Map.Entry<String, Function<String, String>> stringFunctionEntry) {
            Function<String, String> function = stringFunctionEntry.getValue();
            String key = stringFunctionEntry.getKey();
            this.value = function.apply(key);
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            return null;
        }
    }

    private static class SupplementaryDataFunction implements Function<String, String> {
        private final Map<String, String> data;
        SupplementaryDataFunction(Map<String, String> data) {
            this.data = data;
        }
        @Override
        public String apply(String s) {
            return data.get(s);
        }
    }

    private static class CompositeSugarFunction implements Function<String, String> {
        private final Map<String, String> data;

        CompositeSugarFunction(Map<String, String> data) {
            this.data = data;
        }

        @Override
        public String apply(String s) {
            Float monosacharids = new Float(data.get("monosacharids"));
            Float disacharids = new Float(data.get("disacharids"));
            Float sugar = new Float(data.get("sugar"));
            return (monosacharids +
            disacharids +
            sugar) + "";
        }
    }

    private static class EntryToTemplateRowConsumer implements Function<Map.Entry<String, String>, Templatable> {

        private final String template;
        private final boolean includeHalfValues;
        private final boolean useLeftTranslations;

        EntryToTemplateRowConsumer(String document, boolean halfValues, boolean useLeftTranslations) {
            this.template = document;
            this.includeHalfValues = halfValues;
            this.useLeftTranslations = useLeftTranslations;
        }

        @Override
        public Templatable apply(Map.Entry<String, String> it) {
            String key = it.getKey();
            String originalTranslation = Product.getTranslation(key);
            if(useLeftTranslations) {
                key += "_l";
            }
            String translated = Product.getTranslation(key);
            float value = Float.parseFloat(it.getValue());
            int start = originalTranslation.lastIndexOf("(");
            int end = originalTranslation.lastIndexOf(")");
            Templatable template = new Templatable(this.template);
            template.set(Templatable.HEADER, translated);
            if (start >= 0 && end >= 0)
                template.set(Templatable.UNIT, originalTranslation.substring(start + 1, end));
            else
                template.set(Templatable.UNIT, "");
            template.set(Templatable.KILOGRAM, String.format("%d", (int) (value)));
            template.set(Templatable.REGULAR, String.format("%d", (int) (value / 10)));
            if(includeHalfValues)
                template.set(Templatable.HALF, String.format("%d", (int) (value / 20)));
            template.set(Templatable.LAST_ROW, "no");
            return template;
        }
    }

    private String getDefaultTemplateFloatValue(String key) {
        return "0";
    }
}
