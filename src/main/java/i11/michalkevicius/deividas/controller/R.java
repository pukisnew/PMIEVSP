package i11.michalkevicius.deividas.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class R {
    private static final Map<String, String> templates = new HashMap<>();

    public static class FXML {
        public static final String ADMIN_LOGIN = "/fxml/admin_login.fxml";
        public static final String ADMIN_PANEL = "/fxml/admin_panel.fxml";
        public static final String USER_REG = "/fxml/user_reg.fxml";
        public static final String VIEWER_TABLE = "/fxml/lentele_perziurėti.fxml";
        public static final String MAIN = "/fxml/main.fxml";
        public static final String PRODUCT_REG = "/fxml/product_reg.fxml";
        public static final String CALCULATOR = "/fxml/calculator.fxml";
    }

    public static class DOCUMENT {
        public static final String DOC_TABLE_TEMPLATE = "/documents/doc_table_template.html";
        public static final String ROW_LEFT = "/documents/row_left.html";
        public static final String ROW_RIGHT = "/documents/row_right.html";
        public static final String TABLE_LEFT = "/documents/table_left.html";
        public static final String TABLE_RIGHT = "/documents/table_right.html";
    }

    public static final String loadTemplate(String templateURL) {
        return templates.computeIfAbsent(templateURL, R::loadTemplateImpl);
    }
    //public static final String ADMIN_LOGIN = "/fxml/admin_login.fxml";

    private static final String loadTemplateImpl(String templateURL) {
        InputStream ris = R.class.getResourceAsStream(templateURL);
        Scanner risScanner = new Scanner(ris);
        StringBuilder template = new StringBuilder();
        while (risScanner.hasNextLine()) {
            template.append(risScanner.nextLine());
            template.append(System.lineSeparator());
        }
        risScanner.close();
        return template.toString();
    }
}
