import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * The previos button for the GUI.
 * 
 * @author Michael Nipper
 * 
 */
public class PreviousButton extends Button implements ActionListener {

	private Window window;
	private ProgressBar progressBar;

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
	 *            the window object for the PreviousButton to control.
	 */
	public PreviousButton(int x, int y, int width, int height,
			ImageIcon upImage, ImageIcon downImage, Window w) {
		super(x, y, upImage, downImage);
		addActionListener(this);
		this.setWidth(width);
		this.setHeight(height);
		window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getMediaController().isPlaylistSet()) {
			getMediaController().previousTrack();
			window.refreshLabels();
			progressBar.refresh(getMediaController());
			progressBar.start();
		}
	}

	/**
	 * Set a ProgressBar object for the PreviousButton to control.
	 * 
	 * @param p
	 *            The ProgressBar object to control.
	 */
	public void setProgressBar(ProgressBar p) {
		progressBar = p;
	}

}
