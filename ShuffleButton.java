import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * The shuffle button for the GUI.
 * 
 * @author Michael Nipper
 * 
 */
public class ShuffleButton extends Button implements ActionListener {

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
	 *            the Window object to attach the ProgressButton.
	 */
	public ShuffleButton(int x, int y, int width, int height,
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
			if (getMediaController().shuffleStatus()) {
				getMediaController().unshuffle();
				ImageIcon pause = new ImageIcon("images/shuffleButton.png");
				ImageIcon pausePressed = new ImageIcon(
						"images/shuffleButtonPressed.png");
				this.setGraphicUp(pause);
				this.setGraphicDown(pausePressed);
			} else {
				getMediaController().shuffle();
				ImageIcon pause = new ImageIcon(
						"images/shuffleButtonActive.png");
				ImageIcon pausePressed = new ImageIcon(
						"images/shuffleButtonActivePressed.png");
				this.setGraphicUp(pause);
				this.setGraphicDown(pausePressed);
				window.refreshLabels();
				progressBar.refresh(getMediaController());
				progressBar.start();
			}
		}
		this.setIcon(this.getGraphicUp());
		this.setPressedIcon(this.getGraphicDown());
	}

	/**
	 * Set a ProgressBar object for the ShuffleButton to control.
	 * 
	 * @param p
	 *            The ProgressBar object to control.
	 */
	public void setProgressBar(ProgressBar p) {
		progressBar = p;
	}

}
