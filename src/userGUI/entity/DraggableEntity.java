package userGUI.entity;

import java.awt.Color;
import java.awt.Graphics;

import input.MouseInput;
import toolkit.Tools;
import userGUI.Screen;

public abstract class DraggableEntity {
	
	protected double x, y, offsetX, offsetY;
	protected int height, width;
	protected static boolean trigger;
	
	public DraggableEntity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		if (MouseInput.LMB() && !trigger && Tools.isMouseInside(x, y, width, height)) {
			trigger = true;
			offsetX = MouseInput.X() - this.x;
			offsetY = MouseInput.Y() - this.y;
		}

		if (trigger) {
			this.x = MouseInput.X() - offsetX;
			this.y = MouseInput.Y() - offsetY;
		}
		
		if (!MouseInput.LMB())
			trigger = false;
	}
	
	public void render (Graphics g) {
		if (Tools.inRange((int) this.x, -this.width, Screen.getScreenLength()) && 
			Tools.inRange((int) this.y, -this.height, Screen.getScreenHeight())) {
			
			g.setColor(Color.BLACK);
			g.fillRect((int) this.x, (int) this.y, width, height);
		}
	}
	
	public static boolean trigger () {
		return trigger;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}