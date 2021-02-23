package userGUI.text;

import java.awt.Graphics;

import assetManager.Texture;
import information.Setting;
import userGUI.Screen;

/**
 * 
 * @author WYF
 *
 */
public class Text {
	
	protected String text;
	protected int x, y;
	
	/**
	 * locationType should only ever be assigned one of these 3 values:
	 * ""                    text should be relative to the top left corner of screen
	 * "center"    			 text should be centered on the screen
	 * "relative to center"  text should be relative to the center of the screen
	 */
	protected String locationType;
	
	public Text(String text) {
		this.text = text;
		this.locationType = "center";
		this.x = 0;
		this.y = 0;
	}
	
	public Text(String text, int x, int y) {
		this.text = text;
		this.locationType = "";
		this.x = x;
		this.y = y;
	}
	
	public Text(String text, int x, int y, String locationType) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.locationType = locationType;
	}

	public void update() {}
	
	public void render(Graphics g) {
		switch (locationType) {
			case "":
				for (int i = 0; i < text.length(); i++) {
					g.drawImage(Texture.getChar((int)text.charAt(i)), x + (12 * i), y, null);
				}
				break;
			case "center":
				for (int i = 0; i < text.length(); i++) {
					g.drawImage(
						Texture.getChar((int)text.charAt(i)), 
						(12 * i) + Setting.getScreenPadding() + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2, 
						Setting.getScreenPadding() + (Screen.getScreenHeight() - 14) / 2, 
						null
					);
				}
				break;
			case "relative to center":
				for (int i = 0; i < text.length(); i++) {
					g.drawImage(
						Texture.getChar((int)text.charAt(i)), 
						(12 * i) + Setting.getScreenPadding() + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2 + x, 
						Setting.getScreenPadding() + (Screen.getScreenHeight() - 14) / 2 + y, 
						null
					);
				}
				break;
			default:
				System.err.print("key not found");
		}
	}
	
	/**
	 * THIS FUNCTION IS NOT USED
	 * @param g
	 */
	public void drawText(Graphics g) {
		for (int i = 0; i < text.length(); i++) {
			g.drawImage(Texture.getChar((int)text.charAt(i)), (12 * i), 0, null);
		}
	}
	
	public String getLocationType() {
		return locationType;
	}
	
	public void setLocation(int x, int y) {
		if (this.locationType.equals("")) {
			this.x = x;
			this.y = y;
		} else {
			System.err.println("Used function on wrong type of text.");
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
