import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class CSVDataFlattenModuleTest {

	private CSVDataFlattenModule csvDataFlattenModule = new CSVDataFlattenModule();
	String testPath = "testData.csv";

	@Test
	public void findCount_findsCorrectCount() {
		int count = CSVDataFlattenModule.findCount(testPath);
		Assert.assertEquals(count, 13);
	}

	@Test
	public void parseCSVDataToList_createsCorrectAmountOfRecords() throws FileNotFoundException, IOException {
		List<List<String>> testData = csvDataFlattenModule.parseCSVDataToList(13, testPath);
		Assert.assertEquals(13, testData.size());
	}

}
