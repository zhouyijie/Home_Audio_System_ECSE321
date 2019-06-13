package ca.mcgill.ecse321.has;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import ca.mcgill.ecse321.HAS.controller.HASController;
import ca.mcgill.ecse321.HAS.controller.InvalidInputException;
import ca.mcgill.ecse321.HAS.model.Artist;
import ca.mcgill.ecse321.HAS.model.Library;
import ca.mcgill.ecse321.HAS.model.Location;
import ca.mcgill.ecse321.HAS.model.Playlist;
import ca.mcgill.ecse321.HAS.model.Song;
import ca.mcgill.ecse321.HAS.persistence.PersistenceXStream;

public class MainActivity extends AppCompatActivity {


    private void refreshData() {

        TextView tv = (TextView) findViewById(R.id.newalbum_name);
        tv.setText("");
        TextView tv2 = (TextView) findViewById(R.id.newalbum_genre);
        tv.setText("");
        TextView tv3 = (TextView) findViewById(R.id.newalbum_date);
        tv.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //PersistenceXStream.loadFromXMLwithXStream();

        /*Library list;

        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.ArtistSpinner);

        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAlbum(View V) {
        TextView tv = (TextView) findViewById(R.id.newalbum_name);
        TextView tv2 = (TextView) findViewById(R.id.newalbum_genre);
        TextView tv3 = (TextView) findViewById(R.id.newalbum_date);
        // TextView tv4 = (TextView) findViewById(R.id.artistList);


        /*HASController list = new HASController();
        try {
            list.createAlbum(tv.getText().toString(), tv2.getText().toString(), tv3.getText().toString(),tv4.getText().toString());
        } catch(InvalidInputException e){
            TextView error = (TextView) findViewById(R.id.error);
            error.setText("All Fields must be filled!");

        }*/
    }

    public void addSong(String songTitle, String songDuration, String artist) throws InvalidInputException{
        String error =""; //no error
        if (songTitle == null || songTitle.trim().length() == 0)
            error = error +"Song name cannot be empty! ";
        if (songDuration == null || songDuration.trim().length() == 0)
            error = error +"Song duration cannot be empty! ";
        TextView songText = (TextView) findViewById(R.id.songName);
        TextView songTime = (TextView) findViewById(R.id.songDuration);
        //System.out.println(artist);
        //if (artist.trim() == "")
        //if (artist == null || artist.trim().length() == 0)
        //	error = error +"Artist has to be selected! ";
        //ADD isValidTime

        if (error.length()>0)
            throw new InvalidInputException(error);

        Song S = new Song(songTitle, songDuration, artist);
        Library list = Library.getInstance();
        S.setSongTitle(songText.getText().toString());
        S.setSongDuration(songTime.getText().toString());
        list.addSong(S);
        PersistenceXStream.saveToXMLwithXStream(list);

    }

    public void addArtist(String artistName)throws InvalidInputException {
        String error = ""; //no error
        if (artistName == null || artistName.trim().length() == 0)
            error = error + "Artist name cannot be empty! ";
        TextView artist = (TextView) findViewById(R.id.artistName);
        Artist A = new Artist(artistName);
        Library list = Library.getInstance();
        A.setArtistName(artist.getText().toString());
        list.addArtist(A);
        PersistenceXStream.saveToXMLwithXStream(list);
    }
    public void addLocation(String locationName, int volume)throws InvalidInputException {
        String error = ""; //no error
        if (locationName == null || locationName.trim().length() == 0)
            error = error + "Artist name cannot be empty! ";
        TextView artist = (TextView) findViewById(R.id.locationName);
        TextView vol = (TextView) findViewById(R.id.volumeField);
        Location L = new Location(locationName, volume);
        Library list = Library.getInstance();
        L.setLocationName(artist.getText().toString());
        L.setVolume(Integer.parseInt(vol.getText().toString()));
        list.addLocation(L);
        PersistenceXStream.saveToXMLwithXStream(list);
    }

    public void addPlaylist(String playlistName)throws InvalidInputException {
        String error = ""; //no error
        if (playlistName == null || playlistName.trim().length() == 0)
            error = error + "Artist name cannot be empty! ";
        TextView playlist = (TextView) findViewById(R.id.playlistName);
        Playlist P = new Playlist(playlistName);
        Library list = Library.getInstance();
        P.setPlaylistName(playlist.getText().toString());
        list.addPlaylist(P);
        PersistenceXStream.saveToXMLwithXStream(list);
    }
}
