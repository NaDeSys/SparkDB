package io.github.nadesys;

import nadesys.github.io.lib.SparkDB;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MainTest {
    @Test
    void testMain() {
        SparkDB db = new SparkDB();
        System.out.println("Reading the database from disk");
        try {
            db.readFromFile("./db.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Carrots prices (ordered): " + db.get(new HashMap<String, String>() {{
            put("product", "carrots");
        }}, "price"));
        System.out.println("Prices of all products on January: " + db.get(db.getIDs(new HashMap<String, String>() {{
            put("month", "1");
        }})));
    }
}
