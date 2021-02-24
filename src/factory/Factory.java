package factory;

import assetManager.FileLoader;
import command.LoadProductCommand;

public class Factory extends FileLoader {
	public Factory(String directory, String suffix) {
		super(directory, suffix);
		loadFiles(
			LoadProductCommand.init(directory, getFileDirectories())
		);
	}
	
	public Object createProduct(String productType) {		
		if (getFileDirectories().containsKey(productType)) {
			try {
				return Class.forName(
	            	getFileDirectories().get(productType)
	            ).getDeclaredConstructor().newInstance();
				
			} catch (Throwable e) {
				System.err.println("Product " + productType + " could not be created.");
				e.printStackTrace();
				return null;
	        }
		}
		
		System.err.println("Product " + productType + " does not exist.");
		return null;
	}
}
