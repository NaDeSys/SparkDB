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

        /* Get all rows where the month is 5 */

        ArrayList<HashMap<String, String>> Rows = Table.getRows(new HashMap<>() {{
            put("month", "5");
        }});
        System.out.println(Rows);

        /* Get all rows where product is tomato and month is lower than or equal 7 */

        ArrayList<HashMap<String, String>> RowsV2 = new ArrayList<>();
        for(int Month = 1; Month <= 7; Month ++) {
            final int MonthFinal = Month; // Due to a stupid convention by Oracle, we are forced to do this

            RowsV2.addAll(Table.getRows(new HashMap<>() {{
                put("product", "tomato");
                put("month", String.valueOf(MonthFinal));
            }}));
        }
        System.out.println(RowsV2);
    }
}
