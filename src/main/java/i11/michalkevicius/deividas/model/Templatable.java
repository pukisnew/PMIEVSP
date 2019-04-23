package i11.michalkevicius.deividas.model;

import i11.michalkevicius.deividas.controller.R;

import java.util.HashMap;
import java.util.Map;

public class Templatable implements Comparable<Templatable> {
    private final String templateURL;
    private Map<String, String> values = new HashMap<>();
    private String internalTemplate = null;
    public Templatable(String templateURL) {
        this.templateURL = templateURL;
    }

    public Templatable set(String key, String value) {
        values.put(key, value);
        return this;
    }

    public String toTemplate() {
        if(internalTemplate == null) {
            internalTemplate = R.loadTemplate(this.templateURL);
            for (Map.Entry<String, String> it : values.entrySet()) {
                String actualKey = String.format("{{ %s }}", it.getKey());
                internalTemplate = internalTemplate.replace(actualKey, it.getValue());
            }
        }
        return internalTemplate;
    }


    public static final String ROWS = "rows";
    public static final String LAST_ROW = "last_row";
    public static final String ROW_INT = "row_int";
    public static final String HEADER = "header";
    public static final String UNIT = "unit";
    public static final String KILOGRAM = "1000_value";
    public static final String REGULAR = "100_value";
    public static final String HALF = "50_value";
    public static final String TABLE_LEFT = "table_left";
    public static final String TABLE_RIGHT = "table_right";

    @Override
    public int compareTo(Templatable o) {
        return values.get(HEADER).compareTo(o.values.get(HEADER));
    }
}
