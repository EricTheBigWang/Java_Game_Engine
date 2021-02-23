package assetManager;

import java.awt.image.BufferedImage;

public class SpriteSheetLoader {
	
	private static SpriteSheetLoader spriteSheetLoader;
	
	private static SpriteSheet charsSS;
	private static BufferedImage[] ASCII;
	
	private static SpriteSheet bumblySS;
	private static BufferedImage[][] bumbly;
	
	private SpriteSheetLoader() {
		loadASCII();
	}
	
	/**
	 * Singleton instance of sprite sheet loader.
	 * 
	 * @return  singleton instance
	 */
	public static SpriteSheetLoader getInstance() {
		if (spriteSheetLoader == null)
			spriteSheetLoader = new SpriteSheetLoader();
		return spriteSheetLoader;
	}
	
	private void loadASCII() {
		charsSS = new SpriteSheet(Texture.get("spritesheet_font_11_15"), 11, 15);
		ASCII = new BufferedImage[256];
		for (int i = 0; i < 256; i++) {
			ASCII[i] = charsSS.grabImage(1 + (int) (i / 16), 1 + i % 16, 11, 15);
		}
	}

	public static BufferedImage[] getASCII() {
		return ASCII;
	}
}
