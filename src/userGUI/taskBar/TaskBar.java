package userGUI.taskBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import information.Setting;
import main.Main;
import userGUI.taskBar.icon.Icon;
import userGUI.text.Text;

@SuppressWarnings("serial")
public class TaskBar extends JPanel {
	
	public static TaskBar taskBar;
	
	protected static final double GOLDENRATIO = 1.6180339887498948420;
	protected static final int BUTTONWIDTH = (int) (Setting.getTaskBarHeight() * GOLDENRATIO);
	protected static final int BUTTONSPACING = 0;
	
	private static boolean isMoving;
	private static boolean isFocused;

	private static int mouseX, mouseY;	
	private static boolean LMB;
	private static boolean RMB;
	private static int xPos, yPos;
	
	private static final Text TASKBARTEXT = new Text("GAME", 42, 8);
	
	private TaskBar() {
		setPreferredSize(	
			new Dimension(
				Setting.getFrameLength() - 2 * Setting.getBorderLength(), 
				Setting.getTaskBarHeight()
			)
		);
		
		TaskBar.isMoving = false;
		
		addMouseListener(
			new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					xPos = e.getX();
					yPos = e.getY();
					LMB = LMB || SwingUtilities.isLeftMouseButton(e);
					RMB = RMB || SwingUtilities.isRightMouseButton(e);
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					LMB = LMB && !SwingUtilities.isLeftMouseButton(e);
					RMB = RMB && !SwingUtilities.isRightMouseButton(e);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					isFocused = true;
				}

				@Override
				public void mouseExited(MouseEvent e) {
					isFocused = false;
					mouseX = -1;
					mouseY = -1;
				}
			}
		);
		
		addMouseMotionListener(
			new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();
					if (0 <= xPos && xPos <= getWidth() - 3 * BUTTONWIDTH - 2 * BUTTONSPACING) {
						Main.getFrame().setLocation(
							Main.getFrame().getX() + e.getX() - xPos, 
							Main.getFrame().getY() + e.getY() - yPos
						);
					}
				}
				
				@Override
				public void mouseMoved(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();
				}
			}
		);
	}
	
	public static TaskBar getInstance() {
		if (taskBar == null)
			taskBar = new TaskBar();
		return taskBar;
	}
	
	public void update () {
		Icon.getInstance().update();
		ExitButton.getInstance().update();
		MaximizeButton.getInstance().update();
		MinimizeButton.getInstance().update();
		
		// TODO, check for border resizing
		ExitButton.getInstance().setLocation();
		MaximizeButton.getInstance().setLocation();
		MinimizeButton.getInstance().setLocation();
		
		repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, getWidth(), getHeight());
		
        TASKBARTEXT.render(g);
        
        Icon.getInstance().render(g);
        ExitButton.getInstance().render(g);
		MaximizeButton.getInstance().render(g);
		MinimizeButton.getInstance().render(g);
    }
	
	public static double getGoldenratio() {
		return GOLDENRATIO;
	}
	
	public static int getButtonwidth() {
		return BUTTONWIDTH;
	}
	
	public static boolean isFocused() {
		return isFocused;
	}

	public static boolean LMB() {
		return LMB;
	}

	public static boolean RMB() {
		return RMB;
	}

	public static void setFocused(boolean isFocused) {
		TaskBar.isFocused = isFocused;
	}

	public static int getButtonSpacing() {
		return BUTTONSPACING;
	}

	public static boolean isMoving () {
		return TaskBar.isMoving;
	}
	
	public static int getMouseX() {
		return mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}
}
