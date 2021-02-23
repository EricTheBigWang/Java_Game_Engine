package userGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import userGUI.button.Button;

public abstract class Page {
	
	private ArrayList<Button> button;
	
	public Page () {
		button = new ArrayList<Button>();
	}
	
	public abstract void reset();
	
	public void update() {
		for (Button b: button) {
			b.update();
		}
	}
	
	public void render(Graphics g) {
		for (Button b: button) {
			b.render(g);
		}
		fadingTransition(g);
	}
	
	public void fadingTransition(Graphics g) {
		if (Screen.isTransitioning()) {
			g.setColor(
				new Color(
					255, 
					255, 
					255, 
					Screen.getOpacities().get(Screen.getTransitionTimer())
				)
			);
			g.fillRect(0, 0, Screen.getScreenLength(), Screen.getScreenHeight());
		}
	}
	
	public void add(Button button) {
		this.button.add(button);
	}
}