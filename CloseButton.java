import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * The 'x' button which closes the program.
 * 
 * @author Michael Nipper
 * 
 */
public class CloseButton extends Button implements ActionListener {

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
	public CloseButton(int x, int y, int width, int height, ImageIcon upImage,
			ImageIcon downImage) {
		super(x, y, upImage, downImage);
		addActionListener(this);
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
