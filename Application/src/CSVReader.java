import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class CSVReader {
	private static CSVDataFlattenModule dataFlattener = new CSVDataFlattenModule();
	private static JsonCreator jsonIterator = new JsonCreator();
	private static  FileWriter file;
	static String str;
	BufferedReader br;
	static String path = "data.csv";
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		JSONObject jsonObject = createJson();
		writeToFile(jsonObject);
        }
	
	static void writeToFile(JSONObject jsonToWriteTo) throws IOException{
		String fixedJson = jsonToWriteTo.toString().replace("\\", "");
		Scanner in = new Scanner(System.in);
		System.out.println("Please Select path to send JSON Object Too: ");
		String pathToWriteTo = in.nextLine();
        try {
			file = new FileWriter(pathToWriteTo+"\\json.json");
		} catch (IOException e) {
			System.out.println("File Path Not Found");
			e.printStackTrace();
		}
        file.write(fixedJson);
        System.out.println(("Successfully Copied JSON Object to File..."));
        file.close();
	}
	
	static JSONObject createJson() throws FileNotFoundException, IOException {
		int fieldSize = dataFlattener.findCount("data.csv");
		List<List<String>>initialObject = dataFlattener.parseCSVDataToList(fieldSize,path);
		List<LinkedHashMap<String,Object>> dividedElements = dataFlattener.divideChildFromParentElements(initialObject);
		JSONObject firstRow = jsonIterator.createFirstRow(dividedElements);
		JSONObject secondRow = jsonIterator.createSecondRow(dividedElements,firstRow);
		JSONObject finalJson = jsonIterator.createThirdRow(dividedElements,secondRow);
		return finalJson;
	}
	
}
