package userGUI.text;

import java.awt.Graphics;
import java.util.ArrayList;

public class Dialogue {
	private ArrayList<TypingText> texts;
	private int index;
	
	public Dialogue() {
		texts = new ArrayList<TypingText>();
		index = 0;
	}
	
	public void reset() {
		index = 0;
		for (TypingText t: texts) {
			t.reset();
		}
	}
	
	public void update() {
		if (0 <= index && index < texts.size()) {
			texts.get(index).update();
		}
	}
	
	public void render(Graphics g) {
		if (0 <= index && index < texts.size()) {
			texts.get(index).render(g);
		}
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		if (this.index != index)
			texts.get(this.index).reset();
		this.index = index;
	}
	
	public void add(TypingText text) {
		texts.add(text);
	}
	
	public int size() {
		return texts.size();
	}
	
	public TypingText getCurrentDialogue() {
		return texts.get(index);
	}
}
