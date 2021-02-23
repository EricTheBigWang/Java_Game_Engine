package command;

import java.io.File;
import java.util.HashMap;

public class LoadProductCommand implements FileCommand {
	
	private static LoadProductCommand command;
	
	private String directory;
	private HashMap<String, String> productTypes;
	
	private LoadProductCommand(
		String directory, 
		HashMap<String, String> productTypes
	) {
		this.directory    = directory;
		this.productTypes = productTypes;
	}
	
	public static LoadProductCommand init(
		String directory, 
		HashMap<String, String> productTypes
	) {
		if (command == null) {
			command = new LoadProductCommand(directory, productTypes);
		} else {
			command.directory    = directory;
			command.productTypes = productTypes;
		}
		return command;
	}

	@Override
	public void execute(File pathName, String name) {
		String formattedDir = directory.substring(4).replace("/", ".");
		
		String dirValue = pathName + "." + name;
		dirValue = dirValue.replace("\\", ".");
		dirValue = dirValue.substring(
			dirValue.indexOf(formattedDir), 
			dirValue.indexOf(".java")
		);
		
		String key = name.substring(0, name.indexOf(".java"));
		String value = dirValue;
		productTypes.put(key, value);
	}

	public String getDirectory() {
		return directory;
	}

	public HashMap<String, String> getProductTypes() {
		return productTypes;
	}
}
