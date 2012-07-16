import java.io.File;
import javax.media.*;
import javax.media.format.AudioFormat;

/**
 * A Mp3 player class. Should be accessed through MediaController.
 * 
 * @author Michael Nipper
 * 
 */
public class Mp3Player implements FilePlayer {

	private Song currentSong;
	private Player player;
	private Time mediaTime;
	private boolean paused = false;

	/**
	 * Create a Mp3Player object and attach a song object.
	 * @param song
	 *            A song object for the Mp3Player to control.
	 */
	public Mp3Player(Song song) {
		currentSong = song;
	}

	/**
	 * Default constructor.
	 */
	public Mp3Player() {}

	/**
	 * Play the currently attached song object.
	 */
	public void play() {
		if (paused) {
			player.setMediaTime(mediaTime);
			player.start();
			paused = false;
		} else {
			Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
			Format output = new AudioFormat(AudioFormat.MPEGLAYER3);
			PlugInManager.addPlugIn(
					"com.sun.media.codec.audio.mp3.JavaDecoder",
					new Format[] { input1 }, new Format[] { output },
					PlugInManager.CODEC);
			try {
				player = Manager.createPlayer(new MediaLocator(new File(
						currentSong.getFilePath()).toURI().toURL()));
				player.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * Stop the currently attached song object.
	 */
	public void stop() {
		player.stop();
		paused = false;
	}

	/**
	 * Set a song object to this Mp3Player.
	 */
	public void setSong(Song song) {
		currentSong = song;
	}

	/**
	 * Pause the currently attached song object.
	 */
	public void pause() {
		paused = true;
		mediaTime = player.getMediaTime();
		player.stop();
	}

	/**
	 * Test if the Mp3Player object is currently paused.
	 */
	public boolean isPaused() {
		return paused;
	}

}
