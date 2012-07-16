/**
 * The driver class which loads the window, and passes a directory if provided
 * in console.
 * 
 * @author Michael Nipper
 * 
 */
public class Main {

	/**
	 * Loads the window and feeds it a directory if provided.
	 * @param args
	 *            A directory to load
	 */
	public static void main(String[] args) {

		if (args.length > 0) {
			new Window(args[0]);
		} else {
			new Window("");
		}

	}

}
