package userGUI.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import userGUI.entity.ClickableEntity;
import userGUI.text.CenteredString;

public class Button extends ClickableEntity {
	
	private Color releasedColor;
	private Color hoveredColor;
	private Color pressedColor;
	
	private String text;
	private Color textColor;
	private Font font = null;
	
	// Audio
	// private AudioHandler audio;
	
	/**
	 * Button constructor.
	 * 
	 * @param x       the x coordinate of the button
	 * @param y       the y coordinate of the button
	 * @param length  the length of a button
	 * @param height  the height of a button
	 */
	public Button(int x, int y, int length, int height) {
		super(x, y, length, height);
		
		// audio = AudioHandler.getInstance();
		// audio.load("select.wav", "select"):
		
		this.text           = "";
		this.font           = new Font("Helvetica", Font.PLAIN, 10);
		
		this.releasedColor  = new Color(220, 220, 220);
		this.pressedColor   = new Color(200, 200, 200);
		this.hoveredColor   = new Color(210, 210, 210);
		this.textColor      = new Color(100, 100, 100);
	}
	
	/**
	 * Render the button on a page.
	 * 
	 * @param g  graphics from a page
	 */
	public void render(Graphics g) {
		switch (currentState) {
			case RELEASED: g.setColor(releasedColor); break;
			case HOVERED : g.setColor( hoveredColor); break;
			case PRESSED : g.setColor( pressedColor); break;
		}
		
		g.fillRect(getX(), getY(), clickBox.width, clickBox.height);
		
		g.setColor(textColor);
		g.setFont(font);
		
		g.drawString(
			text, 
			CenteredString.x(clickBox.x, clickBox.width, text, font, g), 
			CenteredString.y(clickBox.y, clickBox.height, font, g)
		);
	}
	
	/**
	 * Assign colors to the button.
	 * 
	 * @param released  the color of the button if the mouse is not on the button
	 * @param hovered   the color of the button if the mouse is hovered on the button
	 * @param pressed   the color of the button if the mouse is pressed on the button
	 */
	public void setColors(Color released, Color hovered, Color pressed) {
		this.releasedColor = released;
		this.pressedColor  = pressed;
		this.hoveredColor  = hovered;
	}
	
	/**
	 * Assign colors to the button.
	 * 
	 * @param released  the color of the button if the mouse is not on the button
	 * @param hovered   the color of the button if the mouse is hovered on the button
	 * @param pressed   the color of the button if the mouse is pressed on the button
	 * @param text      the color of the text on the button
	 */
	public void setColors(Color released, Color hovered, Color pressed, Color text) {
		this.releasedColor = released;
		this.pressedColor  = pressed;
		this.hoveredColor  = hovered;
		this.textColor = text;
	}
	
	/**
	 * Assign text to the button.
	 * 
	 * @param text  the text to be rendered
	 * @param font  the font of the text
	 */
	public void setText(String text, Font font) {
		this.text = text;
		this.font = font;
	}
	
	public State getState() {
		return currentState;
	}
	
	public void setX(int x) {
		clickBox.x = x;
	}
	
	public void setY(int y) {
		clickBox.y = y;
	}
	
	public int getX() {
		return clickBox.x;
	}
	
	public int getY() {
		return clickBox.y;
	}
	
	public int getlength() {
		return clickBox.width;
	}
	
	public int getHeight() {
		return clickBox.height;
	}
}
