import java.util.ArrayList;
import java.util.Random;

/**
 * Stores a playlist of songs.
 * 
 * @author Michael Nipper
 * 
 */
public class Playlist {

	private ArrayList<Song> playlist;
	private ArrayList<Song> playlistUnshuffled;
	private String directory = "";
	private int index = 0;

	/**
	 * Generate a playlist for a given directory.
	 * 
	 * @param dir
	 *            A directory path containing Mp3s to use as a playlist.
	 */
	public Playlist(String dir) {

		directory = dir;
		playlist = new ArrayList<Song>();
		FileLoader songLoader = new FileLoader(dir);

		for (int i = 0; i < songLoader.getFileList().size(); i++) {
			Song newSong = new Song(songLoader.getFileList().get(i));
			playlist.add(newSong);
		}

	}

	/**
	 * Get the playlist (an ArrayList of song objects).
	 * 
	 * @return The ArrayList of song objects.
	 */
	public ArrayList<Song> getPlaylist() {

		return playlist;

	}

	/**
	 * Get a song object by its index in the playlist.
	 * 
	 * @param i
	 *            The index of the song object to get.
	 * @return The requested song object.
	 */
	public Song getSongByIndex(int i) {

		return playlist.get(i);
	}

	/**
	 * Get the current song object being played in the playlist.
	 * 
	 * @return The song object being played.
	 */
	public Song getSong() {
		return playlist.get(index);
	}

	/**
	 * Skip a song in the playlist.
	 * 
	 * @return The song object of the next song.
	 */
	public Song skipSong() {
		if (index < playlist.size() - 1)
			index++;
		else
			index = 0;
		return playlist.get(index);
	}

	/**
	 * Goes to the previous song in the playlist
	 * 
	 * @return The new song object.
	 */
	public Song previousSong() {
		if (index > 0)
			index--;
		else
			index = playlist.size() - 1;
		return playlist.get(index);
	}
	
	public int getIndex() {
		return index;
	}

	/**
	 * Get the current directory.
	 * 
	 * @return The current directory.
	 */
	public String getDirectory() {

		return directory;

	}

	/**
	 * Set a new directory for the playlist.
	 * 
	 * @param dir
	 *            A directory location containing mp3 files.
	 */
	public void setDirectory(String dir) {

		directory = dir;

	}

	/**
	 * Print the playlist filepaths to the console.
	 */
	public String toString() {

		String returnString = "";

		for (int i = 0; i < playlist.size(); i++) {
			returnString += playlist.get(i).getFilePath() + "\n";
		}

		return returnString;
	}

	/**
	 * Get a random song from the playlist.
	 * 
	 * @return A random song object.
	 */
	public Song getRandomSong() {
		Random randomNumberGenerator = new Random();
		int randomSong = randomNumberGenerator.nextInt(playlist.size());
		index = randomSong;
		return playlist.get(randomSong);
	}

	/**
	 * Shuffle the playlist.
	 */
	public void shuffleList() {
		playlistUnshuffled = playlist;

		for (int i = 0; i < playlist.size(); i++) {
			Random randomNumberGenerator = new Random();
			int randomSong = randomNumberGenerator.nextInt(playlist.size());
			Song temp = playlist.get(i);
			playlist.set(i, playlist.get(randomSong));
			playlist.set(randomSong, temp);
		}

		index = 0;

	}

	/**
	 * Unshuffle the playlist.
	 */
	public void unshuffleList() {
		playlist = playlistUnshuffled;
	}

}
