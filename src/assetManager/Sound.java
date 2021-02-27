package assetManager;

import command.LoadClipsCommand;
import command.LoadPNGCommand;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Load all the sound files.
 * 
 * @author Yi Feng Wang
 */
// TODO: make sound class extend from the file loader
public class Sound extends FileLoader {
	
	private static Sound sound;
	
	private static final String DIRECTORY = "src/asset/sound/";
	
	private static HashMap <String, Clip> clips;
	
	private Sound() {
		super(DIRECTORY, ".wav");

		clips = new HashMap <String, Clip> ();
		
		// clips.put("background", loadClip("bensound-tenderness"));
		loadFiles(LoadClipsCommand.init(DIRECTORY, clips));
	}
	
	/**
	 * Singleton instance of Sound.
	 * 
	 * @return  singleton instance
	 */
	public static Sound getInstance() {
		if (sound == null)
			sound = new Sound();
		return sound;
	}

	public static void loopClip(String clipName) {
		clips.get(clipName).loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void playClip(String clipName) {
		clips.get(clipName).start();
	}
	
	public void pauseClip(String clipName) {
		try {
			clips.get(clipName).wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stopClip(String clipName) {
		clips.get(clipName).stop();
	}
}
