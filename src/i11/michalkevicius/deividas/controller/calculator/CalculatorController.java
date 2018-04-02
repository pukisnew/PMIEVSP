package i11.michalkevicius.deividas.controller.calculator;

import com.jfoenix.controls.JFXTextField;
import i11.michalkevicius.deividas.controller.Database;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculatorController implements Initializable
{
    public JFXTextField textFilter;
    public MenuBar menu;
    public ListView<Product> all_data;
    public ListView<Product> selected_items;
    public JFXTextField salt;
    public JFXTextField protein;
    public JFXTextField super_fat;
    public JFXTextField sugar;
    public JFXTextField fat;
    public JFXTextField carbonhydrates;
    public JFXTextField joules;
    public JFXTextField kcal;
    public JFXTextField fiber;
    private Product total = new Product();
    private Timeline t = new Timeline();
    private ObservableList<Product> data = FXCollections.observableArrayList();
    private ObservableList<Product> selectedData = FXCollections.observableArrayList();
    private List<Product> allData = Database.getProducts();
    private String lastFilter = "";

    public CalculatorController() throws SQLException {}

    private void onCalculate(ListChangeListener.Change<? extends Product> c)
    {
        total = selectedData
                .stream()
                .reduce((product, product2) ->
                        {
                            Product target = new Product();
                            List<StringProperty> p1 = product.bindableProperties();
                            List<StringProperty> p2 = product2.bindableProperties();
                            List<StringProperty> targetProperties = target.bindableProperties();
                            p1.remove(0);
                            p2.remove(0);
                            targetProperties.remove(0);
                            IntStream
                                    .range(0, p1.size())
                                    .mapToObj((it) -> new Tuple<StringProperty, StringProperty, Integer>(p1.get(it), p2.get(it), it))
                                    .map((it) -> new Tuple<String, String, Integer>(it.key.getValue(), it.value.getValue(), it.index))
                                    .map((it) -> new Pair<Integer, Float>(it.index, new Float(it.key) + new Float(it.value)))
                                    .forEach((it) -> targetProperties.get(it.key).set(it.value.toString()));
                            return target;
                        }
                ).orElse(new Product());
        unbindCurrentProduct();
        bindCurrentProduct();

    }

    public void onBackPress(ActionEvent actionEvent)
    {
        try
        {
            data.clear();
            selectedData.clear();
            t.stop();
            SpreadsheetApp.launchMainStage();
        }
        catch (IOException err)
        {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        bindCurrentProduct();
        initDataSet();
        all_data.setItems(data);
        selected_items.setItems(selectedData);
        all_data.getSelectionModel().selectedItemProperty().addListener(this::onDataAdd);
        selected_items.getSelectionModel().selectedItemProperty().addListener(this::onDataRemove);
        menu.getMenus().get(0).showingProperty().addListener(this::onMenuShown);
        menu.getMenus().get(1).showingProperty().addListener(this::onMoreInfo);
        selectedData.addListener(this::onCalculate);
        t.getKeyFrames().add(new KeyFrame(Duration.seconds(1), this::onPoll));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    private void onMenuShown(Observable observable, boolean old, boolean newvalue)
    {
        if (newvalue)
            onBackPress(null);
    }

    private void onMoreInfo(Observable observable, boolean old, boolean newvalue)
    {
        try
        {
            if (newvalue)
                SpreadsheetApp.launchViewerStage(selectedData, false);
            menu.getMenus().get(1).hide();
        }
        catch (IOException err)
        {

        }
    }

    private void onDataAdd(ObservableValue<? extends Product> observable, Product old, Product nu)
    {
        if (nu == null)
            return;
        TextInputDialog dialog = new TextInputDialog("100");
        dialog.setTitle("");
        dialog.setHeaderText("Reikalinga masė gramais");
        dialog.setContentText(nu.getName() + ":");

        Optional<String> result = dialog.showAndWait();

        Platform.runLater(() ->
        {
            if (!result.isPresent())
                return;
            String value = result.get();
            if (!value.matches("(\\d+(\\.\\d+)?)?"))
                return;
            float coef = new Float(result.get());
            if (coef < 0)
                return;
            Product clone = new Product(nu, coef / 100);
            clone.setName(clone.getName() + " (" + coef + " g)");
            selectedData.add(clone);
            all_data.getSelectionModel().clearSelection();
        });
    }

    private void onPoll(ActionEvent e)
    {
        System.err.println("Polling");
        String currentText = textFilter.getText();
        if (!lastFilter.equals(currentText))
        {
            lastFilter = currentText;
            filter(currentText);
        }
    }

    private void filter(String textFilter)
    {
        List<Product> result = allData;
        if (textFilter.length() != 0)
        {
            result = result.parallelStream().filter((it) -> it.getName() != null && it.getName().toLowerCase().contains(textFilter)).collect(Collectors.toList());
        }
        showResults(result);
    }

    private void initDataSet()
    {
        showResults(allData);
    }

    private void showResults(List<Product> data)
    {
        this.data.clear();
        //filtered.add(NEW_PRODUCT);
        this.data.addAll(data);
    }

    private void onDataRemove(ObservableValue<? extends Product> observable, Product old, Product nu)
    {
        if (nu == null)
            return;
        Platform.runLater(() ->
        {
            //ObservableList<Product> newList = FXCollections.observableArrayList();
            //newList.addListener(this::onCalculate);
            //selected_items.setItems(newList);
            selected_items.getSelectionModel().clearSelection();
            selectedData.remove(nu);
            //newList.addAll(selectedData);
            //selectedData = newList;
        });
    }

    private void bindCurrentProduct()
    {
        salt.textProperty().bind(total.saltProperty());
        protein.textProperty().bind(total.proteinProperty());
        super_fat.textProperty().bind(total.superFatProperty());
        sugar.textProperty().bind(total.sugarProperty());
        fat.textProperty().bind(total.fatProperty());
        carbonhydrates.textProperty().bind(total.carbohydratesProperty());
        joules.textProperty().bind(total.kjProperty());
        kcal.textProperty().bind(total.kcalProperty());
        fiber.textProperty().bind(total.fiberProperty());
    }

    private void unbindCurrentProduct()
    {
        salt.textProperty().unbind();
        protein.textProperty().unbind();
        super_fat.textProperty().unbind();
        sugar.textProperty().unbind();
        fat.textProperty().unbind();
        carbonhydrates.textProperty().unbind();
        joules.textProperty().unbind();
        kcal.textProperty().unbind();
        fiber.textProperty().unbind();
    }

    private static class Tuple<K, V, I> extends Pair<K, V>
    {
        protected final I index;

        Tuple(K key, V value, I index)
        {
            super(key, value);
            this.index = index;
        }
    }

    private static class Pair<K, V>
    {
        protected final K key;
        protected final V value;

        Pair(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
