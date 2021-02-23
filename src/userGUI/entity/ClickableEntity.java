package userGUI.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import input.MouseInput;
import userGUI.Screen;

public class ClickableEntity {
	
	protected Rectangle clickBox;
	protected ArrayList<ActionListener> actionListeners;
	
	protected State currentState = State.RELEASED;
	protected enum State {RELEASED, HOVERED, PRESSED}
	
	/**
	 * Button constructor.
	 * 
	 * @param x       the x coordinate of the button
	 * @param y       the y coordinate of the button
	 * @param length  the length of a button
	 * @param height  the height of a button
	 */
	public ClickableEntity(int x, int y, int length, int height) {
		clickBox = new Rectangle(x, y, length, height);
		actionListeners = new ArrayList <ActionListener> ();
	}
	
	public void update() {
		if (clickBox.contains(MouseInput.X(), MouseInput.Y())) {
			if (MouseInput.LMB()) {
				currentState = State.PRESSED;
			} else {
				if (currentState == State.PRESSED) {
					for (ActionListener all : actionListeners) {
						all.actionPerformed(null);
					}
					// audio.play("select", 0);
				}
				currentState = State.HOVERED;
			}
		} else {
			currentState = State.RELEASED;
		}
	}
	
	public void render(Graphics g) {}
	
	/**
	 * Assign an action to the button. The action is performed if clicked.
	 * 
	 * @param listener
	 */
	public void addActionListener(ActionListener listener) {
		actionListeners.add(listener);
	}
	
	/**
	 * Update the location of the button.
	 * 
	 * @param x  the new x coordinate of the button
	 * @param y  the new y coordinate of the button
	 */
	public void setLocation(int x, int y) {
		clickBox.x = x;
		clickBox.y = y;
	}
	
	/**
	 * Transition to a different page.
	 * 
	 * @param page      the current page the button belongs to
	 * @param pageName  the name of the page to be transitioned to
	 */
	public void transitionPage(final String pageName) {
		this.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Screen.setPage(pageName);
				}
			}
		);
	}
	
	public State getState() {
		return currentState;
	}
	
	public void setX(int x) {
		clickBox.x = x;
	}
	
	public void setY(int y) {
		clickBox.y = y;
	}
	
	public int getX() {
		return clickBox.x;
	}
	
	public int getY() {
		return clickBox.y;
	}
	
	public int getLength() {
		return clickBox.width;
	}
	
	public void setLength(int length) {
		clickBox.width = length;
	}
	
	public int getHeight() {
		return clickBox.height;
	}
	
	public void setHeight(int height) {
		clickBox.height = height;
	}
}
