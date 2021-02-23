package command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class LoadPNGCommand implements FileCommand {
	
	private static LoadPNGCommand command;
	
	private String directory;
	private HashMap<String, BufferedImage> textures;
	
	private LoadPNGCommand(
		String directory, 
		HashMap<String, BufferedImage> textures
	) {
		this.directory = directory;
		this.textures  = textures;
	}
	
	public static LoadPNGCommand init(
		String directory, 
		HashMap<String, BufferedImage> textures
	) {
		if (command == null) {
			command = new LoadPNGCommand(directory, textures);
		} else {
			command.directory = directory;
			command.textures  = textures;
		}
		return command;
	}

	@Override
	public void execute(File pathName, String name) {
		try {	
			String formattedDir = directory.substring(3).replace("/", "\\");
			
			String dirValue = pathName + "\\" + name;
			dirValue = dirValue.substring(dirValue.indexOf(formattedDir)).replace("\\", "/");
			
			String key = name.substring(0, name.indexOf(".png"));
			BufferedImage value = ImageIO.read(getClass().getResource(dirValue));
			textures.put(key, value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDirectory() {
		return directory;
	}

	public HashMap<String, BufferedImage> getTextures() {
		return textures;
	}
}
