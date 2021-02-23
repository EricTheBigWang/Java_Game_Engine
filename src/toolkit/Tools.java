package toolkit;

import input.MouseInput;

/**
 * A class for storing global helper functions.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-04-28 
 */
public class Tools {
	
	private static Tools tools;
	
	/**
	 * Tools constructor.
	 */
	private Tools () {}
	
	/**
	 * Singleton instance of the tools.
	 */
	public static Tools getInstance() {
		if (tools == null)
			tools = new Tools();
		return tools;
	}
	
	/**
	 * Given a rectangle with coordinates x, y, and dimensions length, length,
	 * return true iff the coordinates of the mouse are in the rectangle.
	 * 
	 * @param x       x coordinate of the rectangle
	 * @param y       y coordinate of the rectangle
	 * @param length  length of the rectangle
	 * @param height  height of the rectangle
	 * @return        true iff the mouse is inside of the rectangle
	 */
	public static boolean isMouseInside(double x, double y, double length, double height) {
		final boolean xBounded = MouseInput.X() >= x && MouseInput.X() <= x + length;
		final boolean yBounded = MouseInput.Y() >= y && MouseInput.Y() <= y + height;
		return xBounded && yBounded;
	}
	
	/**
	 * Generate a random integer between low and high inclusive.
	 * 
	 * @param low   lower bound for random number generation
	 * @param high  upper bound for random number generation
	 * @return      a random integer
	 */
	public static int rng(int low, int high) {
		return (int) (Math.random() * (high - low + 1) + low);
	}
	
	/**
	 * Return the closest integer to value that is in the interval [min, max].
	 * 
	 * @param value  any integer
	 * @param min    lower bound of interval
	 * @param max    upper bound of interval
	 * @return       closest integer to value that is between the interval of min and max inclusive
	 */
	public static int ensureRange(int value, int min, int max) {
	   return Math.min(Math.max(value, min), max);
	}
	
	/**
	 * Return true iff the value is between the interval [min, max].
	 * 
	 * @param value  an arbitrary integer
	 * @param min    lower bound of interval
	 * @param max    upper bound of interval
	 * @return		 min <= value and value <= max
	 */
	public static boolean inRange(int value, int min, int max) {
	   return (value >= min) && (value <= max);
	}
	
	/**
	 * Convert a string object to an integer, if possible.
	 * @param str  an arbitrary string
	 * @return     an arbitrary integer
	 */
	public static int num(String str) {
		return Integer.parseInt(str);
	}
	
	/**
	 * Convert a char object to an integer, if possible.
	 * @param c  an arbitrary character
	 * @return   an arbitrary integer
	 */
	public static int num(char c) {
		return Integer.parseInt(String.valueOf(c));
	}
	
	/**
	 * Convert a boolean object to an integer, if possible.
	 * @param b  an arbitrary boolean
	 * @return   1 if b is true else 0
	 */
	public static int num(boolean b) {
		return b ? 1 : 0;
	}
	
	/**
	 * Convert an integer object to a string, if possible.
	 * @param num  an arbitrary integer
	 * @return     an arbitrary string
	 */
	public static String str (int num) {
		return String.valueOf(num);
	}
}
