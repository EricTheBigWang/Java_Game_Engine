package assetManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import command.LoadPNGCommand;

/**
 * This is a class that loads all Texture required by the program. All Texture
 * should be put into the /Assets/Texture/ folder for convenience. To find the
 * assets, the project folder must be used as a root for sources and class
 * files.
 * 
 * @author Yi Feng Wang
 * @version 1.0
 * @since 2020-04-28
 */
public class Texture extends FileLoader {

	private static Texture texture;

	private static final String DIRECTORY = "src/asset/texture/";

	private static HashMap<String, BufferedImage> textures;
	
	/**
	 * Texture constructor, load all Texture here.
	 */
	private Texture() {
		super(DIRECTORY, ".png");
		textures = new HashMap<String, BufferedImage>();
		loadFiles(
			LoadPNGCommand.init(getDirectory(), textures)
		);
	}
	
	/**
	 * Singleton instance of Texture.
	 * 
	 * @return singleton instance
	 */
	public static Texture getInstance() {
		if (texture == null)
			texture = new Texture();
		return texture;
	}
	
	public static BufferedImage get(String key) {
		if (textures.containsKey(key))
			return textures.get(key);
		System.err.println("Failed to retrieve image " + key);
		return null;
	}
	
	public static BufferedImage getChar(int index) {
		return SpriteSheetLoader.getASCII()[index];
	}
}