import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.json.simple.JSONObject;


public class JsonCreator {//this class takes the array of linked hash-maps and iteratively adds values to the json object
	
	public List<LinkedHashMap<String,Object>> cache = new ArrayList<LinkedHashMap<String,Object>>();//cache will contain entries already added to JSON

	public JSONObject createFirstRow(List<LinkedHashMap<String,Object>> list) {
		List<LinkedHashMap<String,Object>> categories = new ArrayList<LinkedHashMap<String,Object>>();
		JSONObject jsonData = new JSONObject();
		for(LinkedHashMap<String,Object> element :list) {
			int level = findLevel((String) element.get("URL"));
			if(level == 1 && !cache.contains(element)) {
				categories.add(element);
				jsonData.put("Categories", categories);
				cache.add(element);
			}
		}
		return jsonData;
	}
	
	public JSONObject createSecondRow(List<LinkedHashMap<String,Object>> list,JSONObject jsonObject) {
		for(LinkedHashMap<String,Object> element :list) {
			int level = findLevel((String) element.get("URL"));
			if(level == 2 && !cache.contains(element)) {
			((List<LinkedHashMap<String,Object>>) ((HashMap) ((ArrayList) jsonObject.get("Categories")).get(0)).get("Children")).add(element);
			cache.add(element);
			}
		}
		return jsonObject;
	}
	
	
	public JSONObject createThirdRow(List<LinkedHashMap<String,Object>> list,JSONObject jsonObject) {
		for(LinkedHashMap<String, Object> parent:((ArrayList<LinkedHashMap<String,Object>>) ((HashMap) ((ArrayList) jsonObject.get("Categories")).get(0)).get("Children"))) {
			for(LinkedHashMap<String,Object> child :list) {
				int level = findLevel((String) child.get("URL"));
				if(level == 3 &&isChild(parent,child)&& !cache.contains(child)) {
					((ArrayList) parent.get("Children")).add(child);
				}
				}
			}
		return jsonObject;
		}
	
	private boolean isChild(LinkedHashMap<String, Object> parent, LinkedHashMap<String, Object> child) {
			return child.get("URL").toString().contains(parent.get("ID").toString());
	}

	private int findLevel(String element) {
		int count = element.length() - element.replace("/", "").length();
		return count-3;
	}
			
}
