package assetManager;

import java.awt.image.BufferedImage;

/**
 * This class allows for accessing sub images in a sprite sheet with indexing.
 * 
 * @author Yi Feng Wang
 */

public class SpriteSheet {
	
	private BufferedImage image;
	private int spur, spuc;
	
	/**
	 * SpriteSheet constructor.
	 * 
	 * @param image  the sprite sheet to be processed
	 * @param spur   the height of the sprite sheet unit, including black border
	 * @param spuc	 the length of the sprite sheet unit, including black border
	 */
	public SpriteSheet(BufferedImage image, int spur, int spuc) {
		this.image = image;
		this.spur = spur;
		this.spuc = spuc;
	}
	
	/**
	 * Return a sprite from the sprite sheet.
	 * Indexing of row begins at 1, and indexing of col begins at 1.
	 * The width and height must account for the one tile gap between each sprite.
	 * 
	 * @param col
	 * @param row
	 * @param width
	 * @param height
	 * @return sprite
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return image.getSubimage(
			row * spur - spur + 1, 
			col * spuc - spuc + 1, 
			width - 1, 
			height - 1
		);
	}
}