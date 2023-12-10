package com.example.photos.shared;

import android.util.Pair;
import androidx.lifecycle.ViewModel;
import com.example.photos.model.Photo;
import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    /*
    arraylist containing all tags
    -used for autocomplete (in Search)
    -stored in the form of pair [tagName, tagType], where tagType can be either Location/Person
    -when a new tag is added, attempt to save the new tag in the arraylist
            -look for repeats: don't add if this tag already exists (use equalsIgnoreCase())

    arraylist containing all photos
    -used for Results
    -includes their URI, location, people, and nameOfContainingAlbum
    -when a new photo is added, store its URI and nameOfContainingAlbum in the arrayList (location and people are empty upon creation)
        -location/people data are filled in using the addTag functionality (in Slideshow)
    -when displaying photos in an album (in Photos), loop through all photos in the arraylist & find photos with matching String nameOfContainingAlbum (use equalsIgnoreCase())
        -create a temporary arraylist to store matching photos
        -show in listView once search is complete
    -when displaying search results (in Search), loop through all photos w/ matching tags
        -create a temporary arraylist to store matching photos
        -show in listView once search is complete
    -(photo repeats are permitted if in different albums)

    arraylist of albums
    -used for Home
    -includes names of albums

    temp arraylist for search

     */

    private ArrayList<Pair<String,String>> allTagsList = new ArrayList<>(); //stores all tags, for autocomplete
    private ArrayList<Photo> allPhotosList = new ArrayList<>();
    private ArrayList<String> allAlbumsList = new ArrayList<>();
    private ArrayList<Photo> searchResults = new ArrayList<>();

    // Methods to load data from text files
    public void loadData() {
        // TODO:Load data from text files and populate the ArrayLists
        // tags = ...
        // allPhotos = ...
        // allAlbums = ...
    }

    public void saveData() {
        // TODO:Save data from arraylists to text files
    }

    public ArrayList<Pair<String,String>> getAllTagsList() {
        return allTagsList;
    }

    public boolean addTagToAllTagsList(String tagName, String tagType) {
        for (Pair<String,String> i:allTagsList) {
            if (i.first.equals(tagName) && i.second.equals(tagType)) {
                return false;
            }
        }
        //tag does not exist, add tag to list
        allTagsList.add(new Pair<>(tagName,tagType));
        return true;
    }

    public ArrayList<Photo> getAllPhotosList() {
        return allPhotosList;
    }

    public boolean addPhotoToAllPhotosList(String newURI, String newAlbumName) {
        for (Photo i:allPhotosList) {
            if (i.getURI().equals(newURI) && i.getNameOfContainingAlbum().equals(newAlbumName)) { //duplicate photo exists
                return false;
            }
        }
        //photo does not exist, add photo to list
            //NOTE: photo with duplicate URI but in different albums are allowed
        allPhotosList.add(new Photo(newURI,newAlbumName));
        return true;
    }

    public ArrayList<String> getAllAlbumsList() {
        return allAlbumsList;
    }

    public boolean addAlbumToAllAlbumsList(String newAlbumName) {
        for (String i:allAlbumsList) {
            if (i.equals(newAlbumName)) { //duplicate album exists
                return false;
            }
        }
        //album does not exist, add album to list
        allAlbumsList.add(newAlbumName);
        return true;
    }

    public void setSearchResults(ArrayList<Photo> photos) {
        this.searchResults = photos;
    }

    public ArrayList<Photo> getSearchResults() {
        return searchResults;
    }
}
