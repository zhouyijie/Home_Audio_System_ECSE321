/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.HAS.model;
import java.util.*;

// line 47 "../../../../../HAS.ump"
// line 86 "../../../../../HAS.ump"
public class Playlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Playlist Attributes
  private String playlistName;
  private List<Song> songsArray;

  //Playlist Associations
  private List<Song> songs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Playlist(String aPlaylistName, ArrayList<Song> songsList)
  {
    playlistName = aPlaylistName;
    songsArray = new ArrayList<Song>();
    songsArray = songsList;
  }
  
  
  

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPlaylistName(String aPlaylistName)
  {
    boolean wasSet = false;
    playlistName = aPlaylistName;
    wasSet = true;
    return wasSet;
  }

  public boolean addSongsArray(Song aSongsArray)
  {
    boolean wasAdded = false;
    wasAdded = songsArray.add(aSongsArray);
    return wasAdded;
  }

  public boolean removeSongsArray(Song aSongsArray)
  {
    boolean wasRemoved = false;
    wasRemoved = songsArray.remove(aSongsArray);
    return wasRemoved;
  }

  public String getPlaylistName()
  {
    return playlistName;
  }

  public Song getSongsArray(int index)
  {
    Song aSongsArray = songsArray.get(index);
    return aSongsArray;
  }

  public Song[] getSongsArray()
  {
    Song[] newSongsArray = songsArray.toArray(new Song[songsArray.size()]);
    return newSongsArray;
  }

  public int numberOfSongsArray()
  {
    int number = songsArray.size();
    return number;
  }

  public boolean hasSongsArray()
  {
    boolean has = songsArray.size() > 0;
    return has;
  }

  public int indexOfSongsArray(Song aSongsArray)
  {
    int index = songsArray.indexOf(aSongsArray);
    return index;
  }

  public Song getSong(int index)
  {
    Song aSong = songs.get(index);
    return aSong;
  }

  public List<Song> getSongs()
  {
    List<Song> newSongs = Collections.unmodifiableList(songs);
    return newSongs;
  }

  public int numberOfSongs()
  {
    int number = songs.size();
    return number;
  }

  public boolean hasSongs()
  {
    boolean has = songs.size() > 0;
    return has;
  }

  public int indexOfSong(Song aSong)
  {
    int index = songs.indexOf(aSong);
    return index;
  }

  public static int minimumNumberOfSongs()
  {
    return 0;
  }

  public boolean addSong(Song aSong)
  {
    boolean wasAdded = false;
    if (songs.contains(aSong)) { return false; }
    songs.add(aSong);
    if (aSong.indexOfPlaylist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSong.addPlaylist(this);
      if (!wasAdded)
      {
        songs.remove(aSong);
      }
    }
    return wasAdded;
  }

  public boolean removeSong(Song aSong)
  {
    boolean wasRemoved = false;
    if (!songs.contains(aSong))
    {
      return wasRemoved;
    }

    int oldIndex = songs.indexOf(aSong);
    songs.remove(oldIndex);
    if (aSong.indexOfPlaylist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSong.removePlaylist(this);
      if (!wasRemoved)
      {
        songs.add(oldIndex,aSong);
      }
    }
    return wasRemoved;
  }

  public boolean addSongAt(Song aSong, int index)
  {  
    boolean wasAdded = false;
    if(addSong(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSongAt(Song aSong, int index)
  {
    boolean wasAdded = false;
    if(songs.contains(aSong))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSongs()) { index = numberOfSongs() - 1; }
      songs.remove(aSong);
      songs.add(index, aSong);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSongAt(aSong, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Song> copyOfSongs = new ArrayList<Song>(songs);
    songs.clear();
    for(Song aSong : copyOfSongs)
    {
      aSong.removePlaylist(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "playlistName" + ":" + getPlaylistName()+ "]"
     + outputString;
  }
}