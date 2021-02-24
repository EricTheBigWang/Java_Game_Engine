package userGUI.taskBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import assetManager.Texture;
import main.Main;
import userGUI.Frame;
import userGUI.button.ImageButton;

public class MaximizeButton extends ImageButton {
	
	private static MaximizeButton maximizeButton;
	
	private MaximizeButton() {
		super(
			Frame.getLength() - TaskBar.BUTTONSPACING - 2 * TaskBar.BUTTONWIDTH,
			0,
			TaskBar.BUTTONWIDTH,
			TaskBar.getInstance().getHeight()
		);
		
		setColors(new Color(240, 240, 240), new Color(225, 225, 225), new Color(210, 210, 210));
		
		setSprite(Texture.get("maximize_icon"));
		
		addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				Frame.setMaximized(!Frame.isMaximized());
				
				if (Frame.isMaximized()) {
					Main.getFrame().setLocation(0, 0);

					// TODO Fix
					// Frame.setDefaultHeight(Frame.getHeight());
					// Frame.setDefaultLength(Frame.getLength());
					
					Main.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				} else {
					Main.getFrame().setSize(new Dimension(Frame.getDefaultLength(), Frame.getDefaultHeight()));
					
					Frame.setLength(Main.getLength());
			    	Frame.setHeight(Main.getHeight());
					
					Main.getFrame().setLocationRelativeTo(null);
				}
				
				System.out.println("Maximized");
			}
		});
	}
	
	public static MaximizeButton getInstance() {
		if (maximizeButton == null)
			maximizeButton = new MaximizeButton();
		return maximizeButton;
	}
	
	public void update () {
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
	
	public void render (Graphics g) {
		super.render(g);
	}
	
	public void setLocation() {
		super.setLocation(
				Frame.getLength() - TaskBar.BUTTONSPACING - 2 * TaskBar.BUTTONWIDTH,
				0
		);
	}
}
