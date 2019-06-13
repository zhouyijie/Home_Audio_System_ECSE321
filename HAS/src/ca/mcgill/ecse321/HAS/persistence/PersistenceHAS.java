package ca.mcgill.ecse321.HAS.persistence;


import java.util.Iterator;

import ca.mcgill.ecse321.HAS.model.Album;
import ca.mcgill.ecse321.HAS.model.Artist;
import ca.mcgill.ecse321.HAS.model.HAS;
import ca.mcgill.ecse321.HAS.model.Library;
//import ca.mcgill.ecse321.HAS.model.LibrarySongList;
import ca.mcgill.ecse321.HAS.model.Location;
import ca.mcgill.ecse321.HAS.model.Playlist;
import ca.mcgill.ecse321.HAS.model.Song;


public class PersistenceHAS {
	
	public static void initializeXStream(){
		PersistenceXStream.setFileName("data.xml");
		PersistenceXStream.setAlias("album", Album.class);
		PersistenceXStream.setAlias("artist", Artist.class);
		PersistenceXStream.setAlias("HAS", HAS.class);
		PersistenceXStream.setAlias("library", Library.class);
		PersistenceXStream.setAlias("location", Location.class);
		PersistenceXStream.setAlias("playlist", Playlist.class);
		PersistenceXStream.setAlias("song", Song.class);
		
	}
	public static void loadLibraryModel(){
		Library rm = Library.getInstance();
		PersistenceHAS.initializeXStream();
		Library rm2 =(Library)PersistenceXStream.loadFromXMLwithXStream();
		if(rm2 != null){
			Iterator<Album> pIt = rm2.getAlbums().iterator();
			while(pIt.hasNext()){
				rm.addAlbum(pIt.next());
			}
			Iterator<Song> eIt =rm2.getSongs().iterator();
			while(eIt.hasNext()){
				rm.addSong(eIt.next());
			}
			Iterator<Artist> rIt =rm2.getArtists().iterator();
			while(rIt.hasNext()){
				rm.addArtist(rIt.next());
			}
			Iterator<Location> lIt =rm2.getLocations().iterator();
			while(lIt.hasNext()){
				rm.addLocation(lIt.next());
			}
			Iterator<Playlist> fIt =rm2.getPlaylists().iterator();
			while(fIt.hasNext()){
				rm.addPlaylist(fIt.next());
			}
			
		}
	}

}