package information;

import main.Main;

/**
 * 
 * @author WYF
 */
public class Setting {
	
	private static Setting setting;
	
	private static int minFrameHeight;
	private static int minframeLength;
	private static int screenHeight;
	private static int screenLength;
	private static int defaultFrameHeight;
	private static int defaultFrameLength;
	private static int frameHeight;
	private static int frameLength;
	
	private static boolean isResizing;
	private static boolean isMaximized;
	
	private static final int BORDERLENGTH = 4;
	
	private static final int TASKBARHEIGHT = 30;

	private static final int SCREENPADDING = 0;
	
	private Setting() {
		minframeLength = 288;
		minFrameHeight = 288 + TASKBARHEIGHT + 2 * BORDERLENGTH;
		
		screenLength = minframeLength;
		screenHeight = minFrameHeight - TASKBARHEIGHT - 2 * BORDERLENGTH;
		
		defaultFrameLength = screenLength;
		defaultFrameHeight = screenHeight;
		
		frameLength = defaultFrameLength;
		frameHeight = defaultFrameHeight;
	}
	
	public static Setting getInstance() {
		if (setting == null)
			setting = new Setting();
		return setting;
	}
	
	public static void update() {
		frameLength = Main.getLength();
		frameHeight = Main.getHeight();
	}	
	
	public static int getDefaultFrameLength() {
		return defaultFrameLength;
	}

	public static void setDefaultFrameLength(int defaultFrameLength) {
		Setting.defaultFrameLength = defaultFrameLength;
	}

	public static int getDefaultFrameHeight() {
		return defaultFrameHeight;
	}

	public static void setDefaultFrameHeight(int defaultFrameHeight) {
		Setting.defaultFrameHeight = defaultFrameHeight;
	}

	public static int getMinFrameHeight() {
		return Setting.minFrameHeight;
	}

	public static int getMinFrameLength() {
		return Setting.minframeLength;
	}

	public static boolean isResizing() {
		return Setting.isResizing;
	}

	public static void setResizing(boolean isResizing) {
		Setting.isResizing = isResizing;
	}
	
	public static boolean isMaximized() {
		return Setting.isMaximized;
	}

	public static void setMaximized(boolean isMaximized) {
		Setting.isMaximized = isMaximized;
	}

	public static int getScreenHeight() {
		return Setting.screenHeight;
	}

	public static int getScreenLength() {
		return Setting.screenLength;
	}
	
	public static int getFrameHeight() {
		return Setting.frameHeight;
	}
	
	public static int getFrameLength() {
		return Setting.frameLength;
	}
	
	public static void setFrameLength(int frameLength) {
		Setting.frameLength = frameLength;
	}
	
	public static void setFrameHeight(int frameHeight) {
		Setting.frameHeight = frameHeight;
	}
	
	public static final int getBorderLength() {
		return Setting.BORDERLENGTH;
	}
	
	public static final int getScreenPadding() {
		return Setting.SCREENPADDING;
	}
	
	public static final int getTaskBarHeight() {
		return TASKBARHEIGHT;
	}
}
