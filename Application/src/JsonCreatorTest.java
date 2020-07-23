

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsonCreatorTest {

	private JsonCreator jsonCreator = new JsonCreator();
	public List<LinkedHashMap<String,Object>> cache = new ArrayList<LinkedHashMap<String,Object>>();
	//jsonobject.put("Categories",arraylist<linkedHashmaps<String,object>>)
	List<LinkedHashMap<String,Object>> categories = new ArrayList<LinkedHashMap<String,Object>>();
	LinkedHashMap<String,Object> entry = new LinkedHashMap<String,Object>();
	List<LinkedHashMap<String,Object>> testElements = new ArrayList<LinkedHashMap<String,Object>>();
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		populateTestElements();
	}
	
	public JSONObject mockFirstEntry() {
		JSONObject jsonData = new JSONObject();
		LinkedHashMap<String,Object> entry = new LinkedHashMap<String,Object>();
		entry.put("Name", "THE BEST");
		entry.put("ID", "178974");
		entry.put("URL","https://groceries.morrisons.com/browse/178974");
		entry.put("Children", new ArrayList<LinkedHashMap<String,Object>>());
		List<LinkedHashMap<String,Object>> categories = new ArrayList<LinkedHashMap<String,Object>>();
		categories.add(entry);
		jsonData.put("Categories", categories);
		return jsonData;
	}
	
	public JSONObject mockSecondEntry() {
		LinkedHashMap<String,Object> entry = new LinkedHashMap<String,Object>();
		entry.put("Name", "FRESH");
		entry.put("ID", "178969");
		entry.put("URL","https://groceries.morrisons.com/browse/178974/178969");
		entry.put("Children", new ArrayList<LinkedHashMap<String,Object>>());
		JSONObject jsonData = mockFirstEntry();
		((List<LinkedHashMap<String,Object>>) ((HashMap) ((ArrayList) jsonData.get("Categories")).get(0)).get("Children")).add(entry);
		return jsonData;
	}
	
	public JSONObject mockThirdEntry() {
		JSONObject jsonObject = mockSecondEntry();
		LinkedHashMap<String,Object> entry = new LinkedHashMap<String,Object>();
		entry.put("Name", "CHEESE");
		entry.put("ID", "178975");
		entry.put("URL","https://groceries.morrisons.com/browse/178974/178969/178975");
		entry.put("Children", new ArrayList<LinkedHashMap<String,Object>>());
		((ArrayList<LinkedHashMap<String,Object>>) ((List<LinkedHashMap<String,Object>>) ((HashMap) ((ArrayList) jsonObject.get("Categories")).get(0)).get("Children")).get(0).get("Children")).add(entry);
		return jsonObject;
		
	}
	
	public void populateTestElements(){
		LinkedHashMap<String,Object> entry = new LinkedHashMap<String,Object>();
		entry.put("Name", "FRESH");
		entry.put("ID", "178969");
		entry.put("URL","https://groceries.morrisons.com/browse/178974/178969");
		entry.put("Children", new ArrayList<LinkedHashMap<String,Object>>());
		LinkedHashMap<String,Object> entry1 = new LinkedHashMap<String,Object>();
		testElements.add(entry);
		entry1.put("Name", "THE BEST");
		entry1.put("ID", "178974");
		entry1.put("URL","https://groceries.morrisons.com/browse/178974");
		entry1.put("Children", new ArrayList<LinkedHashMap<String,Object>>());
		testElements.add(entry1);
		LinkedHashMap<String,Object> entry2 = new LinkedHashMap<String,Object>();
		entry2.put("Name", "CHEESE");
		entry2.put("ID", "178975");
		entry2.put("URL","https://groceries.morrisons.com/browse/178974/178969/178975");
		entry2.put("Children", new ArrayList<LinkedHashMap<String,Object>>());
		testElements.add(entry2);
	}
	
	
	@Test
	public void createFirstRow_createsCorrectJSONObject() {
		JSONObject jsonData = mockFirstEntry();
		JSONObject jsonData1 = jsonCreator.createFirstRow(testElements);
		assertEquals(jsonData,jsonData1);
	}
	
	@Test
	public void createSecondRow_createsCorrectJSONObject() {
		JSONObject mockedJsonData = mockSecondEntry();
		JSONObject jsonToAppendTo = mockFirstEntry();
		JSONObject jsonDataToTest = jsonCreator.createSecondRow(testElements, jsonToAppendTo);
		assertEquals(mockedJsonData,jsonDataToTest);
	}
	
	@Test
	public void createThirdRow_createsCorrectJSONObject() {
		JSONObject jsonToAppendTo = mockSecondEntry();
		JSONObject mockedJsonData = mockThirdEntry();
		JSONObject jsonDataToTest = jsonCreator.createThirdRow(testElements, jsonToAppendTo);
		assertEquals(mockedJsonData,jsonDataToTest);
	}
	}

