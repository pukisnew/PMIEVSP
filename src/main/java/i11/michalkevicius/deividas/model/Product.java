package i11.michalkevicius.deividas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Product {
    private float coefficient = 1f;
    private String id = "";
    private Map<String, String> properties = null;
    private Map<String, StringProperty> stringProperties = null;
    private static final Map<String, String> translations = new HashMap<>();

    static {
        //translations.put();
        translations.put("Pavadinimas", "name");
        translations.put("Energija (kcal)", "kcal");
        translations.put("Energija (kJ)", "kj");
        translations.put("fat", "Riebalai (g)");
        translations.put("name", "Pavadinimas");
        translations.put("kcal", "Energija (kcal)");
        translations.put("kj", "Energija (kJ)");
        translations.put("carbohydrates", "Angliavandeniai (g)");
        translations.put("Angliavandeniai (g)", "carbohydrates");
        translations.put("Riebalai (g)", "fat");
        translations.put("protein", "Baltymai (g)");
        translations.put("Baltymai (g)", "protein");
        translations.put("fiber", "Skaidulinės medžiagos(g)");
        translations.put("Skaidulinės medžiagos(g)", "fiber");
        translations.put("water", "Vanduo (g)");
        translations.put("Vanduo (g)", "water");
        translations.put("alcohol", "Alkoholis (g)");
        translations.put("Alkoholis (g)", "alcohol");
        translations.put("ash", "Pelenai (g)");
        translations.put("Pelenai (g)", "ash");
        translations.put("monosacharids", "Monosacharidai (g)");
        translations.put("Monosacharidai (g)", "monosacharids");
        translations.put("disacharids", "Disacharidai (g)");
        translations.put("Disacharidai (g)", "disacharids");
        translations.put("fakeSuger", "Saharozė (g)");
        translations.put("Saharozė (g)", "fakeSuger");
        translations.put("grain", "Viso grūdo, viso (g)");
        translations.put("Viso grūdo, viso (g)", "grain");
        translations.put("sugar", "Cukrus, viso (g)");
        translations.put("Cukrus, viso (g)", "sugar");
        translations.put("superFat", "Sočiosios riebiosios rūgštys (g)");
        translations.put("Sočiosios riebiosios rūgštys (g)", "superFat");
        translations.put("fat4_10", "Riebiosios rūgštys 4:0-10:0 (g)");
        translations.put("Riebiosios rūgštys 4:0-10:0 (g)", "fat4_10");
        translations.put("fat12", "Riebiosios rūgštys 12:0 (g)");
        translations.put("Riebiosios rūgštys 12:0 (g)", "fat12");
        translations.put("fat14", "Riebiosios rūgštys 14:0 (g)");
        translations.put("Riebiosios rūgštys 14:0 (g)", "fat14");
        translations.put("fat16", "Riebiosios rūgštys 16:0 (g)");
        translations.put("Riebiosios rūgštys 16:0 (g)", "fat16");
        translations.put("fat18", "Riebiosios rūgštys 18:0 (g)");
        translations.put("Riebiosios rūgštys 18:0 (g)", "fat18");
        translations.put("fat20", "Riebiosios rūgštys 20:0 (g)");
        translations.put("Riebiosios rūgštys 20:0 (g)", "fat20");
        translations.put("monoSuperFat", "Mononesočiosios riebiosios rūgštys(g)");
        translations.put("Mononesočiosios riebiosios rūgštys(g)", "monoSuperFat");
        translations.put("fat16_1", "Riebiosios rūgštys 16:1 (g)");
        translations.put("Riebiosios rūgštys 16:1 (g)", "fat16_1");
        translations.put("fat18_1", "Riebiosios rūgštys 18:1 (g)");
        translations.put("Riebiosios rūgštys 18:1 (g)", "fat18_1");
        translations.put("poliSuperFat", "Polinesočiosios riebiosios rūgštys (g)");
        translations.put("Polinesočiosios riebiosios rūgštys (g)", "poliSuperFat");
        translations.put("fat18_2", "Riebiosios rūgštys 18:2 (g)");
        translations.put("Riebiosios rūgštys 18:2 (g)", "fat18_2");
        translations.put("fat18_3", "Riebiosios rūgštys 18:3 (g)");
        translations.put("Riebiosios rūgštys 18:3 (g)", "fat18_3");
        translations.put("fat20_4", "Riebiosios rūgštys 20:4 (g)");
        translations.put("Riebiosios rūgštys 20:4 (g)", "fat20_4");
        translations.put("epa", "EPA (Riebiosios rūgštys 20:5) (g)");
        translations.put("EPA (Riebiosios rūgštys 20:5) (g)", "epa");
        translations.put("dpa", "DPA (Riebiosios rūgštys 22:5) (g)");
        translations.put("DPA (Riebiosios rūgštys 22:5) (g)", "dpa");
        translations.put("dha", "DHA (Riebiosios rūgštys 22:6) (g)");
        translations.put("DHA (Riebiosios rūgštys 22:6) (g)", "dha");
        translations.put("cholesterol", "Cholesterolis (mg)");
        translations.put("Cholesterolis (mg)", "cholesterol");
        translations.put("retinol", "Retinolis (µg)");
        translations.put("Retinolis (µg)", "retinol");
        translations.put("retinolequivalent", "Retinol ekvivalentas (µg)");
        translations.put("Retinol ekvivalentas (µg)", "retinolequivalent");
        translations.put("betakaroten", "Beta-karotenas (µg)");
        translations.put("Beta-karotenas (µg)", "betakaroten");
        translations.put("vit_d", "Vitaminas D (µg)");
        translations.put("Vitaminas D (µg)", "vit_d");
        translations.put("vit_e", "Vitaminas E (mg)");
        translations.put("Vitaminas E (mg)", "vit_e");
        translations.put("vit_k", "Vitaminas K (µg)");
        translations.put("Vitaminas K (µg)", "vit_k");
        translations.put("tiamin", "Tiaminas (mg)");
        translations.put("Tiaminas (mg)", "tiamin");
        translations.put("riboflavin", "Riboflavinas (mg)");
        translations.put("Riboflavinas (mg)", "riboflavin");
        translations.put("vit_c", "Vitaminas C (mg)");
        translations.put("Vitaminas C (mg)", "vit_c");
        translations.put("niacin", "Niacinas (mg)");
        translations.put("Niacinas (mg)", "niacin");
        translations.put("niacinequivalent", "Niacino ekvivalentas (mg)");
        translations.put("Niacino ekvivalentas (mg)", "niacinequivalent");
        translations.put("vit_b_6", "Vitaminas B-6 (mg)");
        translations.put("Vitaminas B-6 (mg)", "vit_b_6");
        translations.put("vit_b_12", "Vitaminas B-12 (µg)");
        translations.put("Vitaminas B-12 (µg)", "vit_b_12");
        translations.put("folate", "Folatas (µg)");
        translations.put("Folatas (µg)", "folate");
        translations.put("phosphorus", "Fosforas (mg)");
        translations.put("Fosforas (mg)", "phosphorus");
        translations.put("iodine", "Jodas (µg)");
        translations.put("Jodas (µg)", "iodine");
        translations.put("iron", "Geležis (mg)");
        translations.put("Geležis (mg)", "iron");
        translations.put("calcium", "Kalcis (mg)");
        translations.put("Kalcis (mg)", "calcium");
        translations.put("potassium", "Kalis (mg)");
        translations.put("Kalis (mg)", "potassium");
        translations.put("magnesium", "Magnis (mg)");
        translations.put("Magnis (mg)", "magnesium");
        translations.put("sodium", "Natris (mg)");
        translations.put("Natris (mg)", "sodium");
        translations.put("salt", "Druska (g)");
        translations.put("Druska (g)", "salt");
        translations.put("selenium", "Selenas (µg)");
        translations.put("Selenas (µg)", "selenium");
        translations.put("zinc", "Cinkas (mg)");
        translations.put("Cinkas (mg)", "zinc");
        translations.put("rest", "Atliekos (pvz. lupenos) (%)");
        translations.put("Atliekos (pvz. lupenos) (%)", "rest");
        translations.put("edible_coefficient", "Valgomosios dalies koeficientas");
        translations.put("Valgomosios dalies koeficientas", "edible_coefficient");
        translations.put("dry_material", "Sausųjų medžiagų (g)");
        translations.put("Sausųjų medžiagų (g)", "dry_material");
        translations.put("animal_protein", "Gyvūninių baltymų (g)");
        translations.put("Gyvūninių baltymų (g)", "animal_protein");
        translations.put("natural_protein", "Augalinių baltymų (g)");
        translations.put("Augalinių baltymų (g)", "natural_protein");
        translations.put("starch", "Krakmolo (g)");
        translations.put("Krakmolo (g)", "starch");
    }

    public Product(ResultSet product) throws SQLException {
        this();
        if (product == null)
            return;
        ResultSetMetaData meta = product.getMetaData();
        int colCount = meta.getColumnCount();
        for(int i = 1; i <= colCount; i++) {
            String name = meta.getColumnName(i);
            String translation = translations.get(name);
            String value = product.getString(name);
            properties.put(translation, value);
        }
//            for (Map.Entry<String, String> it : translations.entrySet()) {
//                try {
//                    String value = product.getString(it.getKey());
//                    String translation = translations.get(it.getKey());
//                    properties.put(translation, value);
//                }
//                catch (SQLException err) {
//                    err.printStackTrace();
//                }
//            }
    }

    public Product() {
        this.properties = new HashMap<>();
        this.stringProperties = new HashMap<>();
    }

    public Product(Product product, float coefficient) {
        this();
        for (Map.Entry<String, String> it : product.properties.entrySet()) {
            String value = it.getValue();
            String key = it.getKey();
            try {
                Float floatValue = new Float(value) * coefficient;
                properties.put(key, floatValue.toString());
            }
            catch(NumberFormatException err) {
                //property is actually a string so we can just set it
                properties.put(key, value);
            }
        }
        this.coefficient = coefficient;
    }

    private static String multiply(float coefficient, String v) {
        double value = new Double(v);

        return (value * coefficient) + "";
    }

    public StringProperty getStringProperty(String property) {
        return this.stringProperties.computeIfAbsent(property, this::mapProperty);
    }

    private StringProperty mapProperty(String key) {
        String value = properties.get(key);
        return new SimpleStringProperty(value);
    }


    public static ArrayList<String> propertyNames() {
        ArrayList<String> names = new ArrayList<>();
        Collections.addAll(names,
                "name",
                "kcal",
                "kj",
                "carbohydrates",
                "fat",
                "protein",
                "fiber",
                "water",
                "alcohol",
                "ash",
                "monosacharids",
                "disacharids",
                "fakeSuger",
                "grain",
                "sugar",
                "superFat",
                "fat4_10",
                "fat12",
                "fat14",
                "fat16",
                "fat18",
                "fat20",
                "monoSuperFat",
                "fat16_1",
                "fat18_1",
                "poliSuperFat",
                "fat18_2",
                "fat18_3",
                "fat20_4",
                "epa",
                "dpa",
                "dha",
                "cholesterol",
                "retinol",
                "retinolequivalent",
                "betakaroten",
                "vit_d",
                "vit_e",
                "vit_k",
                "tiamin",
                "riboflavin",
                "vit_c",
                "niacin",
                "niacinequivalent",
                "vit_b_6",
                "vit_b_12",
                "folate",
                "phosphorus",
                "iodine",
                "iron",
                "calcium",
                "potassium",
                "magnesium",
                "sodium",
                "salt",
                "selenium",
                "zinc",
                "rest",
                "edible_coefficient",
                "dry_material",
                "animal_protein",
                "natural_protein",
                "starch");
        return names;
    }

    @Override
    public String toString() {
        return properties.get("name");
    }


    public List<StringProperty> bindableProperties() {
        return propertyNames().stream().map(this::getStringProperty).collect(Collectors.toList());
    }

    public String getId() {
        if (id.length() != 0)
            return id;
        else
            return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public String getName() {
        return toString();
    }

    public void setName(String s) {
        properties.put("name", s);
        refreshProperty("name");
    }

    public void refreshProperty(String key) {
        StringProperty sp = getStringProperty(key);
        sp.set(this.properties.get(key));
    }

    public String getProperty(String key) {
        String lowercase = key.toLowerCase();
        return properties.get(lowercase);
    }
}
