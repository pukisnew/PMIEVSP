package i11.michalkevicius.deividas.controller.productmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import i11.michalkevicius.deividas.controller.Database;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.Product;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductManagementController implements Initializable, ChangeListener<Product>
{
    private final Product NEW_PRODUCT = new Product();
    public JFXButton deleteButton;
    public JFXButton saveButton;
    public ListView<Product> listview;
    public JFXTextField textFilter;
    public MenuBar menu;
    public JFXTextField pavadinimas;
    public JFXTextField energija__kcal_;
    public JFXTextField energija__kj_;
    public JFXTextField angliavandeniai__g_;
    public JFXTextField riebalai__g_;
    public JFXTextField baltymai__g_;
    public JFXTextField skaidulines_medziagos_g_;
    public JFXTextField vanduo__g_;
    public JFXTextField alkoholis__g_;
    public JFXTextField pelenai__g_;
    public JFXTextField monosacharidai__g_;
    public JFXTextField disacharidai__g_;
    public JFXTextField saharoze__g_;
    public JFXTextField viso_grudo__viso__g_;
    public JFXTextField cukrus__viso__g_;
    public JFXTextField sociosios_riebiosios_rugstys__g_;
    public JFXTextField riebiosios_rugstys_4_0_10_0__g_;
    public JFXTextField riebiosios_rugstys_12_0__g_;
    public JFXTextField riebiosios_rugstys_14_0__g_;
    public JFXTextField riebiosios_rugstys_16_0__g_;
    public JFXTextField riebiosios_rugstys_18_0__g_;
    public JFXTextField riebiosios_rugstys_20_0__g_;
    public JFXTextField mononesociosios_riebiosios_rugstys_g_;
    public JFXTextField riebiosios_rugstys_16_1__g_;
    public JFXTextField riebiosios_rugstys_18_1__g_;
    public JFXTextField polinesociosios_riebiosios_rugstys__g_;
    public JFXTextField riebiosios_rugstys_18_2__g_;
    public JFXTextField riebiosios_rugstys_18_3__g_;
    public JFXTextField riebiosios_rugstys_20_4__g_;
    public JFXTextField epa__riebiosios_rugstys_20_5___g_;
    public JFXTextField dpa__riebiosios_rugstys_22_5___g_;
    public JFXTextField dha__riebiosios_rugstys_22_6___g_;
    public JFXTextField cholesterolis__mg_;
    public JFXTextField retinolis__ug_;
    public JFXTextField retinol_ekvivalentas__ug_;
    public JFXTextField beta_karotenas__ug_;
    public JFXTextField vitaminas_d__ug_;
    public JFXTextField vitaminas_e__mg_;
    public JFXTextField vitaminas_k__ug_;
    public JFXTextField tiaminas__mg_;
    public JFXTextField riboflavinas__mg_;
    public JFXTextField vitaminas_c__mg_;
    public JFXTextField niacinas__mg_;
    public JFXTextField niacino_ekvivalentas__mg_;
    public JFXTextField vitaminas_b_6__mg_;
    public JFXTextField vitaminas_b_12__ug_;
    public JFXTextField folatas__ug_;
    public JFXTextField fosforas__mg_;
    public JFXTextField jodas__ug_;
    public JFXTextField gelezis__mg_;
    public JFXTextField kalcis__mg_;
    public JFXTextField kalis__mg_;
    public JFXTextField magnis__mg_;
    public JFXTextField natris__mg_;
    public JFXTextField druska__g_;
    public JFXTextField selenas__ug_;
    public JFXTextField cinkas__mg_;
    public JFXTextField atliekos__pvz__lupenos____;
    public JFXTextField valgomosios_dalies_koeficientas;
    public JFXTextField sausuju_medziagu__g_;
    public JFXTextField gyvuniniu_baltymu__g_;
    public JFXTextField augaliniu_baltymu__g_;
    public JFXTextField krakmolo__g_;
    private List<Product> data = Database.getProducts();
    private ObservableList<Product> filtered = FXCollections.observableArrayList();
    private Timeline t = new Timeline();
    private String lastFilter = "";
    private Product currentProduct = NEW_PRODUCT;
    private HashMap<JFXTextField, ChangeListenerWithReference> cache = new HashMap<>();

    public ProductManagementController() throws SQLException {}

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initDataSet();
        listview.getSelectionModel().selectedItemProperty().addListener(this);
        listview.setItems(filtered);
        listview.getSelectionModel().select(0);

        menu.getMenus().get(0).showingProperty().addListener(this::onMenuShown);

        t.getKeyFrames().add(new KeyFrame(Duration.seconds(1), this::onPoll));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        initFilters();

        NEW_PRODUCT.setName("<Naujas>");
    }

    private void onMenuShown(Observable observable, boolean old, boolean newvalue)
    {
        if (newvalue)
            onBackPress(null);
    }

    private void initFilters()
    {
        initFilter(pavadinimas);
        initFilter(energija__kcal_);
        initFilter(energija__kj_);
        initFilter(angliavandeniai__g_);
        initFilter(riebalai__g_);
        initFilter(baltymai__g_);
        initFilter(skaidulines_medziagos_g_);
        initFilter(vanduo__g_);
        initFilter(alkoholis__g_);
        initFilter(pelenai__g_);
        initFilter(monosacharidai__g_);
        initFilter(disacharidai__g_);
        initFilter(saharoze__g_);
        initFilter(viso_grudo__viso__g_);
        initFilter(cukrus__viso__g_);
        initFilter(sociosios_riebiosios_rugstys__g_);
        initFilter(riebiosios_rugstys_4_0_10_0__g_);
        initFilter(riebiosios_rugstys_12_0__g_);
        initFilter(riebiosios_rugstys_14_0__g_);
        initFilter(riebiosios_rugstys_16_0__g_);
        initFilter(riebiosios_rugstys_18_0__g_);
        initFilter(riebiosios_rugstys_20_0__g_);
        initFilter(mononesociosios_riebiosios_rugstys_g_);
        initFilter(riebiosios_rugstys_16_1__g_);
        initFilter(riebiosios_rugstys_18_1__g_);
        initFilter(polinesociosios_riebiosios_rugstys__g_);
        initFilter(riebiosios_rugstys_18_2__g_);
        initFilter(riebiosios_rugstys_18_3__g_);
        initFilter(riebiosios_rugstys_20_4__g_);
        initFilter(epa__riebiosios_rugstys_20_5___g_);
        initFilter(dpa__riebiosios_rugstys_22_5___g_);
        initFilter(dha__riebiosios_rugstys_22_6___g_);
        initFilter(cholesterolis__mg_);
        initFilter(retinolis__ug_);
        initFilter(retinol_ekvivalentas__ug_);
        initFilter(beta_karotenas__ug_);
        initFilter(vitaminas_d__ug_);
        initFilter(vitaminas_e__mg_);
        initFilter(vitaminas_k__ug_);
        initFilter(tiaminas__mg_);
        initFilter(riboflavinas__mg_);
        initFilter(vitaminas_c__mg_);
        initFilter(niacinas__mg_);
        initFilter(niacino_ekvivalentas__mg_);
        initFilter(vitaminas_b_6__mg_);
        initFilter(vitaminas_b_12__ug_);
        initFilter(folatas__ug_);
        initFilter(fosforas__mg_);
        initFilter(jodas__ug_);
        initFilter(gelezis__mg_);
        initFilter(kalcis__mg_);
        initFilter(kalis__mg_);
        initFilter(magnis__mg_);
        initFilter(natris__mg_);
        initFilter(druska__g_);
        initFilter(selenas__ug_);
        initFilter(cinkas__mg_);
        initFilter(atliekos__pvz__lupenos____);
        initFilter(valgomosios_dalies_koeficientas);
        initFilter(sausuju_medziagu__g_);
        initFilter(gyvuniniu_baltymu__g_);
        initFilter(augaliniu_baltymu__g_);
        initFilter(krakmolo__g_);
    }

    private void bindProduct(Product currentProduct)
    {
        this.currentProduct = currentProduct;
        initPropogator(pavadinimas, currentProduct.getStringProperty("name"));
        initPropogator(energija__kcal_, currentProduct.getStringProperty("kcal"));
        initPropogator(energija__kj_, currentProduct.getStringProperty("kj"));
        initPropogator(angliavandeniai__g_, currentProduct.getStringProperty("carbohydrates"));
        initPropogator(riebalai__g_, currentProduct.getStringProperty("fat"));
        initPropogator(baltymai__g_, currentProduct.getStringProperty("protein"));
        initPropogator(skaidulines_medziagos_g_, currentProduct.getStringProperty("fiber"));
        initPropogator(vanduo__g_, currentProduct.getStringProperty("water"));
        initPropogator(alkoholis__g_, currentProduct.getStringProperty("alcohol"));
        initPropogator(pelenai__g_, currentProduct.getStringProperty("ash"));
        initPropogator(monosacharidai__g_, currentProduct.getStringProperty("monosacharids"));
        initPropogator(disacharidai__g_, currentProduct.getStringProperty("disacharids"));
        initPropogator(saharoze__g_, currentProduct.getStringProperty("fakeSuger"));
        initPropogator(viso_grudo__viso__g_, currentProduct.getStringProperty("grain"));
        initPropogator(cukrus__viso__g_, currentProduct.getStringProperty("sugar"));
        initPropogator(sociosios_riebiosios_rugstys__g_, currentProduct.getStringProperty("superFat"));
        initPropogator(riebiosios_rugstys_4_0_10_0__g_, currentProduct.getStringProperty("fat4_10"));
        initPropogator(riebiosios_rugstys_12_0__g_, currentProduct.getStringProperty("fat12"));
        initPropogator(riebiosios_rugstys_14_0__g_, currentProduct.getStringProperty("fat14"));
        initPropogator(riebiosios_rugstys_16_0__g_, currentProduct.getStringProperty("fat16"));
        initPropogator(riebiosios_rugstys_18_0__g_, currentProduct.getStringProperty("fat18"));
        initPropogator(riebiosios_rugstys_20_0__g_, currentProduct.getStringProperty("fat20"));
        initPropogator(mononesociosios_riebiosios_rugstys_g_, currentProduct.getStringProperty("monoSuperFat"));
        initPropogator(riebiosios_rugstys_16_1__g_, currentProduct.getStringProperty("fat16_1"));
        initPropogator(riebiosios_rugstys_18_1__g_, currentProduct.getStringProperty("fat18_1"));
        initPropogator(polinesociosios_riebiosios_rugstys__g_, currentProduct.getStringProperty("poliSuperFat"));
        initPropogator(riebiosios_rugstys_18_2__g_, currentProduct.getStringProperty("fat18_2"));
        initPropogator(riebiosios_rugstys_18_3__g_, currentProduct.getStringProperty("fat18_3"));
        initPropogator(riebiosios_rugstys_20_4__g_, currentProduct.getStringProperty("fat20_4"));
        initPropogator(epa__riebiosios_rugstys_20_5___g_, currentProduct.getStringProperty("epa"));
        initPropogator(dpa__riebiosios_rugstys_22_5___g_, currentProduct.getStringProperty("dpa"));
        initPropogator(dha__riebiosios_rugstys_22_6___g_, currentProduct.getStringProperty("dha"));
        initPropogator(cholesterolis__mg_, currentProduct.getStringProperty("cholesterol"));
        initPropogator(retinolis__ug_, currentProduct.getStringProperty("retinol"));
        initPropogator(retinol_ekvivalentas__ug_, currentProduct.getStringProperty("retinolequivalent"));
        initPropogator(beta_karotenas__ug_, currentProduct.getStringProperty("betakaroten"));
        initPropogator(vitaminas_d__ug_, currentProduct.getStringProperty("vit_d"));
        initPropogator(vitaminas_e__mg_, currentProduct.getStringProperty("vit_e"));
        initPropogator(vitaminas_k__ug_, currentProduct.getStringProperty("vit_k"));
        initPropogator(tiaminas__mg_, currentProduct.getStringProperty("tiamin"));
        initPropogator(riboflavinas__mg_, currentProduct.getStringProperty("riboflavin"));
        initPropogator(vitaminas_c__mg_, currentProduct.getStringProperty("vit_c"));
        initPropogator(niacinas__mg_, currentProduct.getStringProperty("niacin"));
        initPropogator(niacino_ekvivalentas__mg_, currentProduct.getStringProperty("niacinequivalent"));
        initPropogator(vitaminas_b_6__mg_, currentProduct.getStringProperty("vit_b_6"));
        initPropogator(vitaminas_b_12__ug_, currentProduct.getStringProperty("vit_b_12"));
        initPropogator(folatas__ug_, currentProduct.getStringProperty("folate"));
        initPropogator(fosforas__mg_, currentProduct.getStringProperty("phosphorus"));
        initPropogator(jodas__ug_, currentProduct.getStringProperty("iodine"));
        initPropogator(gelezis__mg_, currentProduct.getStringProperty("iron"));
        initPropogator(kalcis__mg_, currentProduct.getStringProperty("calcium"));
        initPropogator(kalis__mg_, currentProduct.getStringProperty("potassium"));
        initPropogator(magnis__mg_, currentProduct.getStringProperty("magnesium"));
        initPropogator(natris__mg_, currentProduct.getStringProperty("sodium"));
        initPropogator(druska__g_, currentProduct.getStringProperty("salt"));
        initPropogator(selenas__ug_, currentProduct.getStringProperty("selenium"));
        initPropogator(cinkas__mg_, currentProduct.getStringProperty("zinc"));
        initPropogator(atliekos__pvz__lupenos____, currentProduct.getStringProperty("rest"));
        initPropogator(valgomosios_dalies_koeficientas, currentProduct.getStringProperty("edible_coefficient"));
        initPropogator(sausuju_medziagu__g_, currentProduct.getStringProperty("dry_material"));
        initPropogator(gyvuniniu_baltymu__g_, currentProduct.getStringProperty("animal_protein"));
        initPropogator(augaliniu_baltymu__g_, currentProduct.getStringProperty("natural_protein"));
        initPropogator(krakmolo__g_, currentProduct.getStringProperty("starch"));
    }

    private void initFilter(TextField it)
    {
        if (it != pavadinimas)
        {
            ChangeListenerWithReference filter = new ChangeListenerWithReference(it.textProperty());
            it.textProperty().addListener(filter);
        }
    }

    private void initPropogator(JFXTextField it, StringProperty s)
    {
        ChangeListenerWithReference listenerWithReference = cache.get(it);
        if (listenerWithReference != null)
            it.textProperty().removeListener(listenerWithReference);
        if (it == pavadinimas)
            listenerWithReference = new SimpleChangeListenerWithReference(s, true);
        else
            listenerWithReference = new ChangeListenerWithReference(s, true);
        it.textProperty().setValue(s.getValue());
        it.textProperty().addListener(listenerWithReference);
        cache.put(it, listenerWithReference);
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
        List<Product> result = data;
        if (textFilter.length() != 0)
        {
            result = result.parallelStream().filter((it) -> it.getName() != null && it.getName().toLowerCase().contains(textFilter)).collect(Collectors.toList());
        }
        showResults(result);
    }

    private void initDataSet()
    {
        showResults(data);
    }

    private void showResults(List<Product> data)
    {
        filtered.clear();
        filtered.add(NEW_PRODUCT);
        filtered.addAll(data);
    }

    @Override
    public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue)
    {

        if (newValue == NEW_PRODUCT)
        {
            bindProduct(new Product());
            deleteButton.setVisible(false);
        }
        else if (newValue != null)
        {
            bindProduct(newValue);
            deleteButton.setVisible(true);
        }
    }

    public void onBackPress(Event actionEvent)
    {
        try
        {
            data.clear();
            filtered.clear();
            cache.clear();
            cache = null;
            listview = null;
            textFilter = null;
            menu = null;
            data = null;
            filtered = null;
            lastFilter = null;
            t.stop();
            t = null;
            SpreadsheetApp.launchAdminPanelStage();
        }
        catch (IOException err)
        {

        }
    }

    public void onSaveClick(ActionEvent actionEvent)
    {
        try
        {
            if (currentProduct.getId() == null)
            {
                data.add(currentProduct);
                textFilter.setText("");
                listview.getSelectionModel().select(filtered.indexOf(currentProduct));
            }
            else
                filtered.set(filtered.indexOf(currentProduct), currentProduct);
            Database.createProduct(currentProduct);

        }
        catch (SQLException e)
        {
            new Alert(Alert.AlertType.ERROR, "Išsaugoti nepavyko! " + e.getMessage());
        }
    }

    public void onDeleteClick(ActionEvent actionEvent)
    {
        try
        {
            Database.removeProduct(currentProduct);
            data.remove(currentProduct);
            filtered.remove(currentProduct);
            listview.getSelectionModel().select(0);
        }
        catch (SQLException e)
        {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private class ChangeListenerWithReference implements ChangeListener<String>
    {
        //private final TextField t;
        protected final StringProperty s;
        protected final boolean setValue;

        public ChangeListenerWithReference(StringProperty s)
        {
            this(s, false);
        }

        public ChangeListenerWithReference(StringProperty s, boolean setValue)
        {
            //this.t = t;
            this.s = s;
            this.setValue = setValue;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String old, String nu)
        {
            //System.out.println(s + old + nu);
            boolean newValueIsValid = nu.matches("(\\d+(\\.\\d+)?)?");
            boolean oldValueIsValid = old.matches("(\\d+(\\.\\d+)?)?");
            if (!newValueIsValid)
            {
                if (oldValueIsValid)
                {
                    s.setValue(old);
                }
            }
            if (newValueIsValid && setValue)
                s.setValue(nu);
        }
    }

    private class SimpleChangeListenerWithReference extends ChangeListenerWithReference
    {

        public SimpleChangeListenerWithReference(StringProperty s)
        {
            super(s);
        }

        public SimpleChangeListenerWithReference(StringProperty s, boolean setValue)
        {
            super(s, setValue);
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String old, String nu)
        {
            s.setValue(nu);
        }
    }
}
