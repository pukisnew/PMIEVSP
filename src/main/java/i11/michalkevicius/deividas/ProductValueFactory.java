package i11.michalkevicius.deividas;

import i11.michalkevicius.deividas.model.Product;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductValueFactory extends PropertyValueFactory<Product, String> {
    /**
     * Creates a default PropertyValueFactory to extract the value from a given
     * TableView row item reflectively, using the given property name.
     *
     * @param property The name of the property with which to attempt to
     *                 reflectively extract a corresponding value for in a given object.
     */
    public ProductValueFactory(String property) {
        super(property);
    }

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
        return param.getValue().getStringProperty(this.getProperty());
    }
}
