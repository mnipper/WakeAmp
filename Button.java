import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Controls the functionality of all buttons.
 * 
 * @author Michael Nipper
 * 
 */

public class Button extends JButton implements ActionListener {

	private JButton button;
	private int xPosition, yPosition, width, height = 0;
	private ImageIcon upImage, downImage;
	protected MediaController mediaController;

	/**
	 * Default constructor.
	 */
	public Button() {
		button = new JButton();
	}

	/**
	 * METHOD OVERLOAD
	 * 
	 * @param x
	 *            int The x position of the button.
	 * @param y
	 *            int The y position of the button.
	 * @param upImage
	 *            ImageIcon The image to use when the button is not being
	 *            pressed.
	 * @param downImage
	 *            ImageIcon The image to use when the button is being pressed.
	 */
	public Button(int x, int y, ImageIcon upImage, ImageIcon downImage) {
		button = new JButton();
		setGraphicUp(upImage);
		setGraphicDown(downImage);
		xPosition = x;
		yPosition = y;
	}

	/**
	 * Set the image to use for a button when it is not being pressed.
	 * 
	 * @param image
	 *            imageIcon The image to use.
	 */
	public void setGraphicUp(ImageIcon image) {
		upImage = image;
	}

	/**
	 * Set the image to use for a button when it is being pressed.
	 * 
	 * @param image
	 *            imageIcon The image to use.
	 */
	public void setGraphicDown(ImageIcon image) {
		downImage = image;
	}

	/**
	 * Get the image to use when a button is not being pressed.
	 * 
	 * @return ImageIcon The image for a non-pressed button.
	 */
	public ImageIcon getGraphicUp() {
		return upImage;
	}

	/**
	 * Get the image to use when a button is being pressed.
	 * 
	 * @return ImageIcon The image for a pressed button.
	 */
	public ImageIcon getGraphicDown() {
		return downImage;
	}

	/**
	 * Set the position of a button.
	 * 
	 * @param x
	 *            int The x coordinate.
	 * @param y
	 *            int The y coordinate.
	 */
	public void setPosition(int x, int y) {
		button.setLocation(x, y);
	}

	/**
	 * Warn the developer if a button does not have an associated action set.
	 * 
	 * @Override
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println("No action has been set for this button!");
	}

	/**
	 * Gets the x coordinate of the button.
	 * 
	 * @return int The x coordinate of a button.
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * Sets the x coordinate of a button.
	 * 
	 * @param xPosition
	 *            int Sets the x coordinate of a button.
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * Gets the y coordinate of the button.
	 * 
	 * @return int The y coordinate of a button.
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * Sets the y coordinate of a button.
	 * 
	 * @param xPosition
	 *            int Sets the y coordinate of a button.
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * Get the width of the button.
	 * 
	 * @return int Gets the width of the button.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the button.
	 * 
	 * @param width
	 *            The desired width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get the height of the button.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the button.
	 * 
	 * @param height
	 *            int The desired height of the button.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Set a media controller object for the button to use.
	 * 
	 * @param m
	 *            MediaController A MediaController object for the buttons to
	 *            control.
	 */
	public void setMediaController(MediaController m) {
		mediaController = m;
	}

	/**
	 * Get the MediaController object which controls this button.
	 * 
	 * @return A MediaController object.
	 */
	public MediaController getMediaController() {
		return mediaController;
	}

}
