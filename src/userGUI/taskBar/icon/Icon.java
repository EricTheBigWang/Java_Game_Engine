package userGUI.taskBar.icon;

import java.awt.Graphics;

import assetManager.Texture;

public class Icon {
	
	private static Icon icon;

	private Icon() {
		
	}

	/**
	 * Singleton instance of image icon.
	 *
	 * @return the icon.
	 */
	public static Icon getInstance() {
		if (icon == null)
			icon = new Icon();
		return icon;
	}
	
	public void update() {
		// Update custom icon here.
	}
	
	public void render(Graphics g) {
		// Add custom icon here.
	}
}
