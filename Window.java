import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Window container class, which builds and holds all of the graphics.
 * 
 * @author Michael Nipper
 * 
 */
public class Window extends JFrame {

	// Window properties.
	private Container container;
	private int width = 300;
	private int height = 125;
	
	// Control elements.
	private ArrayList<Button> buttonList;
	private MediaController mediaController;
	private ProgressBar progressBar;
	private Playlist playlist;

	// GUI elements
	private JPanel mainPanel;
	private JLabel artistLabel;
	private JLabel songLabel;
	private JLabel positionLabel;
	private JLabel durationLabel;
	private PlayButton playButton;
	private NextButton nextButton;
	private PreviousButton previousButton;
	private ShuffleButton shuffleButton;
	private ProgressButton progressButton;
	private LoadButton loadButton;
	private MinimizeButton minimizeButton;
	private CloseButton closeButton;

	/**
	 * Creates the window and builds all of the necessary graphics.
	 * 
	 * @param dir
	 *            String A directory location to load. If not provided, will not
	 *            load anything.
	 */
	@SuppressWarnings("serial")
	public Window(String dir) {
		
		mediaController = new MediaController();

		if (!dir.equals("")) {
			
			try {
				
				playlist = new Playlist(dir);
				mediaController.loadNewPlaylist(playlist);
				mediaController.getMp3Player().setSong(playlist.getSongByIndex(0));
				
			} catch (NullPointerException e) {
				System.err.print("There was an error loading this directory...");
			}
			
		}

		container = getContentPane();
		setSize(width, height);
		setUndecorated(true);

		mainPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Image img = new ImageIcon("images/background.png").getImage();
				Dimension size = new Dimension(img.getWidth(null),
						img.getHeight(null));
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		};
		
		container.add(mainPanel);
		mainPanel.setBounds(0, 0, width, height);

		buildLabels();
		buildGraphics();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new ComponentMover(JFrame.class, mainPanel);
		mainPanel.setLayout(null);

		setButtons();
		setProgressBar();
		refreshLabels();

		setVisible(true);
	}

	/**
	 * Driver class for creating all of the graphics.
	 */
	private void buildGraphics() {

		buttonList = new ArrayList<Button>();

		nextButton = new NextButton(97, 82, 36, 36, new ImageIcon(
				"images/nextButton.png"), new ImageIcon(
				"images/nextButtonPressed.png"), this);
		
		previousButton = new PreviousButton(10, 82, 36, 36, new ImageIcon(
				"images/previousButton.png"), new ImageIcon(
				"images/previousButtonPressed.png"), this);
		
		playButton = new PlayButton(50, 78, 44, 44, new ImageIcon(
				"images/playButton.png"), new ImageIcon(
				"images/playButtonPressed.png"));
		
		shuffleButton = new ShuffleButton(239, 93, 53, 24, new ImageIcon(
				"images/shuffleButton.png"), new ImageIcon(
				"images/shuffleButtonPressed.png"), this);
		
		loadButton = new LoadButton(176, 93, 53, 24, new ImageIcon(
				"images/loadButton.png"), new ImageIcon(
				"images/loadButtonPressed.png"), this);
		
		closeButton = new CloseButton(281, 2, 14, 11, new ImageIcon(
				"images/closeButton.png"), new ImageIcon(
				"images/closeButtonHover.png"));
		
		minimizeButton = new MinimizeButton(260, 2, 14, 11, new ImageIcon(
				"images/minimizeButton.png"), new ImageIcon(
				"images/minimizeButtonHover.png"), this);
		
		progressButton = new ProgressButton(30, 51, 19, 12, new ImageIcon(
				"images/progressButton.png"), new ImageIcon(
				"images/progressButton.png"));

		/*
		 * POLYMORPHISM!!
		 */
		buttonList.add(nextButton);
		buttonList.add(previousButton);
		buttonList.add(playButton);
		buttonList.add(shuffleButton);
		buttonList.add(loadButton);
		buttonList.add(closeButton);
		buttonList.add(minimizeButton);
		buttonList.add(progressButton);

		progressBar = new ProgressBar(positionLabel, progressButton, this);

		minimizeButton.setRolloverIcon(new ImageIcon("images/minimizeButtonHover.png"));
		closeButton.setRolloverIcon(new ImageIcon("images/closeButtonHover.png"));
	}

	/**
	 * Builds all of the labels necessary for the graphics.
	 */
	private void buildLabels() {
		Font artistFont = new Font("Californian FB", Font.BOLD, 16);
		Font songFont = new Font("Californian FB", Font.PLAIN, 12);
		Font timeFont = new Font("Californian FB", Font.PLAIN, 11);

		artistLabel = new JLabel("");
		songLabel = new JLabel("");
		positionLabel = new JLabel("0:00");
		durationLabel = new JLabel("0:00");

		artistLabel.setBounds(41, 14, 223, 15);
		songLabel.setBounds(41, 30, 223, 13);
		positionLabel.setBounds(7, 52, 29, 11);
		durationLabel.setBounds(270, 52, 29, 11);

		artistLabel.setFont(artistFont);
		songLabel.setFont(songFont);
		positionLabel.setFont(timeFont);
		durationLabel.setFont(timeFont);

		artistLabel.setForeground(Color.WHITE);
		songLabel.setForeground(Color.WHITE);
		positionLabel.setForeground(Color.WHITE);
		durationLabel.setForeground(Color.WHITE);

		mainPanel.add(artistLabel);
		mainPanel.add(songLabel);
		mainPanel.add(positionLabel);
		mainPanel.add(durationLabel);
	}

	/**
	 * Refresh the labels and proress bar when a new song is loaded.
	 */
	public void refreshLabels() {
		if (mediaController.isPlaylistSet()) {
			durationLabel.setText(mediaController.getCurrentSong().getLength());
			artistLabel.setText(mediaController.getCurrentSong()
					.getArtistName());
			songLabel.setText(mediaController.getCurrentSong().getTitle());

		}

		progressBar.refresh(mediaController);

	}

	/**
	 * Give the necessary buttons a progress bar object.
	 */
	private void setProgressBar() {

		shuffleButton.setProgressBar(progressBar);
		playButton.setProgressBar(progressBar);
		previousButton.setProgressBar(progressBar);
		nextButton.setProgressBar(progressBar);

	}

	/**
	 * Set up the buttons with all necessary calls.
	 */
	private void setButtons() {

		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setMediaController(mediaController);
			buttonList.get(i).setIcon(buttonList.get(i).getGraphicUp());
			buttonList.get(i)
					.setPressedIcon(buttonList.get(i).getGraphicDown());
			buttonList.get(i)
					.setBounds(buttonList.get(i).getxPosition(),
							buttonList.get(i).getyPosition(),
							buttonList.get(i).getWidth(),
							buttonList.get(i).getHeight());
			buttonList.get(i).setOpaque(false);
			buttonList.get(i).setContentAreaFilled(false);
			buttonList.get(i).setBorderPainted(false);
			mainPanel.add(buttonList.get(i));
		}
		
		mediaController.loadPlayButton(playButton);
		
	}
}
