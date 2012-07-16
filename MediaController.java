/**
 * The class used by the developer for accessing a media controller.
 * 
 * @author Michael Nipper
 * 
 */
public class MediaController {

	private Playlist playlist;
	private Mp3Player player;
	private boolean shuffle = false;
	private boolean playlistSet = false;
	private boolean isPlaying = false;
	private PlayButton playButton;

	/**
	 * 
	 * @param p
	 *            The playlist object to use
	 */
	public MediaController(Playlist p) {
		playlist = p;
		player = new Mp3Player(playlist.getSong());
		playlistSet = true;
	}

	/**
	 * Defalult constructor.
	 */
	public MediaController() {
		player = new Mp3Player();
	}

	/**
	 * Play the current song and change the graphics of the play button.
	 */
	public void play() {

		isPlaying = true;
		player.play();

		playButton.makePauseButton();
	}

	/**
	 * Stop the player if it is currently playing.
	 */
	public void stop() {
		if (isPlaying)
			player.stop();
	}

	/**
	 * Pause the current song.
	 */
	public void pause() {
		player.pause();
	}

	/**
	 * Skip the current song and play the next one.
	 */
	public void nextTrack() {
		stop();

		playlist.skipSong();
		player.setSong(playlist.getSong());

		play();

	}

	/**
	 * Go to the previous song and play it.
	 */
	public void previousTrack() {
		stop();

		playlist.previousSong();
		player.setSong(playlist.getSong());

		play();

	}

	/**
	 * Skip to a particular track number.
	 * 
	 * @param index
	 *            The index of the song in the playlist
	 */
	public void jumpToTrack(int index) {
		stop();
		playlist.getSongByIndex(index);
		player.setSong(playlist.getSong());
		play();
	}

	/**
	 * Attach a new playlist object to this MediaController.
	 * 
	 * @param list
	 *            The playlist object to attach.
	 */
	public void loadNewPlaylist(Playlist list) {
		playlist = list;
		playlistSet = true;
	}

	/**
	 * Get the current song that the MediaController is controlling.
	 * 
	 * @return The current song object.
	 */
	public Song getCurrentSong() {
		return playlist.getSong();
	}

	/**
	 * Get the Mp3Player object to which this MediaController is currently
	 * attached.
	 * 
	 * @return The Mp3Player object
	 */
	public Mp3Player getMp3Player() {
		return player;
	}

	/**
	 * Shuffle the playlist.
	 */
	public void shuffle() {
		shuffle = true;
		playlist.shuffleList();
		stop();
		player.setSong(playlist.getSongByIndex(0));
		play();
		isPlaying = true;
	}

	/**
	 * Unshuffle the playlist.
	 */
	public void unshuffle() {
		shuffle = false;
		playlist.unshuffleList();
	}

	/**
	 * Retrurns if the MediaController is currently set to shuffle.
	 * 
	 * @return True if set to shuffle; False if not set to shuffle.
	 */
	public boolean shuffleStatus() {
		return shuffle;
	}

	/**
	 * Guard method to test if a playlist is currently attached to a
	 * MediaController.
	 * 
	 * @return True if a playlist is attached; false if a playlist is not.
	 *         attached.
	 */
	public boolean isPlaylistSet() {
		return playlistSet;
	}

	/**
	 * Test to see if the MediaController is currently paused.
	 * 
	 * @return True if MediaController is paused; false if it is not.
	 */
	public boolean isPaused() {
		return player.isPaused();
	}

	/**
	 * Provide a PlayButton object for the MediaController to control.
	 * 
	 * @param plyBtn
	 *            The playbutton object to control.
	 */
	public void loadPlayButton(PlayButton plyBtn) {
		playButton = plyBtn;
	}

}
