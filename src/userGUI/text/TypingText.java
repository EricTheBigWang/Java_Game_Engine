package userGUI.text;

import java.awt.Color;
import java.awt.Graphics;

import assetManager.Texture;
import toolkit.Tools;
import userGUI.Screen;

public class TypingText extends Text {

	private static final int TYPINGTIME = 4;
	
	/**
	 * Bottom parsing bar attributes.
	 */
	private int index;
	private int updates;
	
	public TypingText(String text) {
		super(text);
	}
	
	public TypingText(String text, int x, int y, String locationType) {
		super(text, x, y, locationType);
	}
	
	public void reset() {
		index = 0;
		updates = 0;
	}

	@Override
	public void update() {
		updates++;
		index = Math.min(text.length(), index + Tools.num(updates % TYPINGTIME == 0));
		super.update();
	}
	
	@Override
	public void render(Graphics g) {
		switch (locationType) {
			case "":
				drawText(g);
				break;
			case "center":
				for (int i = 0; i < index; i++) {
					g.drawImage(
						Texture.getChar((int)text.charAt(i)), 
						(12 * i) + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2,
						(Screen.getScreenHeight() - 14) / 2, 
						null
					);
				}
				
				if (index < text.length()) {
					g.setColor(new Color(91, 98, 107));
					g.fillRect(
						(12 * (index - 1)) + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2, 
						14 + 4 + (Screen.getScreenHeight() - 14) / 2, 
						10,
						2
					);
				}
				
				
				break;
			case "relative to center":
				for (int i = 0; i < index; i++) {
					g.drawImage(
						Texture.getChar((int)text.charAt(i)), 
						x + (12 * i) + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2,
						y + (Screen.getScreenHeight() - 14) / 2, 
						null
					);
				}
				
				if (index < text.length()) {
					g.setColor(new Color(91, 98, 107));
					g.fillRect(
						x + (12 * (index - 1)) + (Screen.getScreenLength() - 10 * text.length() - 2 * text.length() + 2) / 2, 
						y + 14 + 4 + (Screen.getScreenHeight() - 14) / 2, 
						10,
						2
					);
				}
				break;
			default:
				System.err.print("text key not found");
		}
	}
	
	public void drawText(Graphics g) {
		for (int i = 0; i < index; i++) {
			g.drawImage(Texture.getChar((int)text.charAt(i)), x + (12 * i), y, null);
		}
		
		if (index < text.length()) {
			g.setColor(new Color(91, 98, 107));
			g.fillRect((12 * (index - 1)), 14 + 4, 10, 2);
		}
	}
	
	public boolean isFinishedTyping() {
		return index == text.length();
	}
}

