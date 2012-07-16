import java.io.File;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

/** COMMENTED OUT IMPORTS **/
//import org.jaudiotagger.tag.Tag;
//import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
//import org.jaudiotagger.tag.id3.ID3v1Tag;
//import org.jaudiotagger.tag.id3.ID3v24Tag;

/**
 * Stores the information about a particular song.
 * 
 * @author Michael Nipper
 * 
 */
public class Song {

	private String filePath = "";
	private String length = "";
	private String artistName = "";
	private String title = "";
	private int songLengthSeconds = 0;

	/**
	 * Create a song object from a file path.
	 * 
	 * @param file
	 *            The file path of the song to create a song object.
	 */
	public Song(String file) {
		filePath = file;
		File songFile = new File(file);

		try {

			MP3File f = (MP3File) AudioFileIO.read(songFile);
			MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();

			if (audioHeader != null) {
				int secs = audioHeader.getTrackLength() % 60;
				String secondLabel = "" + secs;

				if (secs < 10) {
					secondLabel = "0" + secs;
				}

				length = audioHeader.getTrackLength() / 60 + ":" + secondLabel;
				songLengthSeconds = audioHeader.getTrackLength();
				/*
				 * FOR READING ID INFO FROM MP3, not working correctly.
				 * 
				 * 
				 * 
				 * Tag tag = f.getTag(); AbstractID3v2Tag v2tag =
				 * f.getID3v2Tag(); AbstractID3v2Tag v24tag =
				 * (AbstractID3v2Tag)f.getID3v2TagAsv24(); title =
				 * v1Tag.getTitle().get(0).toString();
				 */

				artistName = file.substring(file.lastIndexOf("\\") + 1,
						file.lastIndexOf(".mp3"));

			}

		} catch (Exception e) {
		}

	}

	/**
	 * Get the current file path.
	 * 
	 * @return The filepath of the song object.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Set the filepath for the song object.
	 * 
	 * @param file
	 *            The filepath.
	 */
	public void setFilePath(String file) {
		this.filePath = file;
	}

	/**
	 * Get the length of the song.
	 * 
	 * @return A length, formated mm:ss.
	 */
	public String getLength() {
		return length;
	}

	/**
	 * Set the length of a song.
	 * 
	 * @param length
	 *            A length, formated mm:ss.
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * Get the artist name.
	 * 
	 * @return The artist name.
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * Set the artist name.
	 * 
	 * @param artistName
	 *            The artist name.
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * Get the title of the song.
	 * 
	 * @return The title of the song.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the song.
	 * 
	 * @param title
	 *            The title of the song.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the song length in seconds.
	 * 
	 * @return The song length in seconds.
	 */
	public int getSongLengthSeconds() {
		return songLengthSeconds;
	}

	/**
	 * Set the song length in seconds.
	 * 
	 * @param songLengthSeconds
	 *            The song length in seconds.
	 */
	public void setSongLengthSeconds(int songLengthSeconds) {
		this.songLengthSeconds = songLengthSeconds;
	}

}
