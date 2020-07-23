import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CSVDataFlattenModule { // this class takes the csv data and converts it into an array of linked
									// hash-maps
	private static final List<List<String>> List = null;
	BufferedReader br;
	static String path = "data.csv";
	List<List<String>> records = new ArrayList<>();

	public static List<List<String>> parseCSVDataToList(int csvRowCount, String csvPath)
			throws FileNotFoundException, IOException {
		List<List<String>> records = new ArrayList<>();
		List<String> individualRecord;
		try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records.add(Arrays.asList(values));
				counter++;
				if (counter == csvRowCount) {
					return records;
				}
			}
		}
		return records;// records is the initial list of strings from the csv data
	}

	public static int findCount(String filePath) {// this method finds the amount of entries in the csv file
		List<List<String>> records = new ArrayList<>();
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String input;
			count = 0;
			while ((input = br.readLine()) != null) {
				count++;
			}
			return count;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static void computeIndividualLevel(List<String> list, List<LinkedHashMap<String, Object>> finalArray) {
		if (list.size() == 4) {// this method takes a single field from the excel finds how many
								// entries(parents with child elements) there are in a single Entry
			createDataFor3Entries(list, finalArray);
		}
		if (list.size() == 7) {
			createDataFor6Entries(list, finalArray);
		}
		if (list.size() == 10) {
			createDataFor9Entries(list, finalArray);
		}
	}

	private static void createDataEntries(List<String> list, List<LinkedHashMap<String, Object>> finalList, int index1,
			int index2, int index3) {
		LinkedHashMap<String, Object> object = new LinkedHashMap<String, Object>();
		object.put("Label", list.get(index1));
		object.put("ID", list.get(index2));
		object.put("URL", list.get(index3));
		object.put("Children", new ArrayList<>());
		finalList.add(object);
	}

	private static void createDataFor6Entries(List<String> list, List<LinkedHashMap<String, Object>> finalList) {
		createDataEntries(list, finalList, 1, 2, 3);
		createDataEntries(list, finalList, 4, 5, 6);
	}

	private static void createDataFor9Entries(List<String> list, List<LinkedHashMap<String, Object>> finalList) {
		createDataEntries(list, finalList, 1, 2, 3);
		createDataEntries(list, finalList, 4, 5, 6);
		createDataEntries(list, finalList, 7, 8, 9);
	}

	private static void createDataFor3Entries(List<String> list, List<LinkedHashMap<String, Object>> finalList) {
		LinkedHashMap<String, Object> object = new LinkedHashMap<String, Object>();
		createDataEntries(list, finalList, 1, 2, 3);
	}

	public static List<LinkedHashMap<String, Object>> divideChildFromParentElements(List<List<String>> initialArray) {
		List<LinkedHashMap<String, Object>> flattenedData = new ArrayList<LinkedHashMap<String, Object>>();
		removeEmtpyEntires(initialArray);
		initialArray.remove(0);
		for (List<String> list : initialArray) {
			int size = list.size();
			computeIndividualLevel(list, flattenedData);
		}
		return flattenedData;
	}

	public static void removeEmtpyEntires(List<List<String>> initialArray) {
		for (int i = 0; i < initialArray.size(); i++) {
			List<String> element = initialArray.get(i);
			if (element.isEmpty())
				initialArray.remove(i);
		}
	}

}
