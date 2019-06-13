package ca.mcgill.ecse321.HAS.controller;

//import java.sql.Date;

import ca.mcgill.ecse321.HAS.model.Album;
import ca.mcgill.ecse321.HAS.model.Artist;
import ca.mcgill.ecse321.HAS.model.Library;
import ca.mcgill.ecse321.HAS.model.Location;
import ca.mcgill.ecse321.HAS.model.Playlist;
//import ca.mcgill.ecse321.HAS.model.LibrarySongList;
import ca.mcgill.ecse321.HAS.model.Song;
import ca.mcgill.ecse321.HAS.persistence.PersistenceHAS;
import ca.mcgill.ecse321.HAS.persistence.PersistenceXStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class HASController {

public HASController(){
		
	}
	
	// List<String> textAreaAlbum = Arrays.asList("String 1", "String 2", "String n");

	//Validate Date
	public static boolean isValidDate(String releaseDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(releaseDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	
	
	//Validate time
	
	
	
	public void addSong(String songTitle, String songDuration, String artist) throws InvalidInputException{
		String error =""; //no error
		if (songTitle == null || songTitle.trim().length() == 0)
			error = error +"Song name cannot be empty! ";
		if (songDuration == null || songDuration.trim().length() == 0)
			error = error +"Song duration cannot be empty! ";
		//System.out.println(artist);
		//if (artist.trim() == "")
		//if (artist == null || artist.trim().length() == 0)
		//	error = error +"Artist has to be selected! ";
		//ADD isValidTime
		
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		Song S = new Song(songTitle, songDuration, artist);
		Library list = Library.getInstance();
		list.addSong(S);
		PersistenceXStream.saveToXMLwithXStream(list);

	}


	public void createAlbum(String albumGenre, String releaseDate, String nameAlbum, ArrayList<Song> songs, String artist) throws InvalidInputException{
		
		String error =""; //no error
		if (nameAlbum == null || nameAlbum.trim().length() == 0)
			error = error +"Album name cannot be empty! "; // Album name cannot be empty! 
		
		if (releaseDate == null || releaseDate.trim().length() == 0)
			error = error +"Release date cannot be empty! ";
		
		if (!isValidDate(releaseDate))
			error = error +"Release date must satisfy format! (yyyy-MM-dd) ";
		
		if (albumGenre == null || albumGenre.trim().length() == 0)
			error = error + "Album genre cannot be empty! ";
		
		if (songs.size() == 0)
			error = error + "At least one song should be selected! ";
		
		if (artist.trim() == "")
			error = error +"Artist has to be selected! ";
		
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		Album A = new Album(albumGenre, releaseDate, nameAlbum, songs);
		Library list = Library.getInstance();
		list.addAlbum(A);
		PersistenceXStream.saveToXMLwithXStream(list);
	}
	
public void createPlaylist(String PlaylistName, ArrayList<Song> songs) throws InvalidInputException{
		
		String error =""; //no error
		if (PlaylistName == null || PlaylistName.trim().length() == 0)
			error = error +"Playlist name cannot be empty! "; // Playlist name cannot be empty! 
		
		if (songs.size() == 0)
			error = error + "At least one song should be selected! ";
		
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		Playlist A = new Playlist(PlaylistName, songs);
		Library list = Library.getInstance();
		list.addPlaylist(A);
		PersistenceXStream.saveToXMLwithXStream(list);
	}
	
	
	
	public void addArtist(String artistName) throws InvalidInputException{
		String error = "";//no error
		if (artistName == null || artistName.trim().length() == 0)
			error = error +"Artist name cannot be empty! ";
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		Artist T = new Artist(artistName);
		Library list = Library.getInstance();
		list.addArtist(T);
		PersistenceXStream.saveToXMLwithXStream(list);
	}
	
	public void addLocation(String locationName) throws InvalidInputException{
		String error = "";//no error
		if (locationName == null || locationName.trim().length() == 0)
			error = error +"Location name cannot be empty! "; 
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		
		System.out.println("SAVING?");
		Integer volume = 5;
		Location L = new Location(locationName, volume);
		Library list = Library.getInstance();
		list.addLocation(L);
		PersistenceXStream.saveToXMLwithXStream(list);
	}
	public void updateVolume(String location, String volume){
		//Library rm = Library.getInstance();
		PersistenceHAS.initializeXStream();
		Library rm2 =(Library)PersistenceXStream.loadFromXMLwithXStream();
		Iterator<Location> lIt = rm2.getLocations().iterator();
		while(lIt.hasNext()){
			Location l = lIt.next();
			if (l.getLocationName() == location){
				rm2.removeLocation(l);
				PersistenceXStream.saveToXMLwithXStream(rm2);
				l.setVolume(Integer.valueOf(volume));
				rm2.addLocation(l);
				PersistenceXStream.saveToXMLwithXStream(rm2);
			}
		}
	}
	public Integer getVolume(String location){
		PersistenceHAS.initializeXStream();
		Library rm2 =(Library)PersistenceXStream.loadFromXMLwithXStream();
		Iterator<Location> lIt = rm2.getLocations().iterator();
		while(lIt.hasNext()){
			Location l = lIt.next();
			if (l.getLocationName() == location){
				return l.getVolume();
			}
		}
		return null;
	}
}

