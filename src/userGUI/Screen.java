package userGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import assetManager.Sound;
import factory.Factory;
import input.MouseInput;
import main.Main;
import toolkit.Debug;
import toolkit.Tools;
import userGUI.taskBar.TaskBar;

/**
 * A class that manages and renders all objects in the program.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-04-28 
 */
@SuppressWarnings("serial")
public class Screen extends JPanel {
	
	private static Screen screen;

	private static HashMap <String, Page> pages;
	private static String currentPage;
	private static boolean isTransitioning;
	private static int transitionTimer;
	private static final double TRANSITIONDURATION = 10.0;
	private static String endPage;
	private static ArrayList<Integer> opacities;

	private static final int HORIZONTALPADDING = 0;
	private static final int VERTICALPADDING = 0;

	private static int screenX;
	private static int screenY;
	private static int screenHeight;
	private static int screenLength;

	/**
	 * Screen constructor.
	 */
	private Screen() {
		opacities = new ArrayList<Integer>();
		for (int i = 0; i < 2 * TRANSITIONDURATION; i++) {
			opacities.add(
				Tools.ensureRange(
					(int) (255 * Math.sin(Math.PI * i / (2.0 * TRANSITIONDURATION))), 0, 255
				)
			);
		}
		
		Screen.pages = new HashMap<String, Page>();
	}
	
	/**
	 * Singleton instance of the screen.
	 * 
	 * @return  singleton instance
	 */
	public static Screen getInstance() {
		if (screen == null) {
			screen = new Screen();
			loadPages();
		}
		return screen;
	}
	
	/**
	 * Reset all attribute values of objects to their default values.
	 */
	public static void reset() {
		if (pages.get(currentPage) != null) {
			pages.get(currentPage).reset();
		}
	}
	
	/**
	 * Update all objects on the screen.
	 */
	public void update() {
		screenX = HORIZONTALPADDING;
		screenY = VERTICALPADDING;
		screenLength = Frame.getLength() - 2 * HORIZONTALPADDING;
		screenHeight = Frame.getHeight() - 2 * VERTICALPADDING - TaskBar.getInstance().getHeight();

		if (isTransitioning) {
			transitionTimer++;
			if (transitionTimer == TRANSITIONDURATION) {
				reset();
				currentPage = endPage;
			}
			
			if (transitionTimer == (2.0 * TRANSITIONDURATION)) {
				transitionTimer = 0;
				isTransitioning = false;
			}
		}
		
		if (pages.get(currentPage) != null) {
			pages.get(currentPage).update();
		}
		
		Debug.update();
		repaint();
	}
	
	/**
	 * Render objects on the screen regardless of the current page.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		((Graphics2D) g).setRenderingHint(
			RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON
		);
		
		((Graphics2D) g).setRenderingHint(
			RenderingHints.KEY_COLOR_RENDERING, 
			RenderingHints.VALUE_COLOR_RENDER_QUALITY
		);
		
		((Graphics2D) g).setRenderingHint(
			RenderingHints.KEY_ALPHA_INTERPOLATION,
			RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
		);
		
		((Graphics2D) g).setRenderingHint(
			RenderingHints.KEY_RENDERING, 
			RenderingHints.VALUE_RENDER_QUALITY
		);

		g.setColor(new Color(25, 25, 25));
		g.fillRect(0, 0, Main.getLength(), Main.getHeight());

		// Fill the game screen with a default color
		g.translate(screenX, screenY);
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, screenLength, screenHeight);
		render(g);
		g.translate(-screenX, -screenY);
		Debug.render(g);
		
		MouseInput.getInstance().render(g);
	}

	/**
	 * Render all objects on the current page.
	 * 
	 * @param g  graphics from paintComponent
	 */
	public static void render(Graphics g) {
		if (pages.get(currentPage) != null) {
			pages.get(currentPage).render(g);
		}
	}
	
	/**
	 * Load all the pages.
	 */
	public static void loadPages() {
		Factory pageFactory = new Factory("src/page", ".java");
		for (HashMap.Entry<String, String> entry : pageFactory.getFileDirectories().entrySet()) {
		    addPage(entry.getKey(), (Page) pageFactory.createProduct(entry.getKey()));
		}
		Screen.init("MyPage");
	}
	
	/**
	 * Add a page to the screen.
	 * 
	 * @param pageName  the name of the page to be added
	 * @param page      the page class that corresponds to the name
	 */
	public static void addPage(String pageName, Page page) {
		pages.put(pageName, page);
	}
	
	/**
	 * Transition to the current page to be updated and rendered.
	 * 
	 * @param pageName  the name of the page to be transitioned to
	 * @throw           IllegalArgumentException is the page is not found.
	 */
	public static void setPage(String pageName) {
		if (!pages.containsKey(pageName))
			throw new IllegalArgumentException("Page " + pageName + " not found.");
		isTransitioning = true;
		endPage = pageName;
	}
	
	public static int getScreenX() {
		return screenX;
	}

	public static int getScreenY() {
		return screenY;
	}
	
	public static int getScreenLength() {
		return screenLength;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public static String getCurrentPage() {
		return currentPage;
	}
	
	public static void init(String pageName) {
		currentPage = pageName;
	}
	
	public static ArrayList<Integer> getOpacities() {
		return opacities;
	}

	public static boolean isTransitioning() {
		return isTransitioning;
	}

	public static int getTransitionTimer() {
		return transitionTimer;
	}

	public static void setTransitionTimer(int transitionTimer) {
		Screen.transitionTimer = transitionTimer;
	}

	public static final int getVerticalPadding() {
		return VERTICALPADDING;
	}

	public static final int getHorizontalPadding() {
		return HORIZONTALPADDING;
	}
}