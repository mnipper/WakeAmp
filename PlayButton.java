import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * The play/pause button for the GUI.
 * 
 * @author Michael Nipper
 * 
 */
public class PlayButton extends Button implements ActionListener {

	private boolean isPlayButton = true;
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
	 */
	public PlayButton(int x, int y, int width, int height, ImageIcon upImage,
			ImageIcon downImage) {
		super(x, y, upImage, downImage);
		addActionListener(this);
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getMediaController().isPlaylistSet()) {
			if (isPlayButton) {
				getMediaController().play();
				progressBar.start();
				makePauseButton();
			} else {
				getMediaController().pause();
				progressBar.stop();
				makePlayButton();
			}

		}

	}

	/**
	 * Adjust the graphics of the play button to make it a pause button.
	 */
	public void makePlayButton() {
		isPlayButton = true;
		ImageIcon pause = new ImageIcon("images/playButton.png");
		ImageIcon pausePressed = new ImageIcon("images/playButtonPressed.png");
		this.setGraphicUp(pause);
		this.setGraphicDown(pausePressed);

		this.setIcon(this.getGraphicUp());
		this.setPressedIcon(this.getGraphicDown());
	}

	/**
	 * Adjust the graphics of the pause button to make it a play button.
	 */
	public void makePauseButton() {
		isPlayButton = false;
		ImageIcon pause = new ImageIcon("images/pauseButton.png");
		ImageIcon pausePressed = new ImageIcon("images/pauseButtonPressed.png");
		this.setGraphicUp(pause);
		this.setGraphicDown(pausePressed);

		this.setIcon(this.getGraphicUp());
		this.setPressedIcon(this.getGraphicDown());
	}

	/**
	 * Set a ProgressBar object for the PlayButton to control.
	 * 
	 * @param p
	 *            The ProgressBar object to control.
	 */
	public void setProgressBar(ProgressBar p) {
		progressBar = p;
	}

}
