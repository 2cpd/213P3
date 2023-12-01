package Model;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * @author Chris Li
 * @author Tony Lu
 */
public class Home implements Serializable{
	private ArrayList<Album> listOfAlbums = new ArrayList<Album>();
	private Photo copiedPhoto = null;
	
	public Home (String name) {
		username = name;
		albums = new ArrayList<Album>();
	}
	
}