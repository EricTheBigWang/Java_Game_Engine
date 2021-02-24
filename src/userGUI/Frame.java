package userGUI;

import main.Main;
import userGUI.taskBar.TaskBar;

public class Frame {
    private static Frame frame;

    private static int minHeight;
    private static int minLength;
    private static int defaultHeight;
    private static int defaultLength;
    private static int height;
    private static int length;

    private static final int BORDERLENGTH = 4;

    private static boolean isResizing;
    private static boolean isMaximized;

    private Frame() {
        minLength = 3 * TaskBar.getButtonWidth() - 2 * TaskBar.getButtonSpacing() + 2 * BORDERLENGTH;
        minHeight = TaskBar.getInstance().getHeight() + 2 * BORDERLENGTH;

        defaultLength = 400; // screenLength;
        defaultHeight = 400; // screenHeight;

        length = defaultLength;
        height = defaultHeight;
    }

    public static Frame getInstance() {
        if (frame == null)
            frame = new Frame();
        return frame;
    }

    public static void update() {
        length = Main.getLength();
        height = Main.getHeight();
    }

    public static boolean isResizing() {
        return Frame.isResizing;
    }

    public static void setResizing(boolean isResizing) {
        Frame.isResizing = isResizing;
    }

    public static boolean isMaximized() {
        return isMaximized;
    }

    public static void setMaximized(boolean isMaximized) {
        Frame.isMaximized = isMaximized;
    }

    public static int getHeight() {
        return height;
    }

    public static int getLength() {
        return length;
    }

    public static int getBorderLength() {
        return BORDERLENGTH;
    }

    public static int getDefaultHeight() {
        return defaultHeight;
    }

    public static int getDefaultLength() {
        return defaultLength;
    }

    public static int getMinHeight() {
        return minHeight;
    }

    public static int getMinLength() {
        return minLength;
    }

    public static void setLength(int length) {
        Frame.length = length;
    }

    public static void setHeight(int height) {
        Frame.height = height;
    }
}
