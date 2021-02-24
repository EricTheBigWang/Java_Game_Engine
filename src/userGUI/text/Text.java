package userGUI.text;

import java.awt.Graphics;

import assetManager.Texture;
import userGUI.Frame;
import userGUI.Screen;
import userGUI.taskBar.TaskBar;

/**
 *
 */
public class Text {

	protected String text;
	protected int x, y;

	private int tempX, tempY;

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
					tempX = 12 * i + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2;
					tempY = (Screen.getScreenHeight() - 14) / 2;
					g.drawImage(Texture.getChar((int)text.charAt(i)), tempX, tempY, null);
				}
				break;
			case "relative to center":
				for (int i = 0; i < text.length(); i++) {
					tempX = 12 * i + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2 + x;
					tempY = (Screen.getScreenHeight() - 14) / 2 + y;
					g.drawImage(Texture.getChar((int)text.charAt(i)), tempX, tempY, null);
				}
				break;
			default:
				System.err.print("key not found");
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
