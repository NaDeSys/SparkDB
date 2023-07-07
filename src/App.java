import java.util.ArrayList;
import java.util.HashMap;

/**
 * Example code for SparkDB
 * @author Morad A.
 */
public class App {
    public static void main(String[] args) throws Exception {
        /* Create a table object */
        SparkDB Table = new SparkDB();
        /* Import from db.txt */
        /* The file path is relative to where you execute the class. In my case, I run the code inside src/ folder.
         * Therefore, db.txt is in the parent directory.
         */
        Table.readFromFile("../db.txt");
        /* Get all tomato prices */
        ArrayList<String> TomatoPrices = Table.get(new HashMap<>() {{
            put("product", "tomato");
        }}, "price");
        System.out.println("Tomato Price list: " + TomatoPrices);
    }
}
