
/**
 * @author Morad
 * @version 2.0
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class SparkDB {
	public static HashMap<String, HMList> Mapper = new HashMap<>();
	public static ArrayList<String> Headers = new ArrayList<>();
	public static int num_queries = 0;
	public static int num_header = 0;

	void readFromFile(String filename) throws Exception {
		BufferedInputStream BIF = new BufferedInputStream(new FileInputStream(new File(filename)), 4096);
		readFromString(new String(BIF.readAllBytes()));
	}

	void readFromString(String data) throws Exception {
		zero();
		InputStream stream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
		boolean headerisprocessed = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line;
		String[] header = null;
		String temp_1 = "";
		String temp_0 = "";
		while ((line = br.readLine()) != null) {
			if (!headerisprocessed) {
				header = line.split("\",\""); // ","
				temp_1 = header[header.length - 1].substring(0, header[header.length - 1].length() - 1);
				temp_0 = header[0].substring(1);
				Mapper.put(temp_0, new HMList());
				Headers.add(temp_0);
				for (int i = 1; i < (header.length - 1); i++) {
					Mapper.put(header[i], new HMList());
					Headers.add(header[i]);
				}
				Mapper.put(temp_1, new HMList());
				Headers.add(temp_1);
				num_header = header.length;
				headerisprocessed = true;
			} else {
				num_queries++;
				String[] single_col = line.split("\",\""); // ","
				Mapper.get(temp_0).add(single_col[0].substring(1));
				for (int x = 1; x < (num_header - 1); x++) {
					Mapper.get(header[x]).add(single_col[x]);
				}
				Mapper.get(temp_1)
						.add(single_col[num_header - 1].substring(0, single_col[num_header - 1].length() - 1));
			}
		}
		br.close();
	}

	public ArrayList<Integer> getIDs(HashMap<String, String> in, int iter) {
		ArrayList<Integer> out = new ArrayList<>();
		Entry<String, String> FirstElement = in.entrySet().iterator().next();
		out.addAll(Mapper.get(FirstElement.getKey()).multipleGet(FirstElement.getValue(), iter));
		for (Integer temp : out) {
			boolean match = false;
			for (Entry<String, String> entry : in.entrySet()) {
				if (Mapper.get(entry.getKey()).get(temp).equals(entry.getValue()))
					match = true;
				else
					match = false;
			}
			if (!match)
				out.remove(temp);
		}
		return out;
	}

	public ArrayList<Integer> getIDs(HashMap<String, String> in) {
		return getIDs(in, Integer.MAX_VALUE);
	}

	ArrayList<String> get(HashMap<String, String> input, String ColToFind, int iter) {
		ArrayList<String> Query = new ArrayList<>();
		ArrayList<Integer> indices = getIDs(input, iter);
		for (int index : indices) {
			Query.add(Mapper.get(ColToFind).get(index));
		}
		return Query;
	}

	ArrayList<String> get(HashMap<String, String> input, String ColToFind) {
		return get(input, ColToFind, Integer.MAX_VALUE);
	}

	HashMap<String, String> get(int index) {
		HashMap<String, String> out = new HashMap<>();
		for (Entry<String, HMList> column : Mapper.entrySet()) {
			out.put(column.getKey(), column.getValue().get(index));
		}
		return out;
	}

	HMList getColumn(String column) {
		return Mapper.get(column);
	}

	void delete(HashMap<String, String> input, int iter) {
		ArrayList<Integer> indices = getIDs(input, iter);
		for (int index : indices) {
			for (String header : Headers) {
				Mapper.get(header).delete(index);
			}
		}
		num_queries = num_queries - indices.size();
	}

	void delete(HashMap<String, String> input) {
		delete(input, Integer.MAX_VALUE);
	}

	void delete(int index) {
		for (Entry<String, HMList> column : Mapper.entrySet()) {
			Mapper.get(column.getKey()).delete(index);
		}
		num_queries--;
	}

	void add(HashMap<String, String> in) {
		add(new ArrayList<HashMap<String, String>>() {
			{
				add(in);
			}
		});
	}

	void add(ArrayList<HashMap<String, String>> in) {
		for (HashMap<String, String> cmd : in) {
			if (!cmd.keySet().containsAll(Headers)) {
				throw new IllegalArgumentException("All supposed headers are not included in add(..) argument."
						+ "Supposed Headers: " + Headers + "." + "Recieved Headers: " + cmd.keySet() + ".");
			}
			for (Entry<String, String> inputaya : cmd.entrySet()) {
				String input = inputaya.getValue();
				input = (input == null) || input.isBlank() || input.isEmpty() ? "0" : input;
				Mapper.get(inputaya.getKey()).add(input);
			}
		}
		num_queries++;
	}

	void modify(HashMap<String, String> in, HashMap<String, String> edit, int iter) {
		ArrayList<Integer> indices = getIDs(in, iter);
		for (int index : indices) {
			for (Entry<String, String> modification : edit.entrySet()) {
				Mapper.get(modification.getKey()).edit(index, modification.getValue());
			}
		}
	}

	void modify(HashMap<String, String> in, HashMap<String, String> edit) {
		modify(in, edit, Integer.MAX_VALUE);
	}

	void modify(int index, HashMap<String, String> edit) {
		for (Entry<String, String> modification : edit.entrySet()) {
			Mapper.get(modification.getKey()).edit(index, modification.getValue());
		}
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		// Print Headers
		for (int i = 0; i < num_header; i++) {
			out.append("\"").append(Headers.get(i)).append("\"");
			if (!((i + 1) == num_header)) {
				out.append(",");
			}
		}
		out.append("\n");
		// Print Data
		for (int i = 0; i < num_queries; i++) {
			for (int x = 0; x < num_header; x++) {
				out.append("\"").append(Mapper.get(Headers.get(x)).get(i)).append("\"");
				if (!((x + 1) == num_header)) {
					out.append(",");
				}
			}
			out.append("\n");
		}
		return out.toString();
	}

	void zero() {
		num_queries = 0;
		num_header = 0;
		Mapper = new HashMap<>();
		Headers = new ArrayList<>();
	}

	public class HMList {
		private HashMap<Integer, String> data = new HashMap<>();
		private int num = 0;

		public void add(String in) {
			data.put(num, in);
			num++;
		}

		public ArrayList<Integer> multipleGet(String in, int iterations) {
			ArrayList<Integer> out = new ArrayList<>();
			int i = 0;
			for (Entry<Integer, String> entry : data.entrySet()) {
				if (entry.getValue().equals(in)) {
					if (i < iterations) {
						out.add(entry.getKey());
						i++;
					} else
						break;
				}
			}
			return out;
		}

		public void edit(int i, String newStr) {
			data.replace(i, newStr);
		}

		public String get(int i) {
			return data.get(i);
		}

		private void syncAfterIndices(int index) {
			for (int i = index; i < (data.size() + 1); i++) {
				String temp = data.get(i);
				data.remove(i);
				data.put(i - 1, temp);
			}
		}

		public void delete(int i) {
			data.remove(i, data.get(i));
			num--;
			syncAfterIndices(i + 1);
			System.out.println(data + "D\n\n\n");
		}

	}
}