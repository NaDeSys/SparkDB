import java.util.HashMap;

public class test {

	public static void main(String[] args) throws Exception {
		SparkDB db = new SparkDB();
		db.readFromFile("./db.txt");
		HashMap<String, String> results = new HashMap<>();
		
		System.out.println(results);
	}

}
