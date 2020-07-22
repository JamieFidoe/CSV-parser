import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CSVReader {
	private static CSVDataFlattenModule dataFlattener = new CSVDataFlattenModule();
	BufferedReader br;
	List<List<String>> records = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int countTo = dataFlattener.findCount();
		List<List<String>>initialObject = dataFlattener.createInitialObject(countTo);
		List<LinkedHashMap<String,Object>> dividedElements = dataFlattener.divideElements(initialObject);
		System.out.println(dividedElements);
	}
	
	
	public void importantInformation() {
		LinkedHashMap<String,Object> jo=new  LinkedHashMap<String,Object>();
		jo.put("link", "");
		jo.put("URL", "Doe");
		jo.put("Children",new ArrayList<>() );
		LinkedHashMap<String,Object> jo1=new  LinkedHashMap<String,Object>();
		jo1.put("link", "");
		jo1.put("URL", "Doe");
		jo1.put("Children",new ArrayList<>() );
		LinkedHashMap<String,Object> jo3=new  LinkedHashMap<String,Object>();
		jo3.put("link", "");
		jo3.put("URL", "Doe");
		jo3.put("Children",new ArrayList<>() );
		LinkedHashMap<String,Object> jo4=new  LinkedHashMap<String,Object>();
		jo4.put("link", "");
		jo4.put("URL", "Doe");
		jo4.put("Children",new ArrayList<>() );

		JSONObject ja = new JSONObject();
		ja.put("Categories",jo);
		((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).add(jo1);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).add(jo1);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).add(jo1);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		
		((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).add(jo1);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo3);
		((ArrayList) ((HashMap) ((ArrayList) ((HashMap) ja.get("Categories")).get("Children")).get(0)).get("Children")).add(jo4);
		System.out.println(ja);
	}

}
