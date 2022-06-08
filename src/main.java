import java.util.HashMap;

public class main {

	public static void main(String[] args) throws Exception {
		SparkDB db = new SparkDB();
		System.out.println("Reading the database from disk");
		long F = System.nanoTime();
		db.readFromFile("./db.txt");
		System.out.println("Time taken (ms): "+(System.nanoTime()-F)/1000000.0);
		F = System.nanoTime();
		// Carrots Prices
		System.out.println("Carrots prices (ordered): "+db.get(new HashMap<String, String>() {{
			put("product","carrots");
		}}, "price"));
		System.out.println("Time taken (ms): "+(System.nanoTime()-F)/1000000.0);
		F = System.nanoTime();
		// Prices of all products on January
		System.out.println("Prices of all products on January: "+db.get(db.getIDs(
				new HashMap<String, String>() {{
					put("month","1");
				}}
				)));
		System.out.println("Time taken (ms): "+(System.nanoTime()-F)/1000000.0);
	}

}