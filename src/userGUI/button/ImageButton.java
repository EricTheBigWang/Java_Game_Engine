package userGUI.button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageButton extends Button {
	
	private BufferedImage releasedImage;
	private BufferedImage hoveredImage;
	private BufferedImage pressedImage;
		
	public ImageButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		switch (currentState) {
			case RELEASED: 
				g.drawImage(
					releasedImage, 
					clickBox.x + (clickBox.width - releasedImage.getWidth()) / 2, 
					clickBox.y + (clickBox.height - releasedImage.getHeight()) / 2, 
					null
				); 
				break;
			case HOVERED: 
				g.drawImage(
					hoveredImage, 
					clickBox.x + (clickBox.width - releasedImage.getWidth()) / 2, 
					clickBox.y + (clickBox.height - releasedImage.getHeight()) / 2, 
					null
				);
				break;
			case PRESSED: 
				g.drawImage(
					pressedImage, 
					clickBox.x + (clickBox.width - releasedImage.getWidth()) / 2, 
					clickBox.y + (clickBox.height - releasedImage.getHeight()) / 2, 
					null
				);
				break;
		}
	}
	
	public void setSprites(BufferedImage released, BufferedImage pressed, BufferedImage hovered) {
		this.releasedImage = released;
		this.pressedImage = pressed;
		this.hoveredImage = hovered;
	}
	
	public void setSprite(BufferedImage image) {
		this.releasedImage = image;
		this.pressedImage = image;
		this.hoveredImage = image;
	}
}
