import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * The load button for the GUI.
 * @author Michael Nipper
 *
 */

public class LoadButton extends Button implements ActionListener {

	private Window window;

	/**
	 * @param x
	 *            x-coordinate for the button
	 * @param y
	 *            y-coordinate for the button
	 * @param width
	 *            width of the button
	 * @param height
	 *            height of the button
	 * @param upImage
	 *            image to show when the button is not being pressed
	 * @param downImage
	 *            image to show when the button is being pressed
	 * @param w
	 * 			  Window object to which the load button is attached.
	 */
	public LoadButton(int x, int y, int width, int height, ImageIcon upImage,
			ImageIcon downImage, Window w) {
		super(x, y, upImage, downImage);
		addActionListener(this);
		this.setWidth(width);
		this.setHeight(height);
		window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			Playlist playlist = new Playlist("" + chooser.getSelectedFile());
			getMediaController().getMp3Player().setSong(
					playlist.getSongByIndex(0));
			getMediaController().loadNewPlaylist(
					new Playlist("" + chooser.getSelectedFile()));
			window.refreshLabels();
		}

	}

	

}
