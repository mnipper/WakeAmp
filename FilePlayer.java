/**
 * For use in expanding the Mp3 player to play other file types.
 * @author Michael Nipper
 *
 */
public interface FilePlayer {

	public void play();

	public void stop();

	public void setSong(Song song);
	
	public void pause();
	
	public boolean isPaused();

}
