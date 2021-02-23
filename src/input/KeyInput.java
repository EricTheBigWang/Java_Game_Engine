package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Main;

/**
 * A class that manages keyboard input.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-04-28 
 */
public class KeyInput {
	
	public static KeyInput keyInput;
	
	private static boolean[] pressed, previous;	
	
	/**
	 * KeyInput constructor.
	 */
	private KeyInput() {
		pressed = new boolean[256];	
		previous = new boolean[256];	
		
		Main.getFrame().addKeyListener(
			new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					pressed[e.getKeyCode()] = true;
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					pressed[e.getKeyCode()] = false;
				}
			}
		);
	}

	/**
	 * Singleton instance of KeyInput
	 * 
	 * @return  singleton instance
	 */
	public static KeyInput getInstance() {
		if (keyInput == null)
			keyInput = new KeyInput();
		return keyInput;
	}
	
	public static void update() {
		for (int i = 0; i < 256; i++) {
			previous[i] = pressed[i];
		}
	}

	public static boolean[] getPressed() {
		return pressed;
	}

	public static boolean isReleased(int index) {
		return previous[index] && !pressed[index];
	}
}
