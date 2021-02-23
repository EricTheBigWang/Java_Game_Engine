package userGUI.text;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * A GUI class that centers a string onto a shape.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-04-28
 */
public class CenteredString {
	
	public static CenteredString centeredString;
	
	/**
	 * CenteredString constructor
	 */
	private CenteredString() {}
	
	/**
	 * Singleton instance of the CenteredString.
	 * 
	 * @return  singleton instance
	 */
	public static CenteredString getInstance() {
		if (centeredString == null)
			centeredString = new CenteredString();
		return centeredString;
	}
	
	/**
	 * Return the x coordinate of a string centered along the length of a rectangle.
	 * 
	 * @param x       x coordinate of a rectangle
	 * @param length  length of a rectangle
	 * @param string  desired string message
	 * @param font    the font the message will be written with
	 * @param g       graphics
	 * @return        the x coordinate of the string
	 */
	public static int x(int x, int length, String string, Font font, Graphics g) {
		g.setFont(font);
		return (int) (x + (length - g.getFontMetrics().getStringBounds(string, g).getWidth()) / 2);
	}
	
	/**
	 * Return the y coordinate of a string centered along the height of a rectangle.
	 * 
	 * @param y       y coordinate of a rectangle
	 * @param height  height of a rectangle
	 * @param font    the font the message will be written with
	 * @param g       graphics
	 * @return        the y coordinate of a string
	 */
	public static int y(int y, int height, Font font, Graphics g) {
		FontMetrics f = g.getFontMetrics(font);
	    return y + (height - f.getHeight()) / 2 + f.getAscent();
	}
}
