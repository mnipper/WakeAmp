import java.util.ArrayList;

import junit.framework.TestCase;


public class WakeAmpTest extends TestCase {

	private String file = "/Users/michael/Dropbox/Mp3s/Wallpaper - Best Song Ever.mp3";
	private String dir = "/Users/michael/Dropbox/Mp3s";
	
    public WakeAmpTest(String name) {
        super(name);
    }
    
    /*
     * Test that the file path is properly set for a song object.
     */
    public void testFilepath() {
        Song testSong = new Song(file);
        assertEquals(file, testSong.getFilePath());
    }
    
    /*
     * Test that when a song is skipped on a playlist, that the index is properly adjusted.
     */
    public void testSkipSong() {   
        Playlist testPlaylist = new Playlist(dir);
        testPlaylist.skipSong();
        assertEquals(1,testPlaylist.getIndex());
    }
    
    /*
     * Test that when previous is called when the playlist is on the first song, that it goes to
     * the end of the playlist.
     */
    public void testPreviousSong() {   
        Playlist testPlaylist = new Playlist(dir);
        testPlaylist.previousSong();
        assertEquals(testPlaylist.getPlaylist().size() - 1,testPlaylist.getIndex());
    }
 
    /*
     * Test the pause buttons functionality when the Mp3Player is both playing and paused.
     */
    public void testPause() {
    	Playlist testPlaylist = new Playlist(dir);
    	MediaController testMediaController = new MediaController(testPlaylist);
    	Mp3Player testMp3Player = testMediaController.getMp3Player();
    	testMp3Player.pause();
    	assertTrue(testMp3Player.isPaused());
    }
    
    /*
     * Check that the MediaController play button properly plays a song when it is loaded.
     */
    public void testMediaControllerPlay() {
    	Playlist testPlaylist = new Playlist(dir);
    	MediaController testMediaController = new MediaController(testPlaylist);
    	Mp3Player testMp3Player = testMediaController.getMp3Player();
    	testMediaController.play();
    	assertFalse(testMp3Player.isPaused());
    }
    
    /*
     * Check that the Playlist play button properly plays a song when it is loaded.
     */
    public void testPlaylistPlay() {
    	Playlist testPlaylist = new Playlist(dir);
    	MediaController testMediaController = new MediaController(testPlaylist);
    	assertFalse(testMediaController.isPaused());
    }
    
    /*
     * Test that the shuffle status is properly set when shuffle is called on a media controller.
     */
    public void testMediaControllerShuffle() {
    	Playlist testPlaylist = new Playlist(dir);
    	MediaController testMediaController = new MediaController(testPlaylist);
    	testMediaController.shuffle();
    	assertTrue(testMediaController.shuffleStatus());
    }
    
    /*
     * Test that the getSongByIndex properly works for all songs in the playlist.
     */
    public void testPlaylistGetSongByIndex() {
    	Playlist testPlaylist = new Playlist(dir);
    	for (int i = 0; i < testPlaylist.getPlaylist().size(); i++)
    		assertEquals(testPlaylist.getSongByIndex(i), testPlaylist.getPlaylist().get(i));
    }
    
    /*
     * Test that skipping a song on a playlist object returns the proper Song object.
     */
    public void testPlaylistSkipSong() {
    	
    	Playlist testPlaylist = new Playlist(dir);
    	
    	testPlaylist.skipSong();
    	assertEquals(testPlaylist.getSong(), testPlaylist.getSongByIndex(1));
    	
    }
    
    /*
     * Test that a previous song on a playlist object returns the proper Song object.
     * Also tests the wrap-around funcitonality of previous song.
     */
    public void testPlaylistPreviousSong() {
    	
    	Playlist testPlaylist = new Playlist(dir);
    	
    	testPlaylist.previousSong();
    	assertEquals(testPlaylist.getSong(), testPlaylist.getSongByIndex(testPlaylist.getPlaylist().size() - 1));
    	
    }
    
    /*
     * Tests that the unshuffle playlist function properly works.
     */
    public void testUnshufflePlaylist() {
    	
    	Playlist testPlaylist = new Playlist(dir);
    	
    	// Store the unshuffled list for comparison.
    	ArrayList<Song> unshuffled = new ArrayList<Song>();
    	
    	// Copy the current list into the unshuffled list.
    	for (int i = 0; i < testPlaylist.getPlaylist().size(); i++){
    		unshuffled.add(testPlaylist.getSongByIndex(i));
    	}
    	
    	// Shuffle and then unshuffle the list.
    	testPlaylist.shuffleList();
    	testPlaylist.unshuffleList();
    	
    	assertEquals(unshuffled, testPlaylist.getPlaylist());
    	
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(WakeAmpTest.class);
    }
    
}
