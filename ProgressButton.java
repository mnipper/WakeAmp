import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * Shows the progress of the song on the progress bar. Controlled by a
 * ProgressBar object.
 * 
 * @author Michael Nipper
 * 
 */
public class ProgressButton extends Button implements ActionListener {

	private float pixelRounder;

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
	public ProgressButton(int x, int y, int width, int height,
			ImageIcon upImage, ImageIcon downImage) {
		super(x, y, upImage, downImage);
		addActionListener(this);
		this.setWidth(width);
		this.setHeight(height);
		pixelRounder = x;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Increment the progress button to progress through the song.
	 * 
	 * @param pixels
	 *            The number of pixels to progress.
	 */
	public void moveButton(double pixels) {
		pixelRounder += pixels;
		this.setxPosition(Math.round(pixelRounder));
		this.setLocation(this.getxPosition(), this.getyPosition());
	}

	/**
	 * Set the ProgressButton object back to its initial position at the
	 * beginning of the ProgressBar.
	 */
	public void refreshPixelRounder() {
		pixelRounder = 30;
	}

}
