package assetManager;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import command.FileCommand;

public class FileLoader {
	
	private String directory;
	private String suffix;
	private HashMap<String, String> fileDirectories;
	private boolean executed;
	
	public FileLoader(String directory, String suffix) {
		this.directory = directory;
		this.suffix = suffix;
		this.fileDirectories = new HashMap<String, String>();
	}

	public void loadFiles(FileCommand fileCommand) {
		if (!executed) {
			String abs = new File(directory).getAbsolutePath();
			
			Queue<File> queue = new LinkedList<File>();
			queue.add(new File(abs));
	
			while (!queue.isEmpty()) {
				
				File node = queue.remove();
				
				File[] childs = node.listFiles(
					new FilenameFilter() {
						@Override
						public boolean accept(File pathName, String name) {
							if (name.endsWith(suffix)) {
								fileCommand.execute(pathName, name);
							}
							
							return pathName.isDirectory();
						}
					}
				);
	
				if (childs != null) {
					for (File child : childs) {
						queue.add(child);
					}
				}
			}
		}
		executed = true;
	}

	public String getDirectory() {
		return directory;
	}

	public String getSuffix() {
		return suffix;
	}

	public boolean isExecuted() {
		return executed;
	}

	public HashMap<String, String> getFileDirectories() {
		return fileDirectories;
	}
}
