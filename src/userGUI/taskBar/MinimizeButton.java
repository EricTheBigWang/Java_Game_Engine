package userGUI.taskBar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import assetManager.Texture;
import information.Setting;
import main.Main;
import userGUI.button.ImageButton;

public class MinimizeButton extends ImageButton{
	
	private static MinimizeButton minimizeButton;
	
	private MinimizeButton() {
		super(
			Setting.getFrameLength() - 2 * TaskBar.BUTTONSPACING - 3 * TaskBar.BUTTONWIDTH,
			0, 
			TaskBar.BUTTONWIDTH, 
			Setting.getTaskBarHeight()
		);
		
		setColors(new Color(240, 240, 240), new Color(225, 225, 225), new Color(210, 210, 210));
		setSprite(Texture.get("minimize_icon"));
		
		addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				Main.getFrame().setExtendedState(JFrame.ICONIFIED);
				System.out.println("Minimized");
			}
		});
	}
	
	public static MinimizeButton getInstance() {
		if (minimizeButton == null)
			minimizeButton = new MinimizeButton();
		return minimizeButton;
	}
	
	public void update() {
		if (TaskBar.isFocused() && clickBox.contains(TaskBar.getMouseX(), TaskBar.getMouseY())) {
			if (TaskBar.LMB()) {
				currentState = State.PRESSED;
			} else {
				if (currentState == State.PRESSED) {
					for (ActionListener all : actionListeners) {
						all.actionPerformed(null);
					}
				}
				currentState = State.HOVERED;
			}
		} else {
			currentState = State.RELEASED;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	public void setLocation() {
		super.setLocation(
			Setting.getFrameLength() - 2 * TaskBar.BUTTONSPACING - 3 * TaskBar.BUTTONWIDTH, 
			0
		);
	}
}
