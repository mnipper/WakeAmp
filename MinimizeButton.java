import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The '-' minimize button.
 * 
 * @author Michael Nipper
 * 
 */
public class MinimizeButton extends Button implements ActionListener {

	private JFrame window;

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
	 * @param frame
	 *            the JFrame object for the button to minimize
	 */
	public MinimizeButton(int x, int y, int width, int height,
			ImageIcon upImage, ImageIcon downImage, JFrame frame) {
		super(x, y, upImage, downImage);
		addActionListener(this);
		this.setWidth(width);
		this.setHeight(height);
		window = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.setState(Frame.ICONIFIED);
	}

}
