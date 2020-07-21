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
	static String path = "data.csv";
	BufferedReader br;
	List<List<String>> records = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int countTo = findCount(path);
		System.out.println(createInitialObject(countTo).get(4));
		
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
	
	
	public static List<List<String>> createInitialObject(int csvRowCount) throws FileNotFoundException, IOException{
		List<List<String>> records = new ArrayList<>();
		List<String> individualRecord;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line;
		    int counter = 0;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		        counter++;
		        if(counter == csvRowCount) {
		        	return records;
		    }
		}}
		return records;
	}
	
	public static int findCount (String path) {
		List<List<String>> records = new ArrayList<>();
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
		     String input;
		     count = 0;
		     while((input = br.readLine()) != null)
		     {
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
		// TODO Auto-generated method stub



}
