package nadesys.github.io;

import nadesys.github.io.lib.SparkDB;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        SparkDB db = new SparkDB();
        System.out.println("Reading the database from disk");
        db.readFromFile("./db.txt");
        // Carrots Prices
        System.out.println("Carrots prices (ordered): " + db.get(new HashMap<String, String>() {{
            put("product", "carrots");
        }}, "price"));
        // Prices of all products on January
        System.out.println("Prices of all products on January: " + db.get(db.getIDs(new HashMap<String, String>() {{
            put("month", "1");
        }})));
    }

}
