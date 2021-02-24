package loop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import input.KeyInput;
import input.MouseInput;
import userGUI.Frame;
import userGUI.Screen;
import userGUI.taskBar.TaskBar;

/**
 * Program loop that runs at 60 updates per second.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-04-28 
 */
public class Loop implements ActionListener {
	
	private static Loop loop;
	
	private static double delta;
	private static int updates, reportedUpdates;
	private static int loopsPerSecond, reportedLoops;
	private static long lastTime;
	private static long timer;
	private static long now;
	
	private static MouseInput mouseInput;
	
	/**
	 * Loop constructor.
	 */
	private Loop() {
		mouseInput = MouseInput.getInstance();
		lastTime = System.nanoTime();
		timer = System.currentTimeMillis();
	}
	
	/**
	 * Singleton instance of the loop.
	 * 
	 * @return  singleton instance
	 */
	public static Loop getInstance () {
		if (loop == null)
			loop = new Loop();
		return loop;
	}
	
	/**
	 * Program loop that runs at 60 updates per second.
	 * @param e  Does nothing.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		now = System.nanoTime();
		delta += 60.0 * (now - lastTime) / 1000000000;
		
		lastTime = now;
		
		if (delta >= 1) {
		    update();
			updates ++;
			delta --;
		}
		
		loopsPerSecond++;
		if (System.currentTimeMillis() - timer > 1000) {
			reportedUpdates = updates;
			reportedLoops = loopsPerSecond;
			loopsPerSecond = 0;
			updates = 0;
			timer += 1000;
		}
	}
	
	/**
	 * Update all objects in the application.
	 */
	public static void update() {
		Frame.getInstance().update();
		mouseInput.update();
		TaskBar.getInstance().update();
		Screen.getInstance().update();
		KeyInput.update();
	}
	
	public static int getLoopsPerSecond() {
		return reportedLoops;
	}

	public static void setLoopsPerSecond(int loopsPerSecond) {
		Loop.loopsPerSecond = loopsPerSecond;
	}

	public static int getUpdates() {
		return Loop.reportedUpdates;
	}
}