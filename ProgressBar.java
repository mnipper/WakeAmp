import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * The progress bar, which controls the movement of a ProgressButton object.
 * 
 * @author Michael Nipper
 * 
 */
public class ProgressBar implements ActionListener {

	private JLabel progressLabel;
	private int duration = 0;
	private int position = 0;
	private ProgressButton progressButton;
	private MediaController mediaController;
	private Timer timer;
	private Window window;

	/**
	 * 
	 * @param pl
	 *            The JLabel for the duration label.
	 * @param pg
	 *            The ProgressButton to attach to the ProgressBar.
	 * @param w
	 *            The Window object to attach the ProgressBar.
	 */
	public ProgressBar(JLabel pl, ProgressButton pg, Window w) {
		progressLabel = pl;
		progressButton = pg;
		timer = new Timer(1000, this);
		window = w;
	}

	/**
	 * Start the graphics for the ProgressBar.
	 */
	public void start() {
		if (mediaController.isPlaylistSet()) {
			timer.start();
			duration = mediaController.getCurrentSong().getSongLengthSeconds();
		}
	}

	/**
	 * Stop the graphics for the ProgressBar.
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Refresh the ProgressBar graphics and labels.
	 * 
	 * @param mc
	 *            The MediaController that is controlling the ProgressBar.
	 */
	public void refresh(MediaController mc) {
		stop();
		position = 0;
		mediaController = mc;
		progressLabel.setText("0:00");
		progressButton.setLocation(30, 51);
		progressButton.setxPosition(30);
		progressButton.setyPosition(51);
		progressButton.refreshPixelRounder();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (position < duration) {

			position++;
			String secondLabel = "" + position % 60;

			if (position % 60 < 10) {
				secondLabel = "0" + position % 60;
			}

			progressLabel.setText(position / 60 + ":" + secondLabel);
			progressButton.moveButton(((1.0 / duration) * 215));
		} else {
			mediaController.nextTrack();
			refresh(mediaController);
			window.refreshLabels();
			start();
		}
	}

}
