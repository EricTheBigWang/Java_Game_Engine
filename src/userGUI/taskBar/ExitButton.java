package userGUI.taskBar;

import java.awt.Color;
import java.awt.Graphics;

import assetManager.Data;
import assetManager.Texture;
import information.Setting;
import userGUI.button.ImageButton;

public class ExitButton extends ImageButton {
	
	private static ExitButton exitButton;
	
	private ExitButton() {
		super(
			Setting.getFrameLength() - TaskBar.BUTTONWIDTH - 2 * Setting.getBorderLength(), 
			0,
			TaskBar.BUTTONWIDTH, 
			Setting.getTaskBarHeight()
		);
		
		setColors(new Color(240, 240, 240), new Color(225, 225, 225), new Color(210, 210, 210));
		
		setSprite(Texture.get("exit_icon"));
	}
	
	public static ExitButton getInstance() {
		if (exitButton == null)
			exitButton = new ExitButton();
		return exitButton;
	}
	
	public void update() {
		if (TaskBar.isFocused() && clickBox.contains(TaskBar.getMouseX(), TaskBar.getMouseY())) {
			if (TaskBar.LMB()) {
				currentState = State.PRESSED;
			} else {
				if (currentState == State.PRESSED) {
					
					// TODO find way of avoiding IndexError
					System.out.println("Exited");
					Data.writeData();
					System.exit(0);
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
		super.setLocation(Setting.getFrameLength() - TaskBar.BUTTONWIDTH, 0);
	}
}
