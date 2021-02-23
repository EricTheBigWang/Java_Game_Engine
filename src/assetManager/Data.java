package assetManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Data {
	
	private static Data data;
	
	/**
	 * Used to parse the file.
	 */
	private static final String DIRECTORY = "src/asset/data/levelData.txt";
	private static String[] keyVal; 
	private static String line, accumulator;
	private static Vector<String> levelStringManager;
	
	/**
	 * Level data retrieved.
	 */
	private static HashMap<String, String> levelData;
	
	/**
	 * 
	 */
	private Data () {
		levelData = new HashMap<String, String>();
		readData();
	}
	
	/**
	 * Singleton instance of the data class.
	 * 
	 * @return
	 */
	
	public static Data getInstance() {
		if (data == null)
			data = new Data();
		return data;
	}
	
	/**
	 * 
	 * 
	 * @param key
	 * @param value
	 */
	public static void setData(String key, String value) {
		Data.levelData.put(key, value);
	}
	
	/**
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public static String getData(String key) {
		return Data.levelData.get(key);
	}
	
	/**
	 * 
	 */
	public static void printData() {
		for (Map.Entry<String, String> entry : levelData.entrySet()) {
	        System.out.println(entry.getKey() + ":" + entry.getValue());
	    }
	}
	
	/**
	 * 
	 */
	public static void readData() {
		try {
		    BufferedReader fileReader = new BufferedReader(new FileReader(DIRECTORY));
		    while((line = fileReader.readLine()) != null) {
		    	keyVal = line.split(" ");
		    	levelData.put(keyVal[0], keyVal[1]);
		    }
		    fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public static void writeData () {
		try {
			 BufferedWriter fileWriter = new BufferedWriter(new FileWriter(DIRECTORY));
			 
			 levelStringManager = new Vector<String>();
			 for (Map.Entry<String, String> entry : levelData.entrySet()) {
		         // System.out.println(entry.getKey() + ":" + entry.getValue());
		         levelStringManager.add(entry.getKey() + " " + entry.getValue() + "\n");
		     }
			 
			 levelStringManager.sort(null);
			 accumulator = "";
			 for (int i = 0; i < levelStringManager.size(); i++) {
		         accumulator += levelStringManager.get(i);
		     }
			 
			 fileWriter.write(accumulator);
			 accumulator = "";
			 fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
