package command;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class LoadClipsCommand implements FileCommand {

    private static LoadClipsCommand command;

    private String directory;
    private HashMap<String, Clip> clips;

    private LoadClipsCommand(String directory, HashMap<String, Clip> clips) {
        this.directory = directory;
        this.clips = clips;
    }

    public static LoadClipsCommand init(
            String directory,
            HashMap<String, Clip> clips
    ) {
        if (command == null) {
            command = new LoadClipsCommand(directory, clips);
        } else {
            command.directory = directory;
            command.clips = clips;
        }
        return command;
    }

    @Override
    public void execute(File pathName, String name) {
        try {
            String dirValue = pathName + "\\" + name;

            String key = name.substring(0, name.indexOf(".wav"));

            Clip myClip = AudioSystem.getClip();

            myClip.open(AudioSystem.getAudioInputStream(new File(dirValue)));

            clips.put(key, myClip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDirectory() {
        return directory;
    }

    public HashMap<String, Clip> getClips() {
        return clips;
    }
}
