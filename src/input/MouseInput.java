package input;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

import main.Main;
import toolkit.Tools;
import userGUI.Screen;

/**
 * A class that manages mouse inputs and location on the screen.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-04-28 
 */
public class MouseInput {
	
	private static MouseInput mouseInput;

	private static int x, y;

	/**
	 * Left mouse button
	 * Middle mouse button
	 * Right mouse button
	 */
	private static boolean LMB;
	private static int MMB;
	private static boolean RMB;
	
	private static boolean wheelRotated;
	
	/**
	 * MouseInput constructor, adds listeners to the screen.
	 */
	private MouseInput() {

		Screen.getInstance().addMouseListener(
			new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					LMB = LMB || SwingUtilities.isLeftMouseButton(e);
					RMB = RMB || SwingUtilities.isRightMouseButton(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					LMB = LMB && !SwingUtilities.isLeftMouseButton(e);
					RMB = RMB && !SwingUtilities.isRightMouseButton(e);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
            }
		);
		
		Screen.getInstance().addMouseMotionListener(
			new MouseAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					setCoordinates(e);
					
					if (Tools.inRange(e.getX(), 4, Main.getLength() - 4) &&
							Tools.inRange(e.getY(), 4, Main.getHeight() - 4))
						Main.getFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					setCoordinates(e);
					
					if (Tools.inRange(e.getX(), 4, Main.getLength() - 4) &&
							Tools.inRange(e.getY(), 4, Main.getHeight() - 4))
						Main.getFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
            }
		);
		
		Screen.getInstance().addMouseWheelListener(
			new MouseAdapter() {
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					MMB = e.getWheelRotation();
					wheelRotated = true;
				}
            }
		);
	}
	
	/**
	 * Singleton instance of MouseInput.
	 * 
	 * @return  singleton instance
	 */
	public static MouseInput getInstance() {
		if (mouseInput == null)
			mouseInput = new MouseInput();
		return mouseInput;
	}
	
	public void update() {
		if (!wheelRotated) {
			MMB = 0;
		}
		
		wheelRotated = false;
		if (Main.getFrame().getCursor().getType() != Cursor.DEFAULT_CURSOR) {
			reset();
		}
	}
	
	public void render(Graphics g) {}
	
	public void setCoordinates(MouseEvent e) {
		MouseInput.getInstance().setX((int) e.getPoint().getX() - Screen.getScreenX());
		MouseInput.getInstance().setY((int) e.getPoint().getY() - Screen.getScreenY());
	}

	/**
	 * Assign class attributes to their default values.
	 */
	public void reset() {
		LMB = false;
		MMB = 0;
		RMB = false;
	}
	
	public static int X() {
		return (int) MouseInput.getInstance().x;
	}
	
	public static int Y() {
		return (int) MouseInput.getInstance().y;
	}

	public static boolean LMB() {
		return LMB;
	}

	public static int MMB() {
		return MMB;
	}

	public static boolean RMB() {
		return RMB;
	}

	private void setX(int x) {
		MouseInput.getInstance().x = x;
	}

	private void setY(int y) {
		MouseInput.getInstance().y = y;
	}
}
