package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import assetManager.Data;
import assetManager.Sound;
import assetManager.SpriteSheetLoader;
import assetManager.Texture;
import information.Setting;
import input.KeyInput;
import input.MouseInput;
import loop.Loop;
import toolkit.Debug;
import toolkit.Tools;
import userGUI.Screen;
import userGUI.taskBar.TaskBar;

/**
 * A framework for the entire program.
 * 
 * @author  Yi Feng Wang
 * @version 1.0
 * @since   2020-12-23
 */
public class Main {
	
	private static Main main;
	
	private static JFrame frame;
	
	private static boolean isFocused;
	private static boolean isIconified;
	
	/**
	 * Main constructor.
	 */
	private Main() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame();
	}
	
	/**
	 * Singleton instance of the Main.
	 * 
	 * @return  singleton instance
	 */
	public static Main getInstance () {
		if (main == null)
			main = new Main();
		return main;
	}
	
	/**
	 * Main method.
	 * 
	 * @param args  does nothing.
	 */
   	public static void main(String[] args) {
   		SwingUtilities.invokeLater(
   			new Runnable() {
	   			public void run() {
	   				Main.getInstance();
	   				Setting.getInstance();
	   				Texture.getInstance();
	   				SpriteSheetLoader.getInstance();
	   				Sound.getInstance();
	   				Data.getInstance();
	   				Tools.getInstance();
	   				KeyInput.getInstance();
	   				MouseInput.getInstance();
	   				Screen.getInstance();
		   		   	addStuffToFrame();
		   			Debug.getInstance();
		   			frame.setVisible(true);
	   		   		new Timer(1, Loop.getInstance()).start();
	   			}
   			}
   		);
   	}
   	
   	private static void addStuffToFrame() {
   		setFrameBorder();
		frame.getLayeredPane().getComponent(1).setPreferredSize(new Dimension(0, 0));
		frame.getContentPane().add(TaskBar.getInstance(), BorderLayout.NORTH);
   		setFrameDimensions();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		frame.getContentPane().add(Screen.getInstance());
   		frame.pack();
   		frame.setLocationRelativeTo(null);
	   	addFrameListeners();
   	}
   	
   	private static void setFrameBorder() {
   		frame.getRootPane().setBorder(
			BorderFactory.createLineBorder(
				new Color(230, 230, 230), 
				Setting.getBorderLength()
			)
		);
   	}
   	
   	private static void setFrameDimensions() {
		frame.setMinimumSize(
    		new Dimension(
    			Setting.getMinFrameLength(), 
    			Setting.getMinFrameHeight()
    		)
    	);
    	
    	frame.getContentPane().setPreferredSize(
    		new Dimension(
    			Setting.getFrameLength(), 
    			Setting.getFrameHeight()
    		)
    	);
	}
   	
   	private static void addFrameListeners() {
   		frame.addWindowFocusListener(
	   		new WindowAdapter() {
   				@Override
   		   		public void windowGainedFocus(WindowEvent e) {
   			    	isFocused = true;
   			    }

   				@Override
   			    public void windowLostFocus(WindowEvent e) {
   			    	MouseInput.getInstance().reset();
   			    	isFocused = false;
   			    }
	   		}
	   	);

	   	frame.addWindowListener(
	   		new WindowAdapter() {
	   			/**
	   			 * Account for ALT F4 edge case.
	   			 *
	   			 * @param e  does nothing
	   			 */
	   			@Override
		    	public void windowClosing(WindowEvent e) {
		    		System.out.println("closed");
		    		Data.writeData();
		    		System.exit(0);
		    	}

   		   		@Override
   				public void windowIconified(WindowEvent e) {
   		   			isIconified = true;
   			    }

   		   		@Override
   		   		public void windowDeiconified(WindowEvent e) {
   		   			isIconified = false;
   		   	 	}
	   		}
	   	);
   	}
   	
   	public static JFrame getFrame() {
   		return frame;
   	}
   	
   	public static int getLength() {
   		return frame.getContentPane().getWidth();
   	}
   	
   	public static int getHeight() {
   		return frame.getContentPane().getHeight();
   	}
   	
   	public static boolean isFocused() {
		return isFocused;
	}

	public static void setFocused(boolean isFocused) {
		Main.isFocused = isFocused;
	}
	
	public static boolean isIconified() {
		return isIconified;
	}

	public static void setIconified(boolean isIconified) {
		Main.isIconified = isIconified;
	}
}
