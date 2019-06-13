/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.HAS.model;

// line 52 "../../../../../HAS.ump"
// line 91 "../../../../../HAS.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private String locationName;
  private int volume;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(String aLocationName, int aVolume)
  {
    locationName = aLocationName;
    volume = aVolume;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocationName(String aLocationName)
  {
    boolean wasSet = false;
    locationName = aLocationName;
    wasSet = true;
    return wasSet;
  }

  public boolean setVolume(int aVolume)
  {
    boolean wasSet = false;
    volume = aVolume;
    wasSet = true;
    return wasSet;
  }

  public String getLocationName()
  {
    return locationName;
  }

  public int getVolume()
  {
    return volume;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "locationName" + ":" + getLocationName()+ "," +
            "volume" + ":" + getVolume()+ "]"
     + outputString;
  }
}