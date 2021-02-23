package assetManager;

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
// TODO: make sound class extend from the fileloader
public class Sound {
	
	private static Sound sound;
	
	private static final String DIRECTORY = "src/asset/sound/";
	
	private static HashMap <String, Clip> Clips;
	
	public Sound () {
		
		Clips = new HashMap <String, Clip> ();
		
		Clips.put("background", loadClip("bensound-tenderness"));
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
	
	/**
	 * Loads an audio clip to be played. 
	 * The clip must have 16 PCM, with a frequency of 44100 Hz
	 * 
	 * @param clipName  the name of the clip to load
	 * @return			the clip to be played
	 */
	public Clip loadClip(String clipName) {
		try{
            Clip myClip = AudioSystem.getClip();
            
            myClip.open(
        		AudioSystem.getAudioInputStream(
    				new File(
            			DIRECTORY + clipName + ".wav"
            		)
                )
	        );
            
            FloatControl gainControl = 
            		  (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            
			return myClip;
        } catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	public static void loopClip(String clipName) {
		Clips.get(clipName).loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void playClip(String clipName) {
		Clips.get(clipName).start();
	}
	
	public void pauseClip(String clipName) {
		try {
			Clips.get(clipName).wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stopClip(String clipName) {
		Clips.get(clipName).stop();
	}
}
