/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.HAS.model;
import java.util.*;

// line 30 "../../../../../HAS.ump"
// line 75 "../../../../../HAS.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String songTitle;
  private String songDuration;
  private String artist;

  //Song Associations
  private List<Album> albums;
  private List<Playlist> playlists;
  private List<Artist> artists;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aSongTitle, String aSongDuration, String aArtist)
  {
    songTitle = aSongTitle;
    songDuration = aSongDuration;
    artist = aArtist;
    albums = new ArrayList<Album>();
    playlists = new ArrayList<Playlist>();
    artists = new ArrayList<Artist>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSongTitle(String aSongTitle)
  {
    boolean wasSet = false;
    songTitle = aSongTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setSongDuration(String aSongDuration)
  {
    boolean wasSet = false;
    songDuration = aSongDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setArtist(String aArtist)
  {
    boolean wasSet = false;
    artist = aArtist;
    wasSet = true;
    return wasSet;
  }

  public String getSongTitle()
  {
    return songTitle;
  }

  public String getSongDuration()
  {
    return songDuration;
  }

  public String getArtist()
  {
    return artist;
  }

  public Album getAlbum(int index)
  {
    Album aAlbum = albums.get(index);
    return aAlbum;
  }

  public List<Album> getAlbums()
  {
    List<Album> newAlbums = Collections.unmodifiableList(albums);
    return newAlbums;
  }

  public int numberOfAlbums()
  {
    int number = albums.size();
    return number;
  }

  public boolean hasAlbums()
  {
    boolean has = albums.size() > 0;
    return has;
  }

  public int indexOfAlbum(Album aAlbum)
  {
    int index = albums.indexOf(aAlbum);
    return index;
  }

  public Playlist getPlaylist(int index)
  {
    Playlist aPlaylist = playlists.get(index);
    return aPlaylist;
  }

  public List<Playlist> getPlaylists()
  {
    List<Playlist> newPlaylists = Collections.unmodifiableList(playlists);
    return newPlaylists;
  }

  public int numberOfPlaylists()
  {
    int number = playlists.size();
    return number;
  }

  public boolean hasPlaylists()
  {
    boolean has = playlists.size() > 0;
    return has;
  }

  public int indexOfPlaylist(Playlist aPlaylist)
  {
    int index = playlists.indexOf(aPlaylist);
    return index;
  }

  public Artist getArtist(int index)
  {
    Artist aArtist = artists.get(index);
    return aArtist;
  }

  public List<Artist> getArtists()
  {
    List<Artist> newArtists = Collections.unmodifiableList(artists);
    return newArtists;
  }

  public int numberOfArtists()
  {
    int number = artists.size();
    return number;
  }

  public boolean hasArtists()
  {
    boolean has = artists.size() > 0;
    return has;
  }

  public int indexOfArtist(Artist aArtist)
  {
    int index = artists.indexOf(aArtist);
    return index;
  }

  public static int minimumNumberOfAlbums()
  {
    return 0;
  }

  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    albums.add(aAlbum);
    if (aAlbum.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAlbum.addSong(this);
      if (!wasAdded)
      {
        albums.remove(aAlbum);
      }
    }
    return wasAdded;
  }

  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    if (!albums.contains(aAlbum))
    {
      return wasRemoved;
    }

    int oldIndex = albums.indexOf(aAlbum);
    albums.remove(oldIndex);
    if (aAlbum.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAlbum.removeSong(this);
      if (!wasRemoved)
      {
        albums.add(oldIndex,aAlbum);
      }
    }
    return wasRemoved;
  }

  public boolean addAlbumAt(Album aAlbum, int index)
  {  
    boolean wasAdded = false;
    if(addAlbum(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAlbumAt(Album aAlbum, int index)
  {
    boolean wasAdded = false;
    if(albums.contains(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAlbumAt(aAlbum, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPlaylists()
  {
    return 0;
  }

  public boolean addPlaylist(Playlist aPlaylist)
  {
    boolean wasAdded = false;
    if (playlists.contains(aPlaylist)) { return false; }
    playlists.add(aPlaylist);
    if (aPlaylist.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlaylist.addSong(this);
      if (!wasAdded)
      {
        playlists.remove(aPlaylist);
      }
    }
    return wasAdded;
  }

  public boolean removePlaylist(Playlist aPlaylist)
  {
    boolean wasRemoved = false;
    if (!playlists.contains(aPlaylist))
    {
      return wasRemoved;
    }

    int oldIndex = playlists.indexOf(aPlaylist);
    playlists.remove(oldIndex);
    if (aPlaylist.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlaylist.removeSong(this);
      if (!wasRemoved)
      {
        playlists.add(oldIndex,aPlaylist);
      }
    }
    return wasRemoved;
  }

  public boolean addPlaylistAt(Playlist aPlaylist, int index)
  {  
    boolean wasAdded = false;
    if(addPlaylist(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylists()) { index = numberOfPlaylists() - 1; }
      playlists.remove(aPlaylist);
      playlists.add(index, aPlaylist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlaylistAt(Playlist aPlaylist, int index)
  {
    boolean wasAdded = false;
    if(playlists.contains(aPlaylist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlaylists()) { index = numberOfPlaylists() - 1; }
      playlists.remove(aPlaylist);
      playlists.add(index, aPlaylist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlaylistAt(aPlaylist, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfArtists()
  {
    return 0;
  }

  public boolean addArtist(Artist aArtist)
  {
    boolean wasAdded = false;
    if (artists.contains(aArtist)) { return false; }
    artists.add(aArtist);
    if (aArtist.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aArtist.addSong(this);
      if (!wasAdded)
      {
        artists.remove(aArtist);
      }
    }
    return wasAdded;
  }

  public boolean removeArtist(Artist aArtist)
  {
    boolean wasRemoved = false;
    if (!artists.contains(aArtist))
    {
      return wasRemoved;
    }

    int oldIndex = artists.indexOf(aArtist);
    artists.remove(oldIndex);
    if (aArtist.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aArtist.removeSong(this);
      if (!wasRemoved)
      {
        artists.add(oldIndex,aArtist);
      }
    }
    return wasRemoved;
  }

  public boolean addArtistAt(Artist aArtist, int index)
  {  
    boolean wasAdded = false;
    if(addArtist(aArtist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtists()) { index = numberOfArtists() - 1; }
      artists.remove(aArtist);
      artists.add(index, aArtist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArtistAt(Artist aArtist, int index)
  {
    boolean wasAdded = false;
    if(artists.contains(aArtist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtists()) { index = numberOfArtists() - 1; }
      artists.remove(aArtist);
      artists.add(index, aArtist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArtistAt(aArtist, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Album> copyOfAlbums = new ArrayList<Album>(albums);
    albums.clear();
    for(Album aAlbum : copyOfAlbums)
    {
      aAlbum.removeSong(this);
    }
    ArrayList<Playlist> copyOfPlaylists = new ArrayList<Playlist>(playlists);
    playlists.clear();
    for(Playlist aPlaylist : copyOfPlaylists)
    {
      aPlaylist.removeSong(this);
    }
    ArrayList<Artist> copyOfArtists = new ArrayList<Artist>(artists);
    artists.clear();
    for(Artist aArtist : copyOfArtists)
    {
      aArtist.removeSong(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "songTitle" + ":" + getSongTitle()+ "," +
            "songDuration" + ":" + getSongDuration()+ "," +
            "artist" + ":" + getArtist()+ "]"
     + outputString;
  }
}