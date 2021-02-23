package toolkit;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import assetManager.Texture;
import input.KeyInput;
import input.MouseInput;
import loop.Loop;

public class Debug {
	
	private static Debug debug;
	
	private static final int FONTHEIGHTSPACING = Texture.getChar(0).getHeight() + 4;
	
	private static boolean isDisplayed, isPressed;
	
	private static ArrayList<String> parameters;
	
	private Debug() {
		parameters = new ArrayList<String>();
	}
	
	public static Debug getInstance() {
		if (debug == null)
			debug = new Debug();
		return debug;
	}
	
	public static void update() {
		if (KeyInput.getPressed()[KeyEvent.VK_F3]) {
			isPressed = true;
		}
		
		if (!KeyInput.getPressed()[KeyEvent.VK_F3] && isPressed) {
			isPressed = false;
			isDisplayed = !isDisplayed;
		}
		
		if (isDisplayed) {
			addParam("UPDATES PER SECOND: " + Tools.str(Loop.getUpdates()));
			addParam("LOOPS PER SECOND: " + Tools.str(Loop.getLoopsPerSecond()));
			addParam("LEFT MOUSE BUTTON: " + Tools.str(Tools.num(MouseInput.LMB())));
			addParam("MOUSE WHEEL: " + Tools.str(MouseInput.MMB()));
			addParam("RIGHT MOUSE BUTTON: " + Tools.str(Tools.num(MouseInput.RMB())));
			addParam("MOUSE X: " + Tools.str(MouseInput.X()));
			addParam("MOUSE Y: " + Tools.str(MouseInput.Y()));
		}
	}
	
	public static void render(Graphics g) {
		if (isDisplayed) {
			for (int i = 0; i < parameters.size(); i++) {
				for (int j = 0; j < parameters.get(i).length(); j++) {
					g.drawImage(
						Texture.getChar((int) parameters.get(i).charAt(j)), 
						10 + (12 * j), 
						10 + i * FONTHEIGHTSPACING, 
						null
					);
				}
			}
		}
		parameters.clear();
	}
	
	public static void addParam(String param) {
		parameters.add(param);
	}
}
