import java.io.File;
import java.util.ArrayList;

/**
 * Loads all the files into an arrayList<String>, which contains all of the file
 * locations in the directory ending in mp3.
 * 
 * @author Michael Nipper
 * 
 */
public class FileLoader {

	private ArrayList<String> fileList = new ArrayList<String>();

	/**
	 * Loads all of filepaths in a given directory as an ArrayList of strings.
	 * @param dir String		A directory to load.
	 */
	public FileLoader(String dir) {
		String file;
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				file = listOfFiles[i].getName();
				if (file.endsWith(".mp3")) {
					fileList.add(dir + "\\" + file);
				}
			}
		}
	}

	/**
	 * Get the file list.
	 * @return ArrayList<String>		The arraylist of filepaths.
	 */
	public ArrayList<String> getFileList() {
		return fileList;
	}
}
