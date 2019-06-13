package ca.mcgill.ecse321.HAS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;


import ca.mcgill.ecse321.HAS.controller.HASController;
import ca.mcgill.ecse321.HAS.controller.InvalidInputException;
import ca.mcgill.ecse321.HAS.model.Album;
import ca.mcgill.ecse321.HAS.model.Artist;
import ca.mcgill.ecse321.HAS.model.Library;
import ca.mcgill.ecse321.HAS.model.Location;
import ca.mcgill.ecse321.HAS.model.Playlist;
import ca.mcgill.ecse321.HAS.model.Song;


import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


public class View {
	//UI
	
	public JFrame frame;
	private String error = null;
	
	private JTextField textFieldSongTitle;
	private JTextField textFieldSongDuration;
	private JTextField textFieldAlbumName;
	private JTextField textFieldAlbumGenre;
	private JTextField textFieldAlbumDate;
	
	
	private JLabel lblError;
	
	
	private JComboBox<String> comboBoxAddSongsToAlbum;
	private JComboBox<String> comboBoxSelectArtist;
	private JComboBox<String> comboBoxSelectArtist_2;
	private JComboBox<String> comboBoxAddSongsToPlaylist;
	private JComboBox<String> comboBoxPickAlbum;
	private JComboBox<String> comboBoxPickPlaylist;
	private JComboBox<String> comboBoxPickSong;
	private JComboBox<String> comboBoxSetLocation;
	private JComboBox<String> comboBoxVolumeLevel;
	private JComboBox<String> comboBoxVolumeLocation;
	
	
	
	
	
	//Data
	
	private Integer selectedSong = -1;
	private Integer selectedSong_2 = -1;
	
	
	private String selectedSong_3 = "";
	
	private String selectedArtist;
	private String selectedArtist_2;
	private String selectedAlbum;
	private String selectedPlaylist;
	private String selectedLocation;
	private String selectedLocation_2;
	private String selectedVolume;
	private HashMap<Integer, Song> songs;
	private HashMap<Integer, Artist> artists;
	private JTextField textFieldPlaylistName;
	private JTextField textFieldLocationName;
	
	//Songs object -- always matches JComboBox indices
	ArrayList<Song> songsArrayListMatchesBox = new ArrayList<Song>(); // array with Song objects
	ArrayList<Song> selectedSongArray = new ArrayList<Song>(); // array with Song objects
	
	ArrayList<Song> songsArrayListMatchesBox_2 = new ArrayList<Song>(); // array with Song objects
	ArrayList<Song> selectedSongArray_2 = new ArrayList<Song>(); // array with Song objects
	
	
	
	//Controller
	final HASController ctr = new HASController();
	private JTextField textFieldArtistName;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 880, 716);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddSongsToLibrary = new JLabel("ADD SONG TO LIBRARY");
		lblAddSongsToLibrary.setBounds(6, 181, 204, 16);
		frame.getContentPane().add(lblAddSongsToLibrary);
		
		
		
		
		JLabel lblSongTitle = new JLabel("Song Title");
		lblSongTitle.setBounds(6, 209, 89, 16);
		frame.getContentPane().add(lblSongTitle);
		
		textFieldSongTitle = new JTextField();
		textFieldSongTitle.setBounds(269, 203, 134, 28);
		frame.getContentPane().add(textFieldSongTitle);
		textFieldSongTitle.setColumns(10);
		
		JLabel lblSongDuration = new JLabel("Song Duration (in seconds)");
		lblSongDuration.setBounds(6, 237, 231, 16);
		frame.getContentPane().add(lblSongDuration);
		
		textFieldSongDuration = new JTextField();
		textFieldSongDuration.setBounds(269, 231, 134, 28);
		frame.getContentPane().add(textFieldSongDuration);
		textFieldSongDuration.setColumns(10);
		
		
		
		JLabel lblAddAlbumTo = new JLabel("ADD ALBUM TO LIBRARY");
		lblAddAlbumTo.setBounds(6, 329, 188, 16);
		frame.getContentPane().add(lblAddAlbumTo);
		
		JLabel lblAlbumName = new JLabel("Album Name");
		lblAlbumName.setBounds(6, 357, 89, 16);
		frame.getContentPane().add(lblAlbumName);
		
		textFieldAlbumName = new JTextField();
		textFieldAlbumName.setBounds(269, 351, 134, 28);
		frame.getContentPane().add(textFieldAlbumName);
		textFieldAlbumName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Album Genre");
		lblNewLabel.setBounds(6, 385, 89, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldAlbumGenre = new JTextField();
		textFieldAlbumGenre.setBounds(269, 379, 134, 28);
		frame.getContentPane().add(textFieldAlbumGenre);
		textFieldAlbumGenre.setColumns(10);
		
		JLabel lblAlbumReleaseDate_1 = new JLabel("Album Release Date (yyyy-MM-dd)");
		lblAlbumReleaseDate_1.setBounds(6, 413, 242, 16);
		frame.getContentPane().add(lblAlbumReleaseDate_1);
		
		textFieldAlbumDate = new JTextField();
		textFieldAlbumDate.setBounds(269, 407, 134, 28);
		frame.getContentPane().add(textFieldAlbumDate);
		textFieldAlbumDate.setColumns(10);
		
		JLabel lblArtist = new JLabel("Select Artist");
		lblArtist.setBounds(6, 265, 145, 16);
		frame.getContentPane().add(lblArtist);
		
		JLabel lblAddSongs = new JLabel("Add Songs to Album");
		lblAddSongs.setBounds(6, 471, 204, 16);
		frame.getContentPane().add(lblAddSongs);
		
		final JLabel lblPlaying = new JLabel("");
		lblPlaying.setForeground(Color.BLACK);
		lblPlaying.setBounds(48, 62, 355, 16);
		frame.getContentPane().add(lblPlaying);
		
		
		final JTextArea textAreaAlbum = new JTextArea();
		textAreaAlbum.setBounds(6, 497, 216, 131);
		frame.getContentPane().add(textAreaAlbum);
		
		final JTextArea textAreaPlaylist = new JTextArea();
		textAreaPlaylist.setBounds(443, 497, 216, 131);
		frame.getContentPane().add(textAreaPlaylist);
		
		
		
		//Buttons
		
		
		JButton btnAddSongToLibrary = new JButton("Add Song");
		btnAddSongToLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				if (comboBoxSelectArtist.getSelectedIndex() != -1 && comboBoxSelectArtist.getSelectedIndex() != 0){
					addSongButtonActionPerformed(evt2);
				}
				else {
					lblError.setText("Please add an artist name! ");
				}
				
				
		
			}
		});
		btnAddSongToLibrary.setBounds(286, 288, 117, 29);
		frame.getContentPane().add(btnAddSongToLibrary);
		
		
		
		final JButton btnAddSongToAlbum = new JButton("Add Song");
		btnAddSongToAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxAddSongsToAlbum.getSelectedIndex() != -1 && comboBoxAddSongsToAlbum.getSelectedIndex() != 0){
					textAreaAlbum.append(comboBoxAddSongsToAlbum.getSelectedItem() + "\n");
					selectedSongArray.add(songsArrayListMatchesBox.get(comboBoxAddSongsToAlbum.getSelectedIndex()));
					
				}
				else {
					lblError.setText("Please add a song name to the combobox! ");
				}
			}
		});
		btnAddSongToAlbum.setBounds(286, 492, 117, 29);
		frame.getContentPane().add(btnAddSongToAlbum);
		
		
		
		final JButton btnAddSongToPlaylist = new JButton("Add Song");
		btnAddSongToPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxAddSongsToPlaylist.getSelectedIndex() != -1 && comboBoxAddSongsToPlaylist.getSelectedIndex() != 0){
					textAreaPlaylist.append(comboBoxAddSongsToPlaylist.getSelectedItem() + "\n");
					selectedSongArray_2.add(songsArrayListMatchesBox_2.get(comboBoxAddSongsToPlaylist.getSelectedIndex()));
					
				}
				else {
					lblError.setText("Please add a song name to the combobox! ");
				}
				
			}
		});
		btnAddSongToPlaylist.setBounds(723, 492, 117, 29);
		frame.getContentPane().add(btnAddSongToPlaylist);
		
		
		
		
		JButton btnAddAlbum = new JButton("Add Album");
		btnAddAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (comboBoxSelectArtist_2.getSelectedIndex() != -1 && comboBoxSelectArtist_2.getSelectedIndex() != 0){
					addAlbumButtonActionPerformed(evt);
				}
				else {
					lblError.setText("Please add an artist name! ");
				}
			}
		});
		btnAddAlbum.setBounds(286, 599, 117, 29);
		frame.getContentPane().add(btnAddAlbum);
		
		
		JButton btnAddArtist = new JButton("Add Artist");
		btnAddArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArtistButtonActionPerformed(e);
			
			}
		});
		btnAddArtist.setBounds(286, 148, 117, 29);
		frame.getContentPane().add(btnAddArtist);
		
		
		JButton btnAddLocation = new JButton("Add Location");
		btnAddLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLocationButtonActionPerformed(e);
			}
		});
		btnAddLocation.setBounds(723, 85, 117, 29);
		frame.getContentPane().add(btnAddLocation);
		
		
		
		
		JButton btnAddPlaylist = new JButton("Add Playlist");
		btnAddPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (comboBoxAddSongsToPlaylist.getSelectedIndex() != -1 && comboBoxAddSongsToPlaylist.getSelectedIndex() != 0){
					addPlaylistButtonActionPerformed(evt);
				}
				else {
					lblError.setText("Please add a playlist name! ");
				}
			}
		});
		btnAddPlaylist.setBounds(723, 599, 117, 29);
		frame.getContentPane().add(btnAddPlaylist);
		
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxSetLocation.getSelectedIndex() <= 0) //error here
					lblError.setText("No location has been selected! ");
				else {
					lblError.setText("");
					Integer vol = ctr.getVolume((String)comboBoxSetLocation.getSelectedItem());
					if (comboBoxPickSong.getSelectedIndex() > 0){
						lblPlaying.setText(selectedSong_3 + " is playing at location: " + selectedLocation + " at volume: " + vol);
					}else if (comboBoxPickAlbum.getSelectedIndex() > 0){
						lblPlaying.setText(selectedAlbum + " is playing at location: " + selectedLocation);
						//In each of these three else statements, takes the texts and add them to an array, displays array in textarea
					}else if (comboBoxPickPlaylist.getSelectedIndex() > 0){
						lblPlaying.setText(selectedPlaylist + " is playing at location: " + selectedLocation);
					}else {
						//Neither a song, nor an album, or a playlist was selected.. error
						lblError.setText("Neither songs, albums nor playlists have been selected! ");
					}
				}
			}
		});
		btnPlay.setBounds(723, 252, 117, 29);
		frame.getContentPane().add(btnPlay);
		
		
		JButton btnSetVolume = new JButton("Set Volume");
		btnSetVolume.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (comboBoxVolumeLocation.getSelectedIndex() <= 0)
					lblError.setText("Please select a location");
				else {
					if (comboBoxVolumeLevel.getSelectedIndex() < 0){
						lblError.setText("Please select the new volume");
					}else {
						selectedVolume = (String) comboBoxVolumeLevel.getSelectedItem();
						selectedLocation_2 = (String)comboBoxVolumeLocation.getSelectedItem();
						ctr.updateVolume(selectedLocation_2, selectedVolume);
						lblError.setText("");
					}
				}
			}
			
		});
		btnSetVolume.setBounds(723, 372, 117, 29);
		frame.getContentPane().add(btnSetVolume);
		
		
		JButton btnMute = new JButton("Mute");
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxVolumeLocation.getSelectedIndex() <= 0)
					lblError.setText("Please select a location");
				else {
					int d = 0;
					ctr.updateVolume(selectedLocation_2, String.valueOf(d));
					lblError.setText("");
				}
				
			}
		});
		btnMute.setBounds(488, 372, 117, 29);
		frame.getContentPane().add(btnMute);
		
		
	
		
		
		
		//ComboBoxes
		
		comboBoxAddSongsToAlbum = new JComboBox<String>();
		comboBoxAddSongsToAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				selectedSong = cb.getSelectedIndex();
			}
		});
		comboBoxAddSongsToAlbum.setBounds(269, 467, 134, 27);
		frame.getContentPane().add(comboBoxAddSongsToAlbum);
		
		
		comboBoxSelectArtist = new JComboBox<String>();
		comboBoxSelectArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				selectedArtist = (String) cb.getSelectedItem(); //Gets selected item, casts to a String object
				
			}
		});
		comboBoxSelectArtist.setBounds(269, 261, 134, 27);
		frame.getContentPane().add(comboBoxSelectArtist);
		
		
		
		
		comboBoxSelectArtist_2 = new JComboBox<String>();
		comboBoxSelectArtist_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				selectedArtist_2 = (String) cb.getSelectedItem();
				refreshSongData();
			}
		});
		comboBoxSelectArtist_2.setBounds(269, 439, 134, 27);
		frame.getContentPane().add(comboBoxSelectArtist_2);
		
		
		comboBoxAddSongsToPlaylist = new JComboBox<String>();
		comboBoxAddSongsToPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				selectedSong_2 = cb.getSelectedIndex();
			
			}
		});
		
		
		comboBoxAddSongsToPlaylist.setBounds(706, 465, 134, 27);
		frame.getContentPane().add(comboBoxAddSongsToPlaylist);
		
		
	

		
		comboBoxPickAlbum = new JComboBox<String>();
		comboBoxPickAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				if(cb.getSelectedIndex() != -1){
					selectedAlbum = (String) cb.getSelectedItem();	
					comboBoxPickPlaylist.setSelectedIndex(-1);
					comboBoxPickSong.setSelectedIndex(-1);
				}
			}
		});
		
		
		
		
		
		
		comboBoxPickAlbum.setBounds(706, 168, 134, 27);
		frame.getContentPane().add(comboBoxPickAlbum);
		
		
		comboBoxPickPlaylist = new JComboBox<String>();
		comboBoxPickPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				if(cb.getSelectedIndex() != -1){
					selectedPlaylist = (String) cb.getSelectedItem();
					//
					comboBoxPickAlbum.setSelectedIndex(-1);
					comboBoxPickSong.setSelectedIndex(-1);
					//
				}
			}
		});
		comboBoxPickPlaylist.setBounds(706, 196, 134, 27);
		frame.getContentPane().add(comboBoxPickPlaylist);
		
		
		
		
		
		comboBoxPickSong = new JComboBox<String>();
		comboBoxPickSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
				if(cb.getSelectedIndex() != -1){
					selectedSong_3 =  (String)cb.getSelectedItem();
					//
					comboBoxPickAlbum.setSelectedIndex(-1);
					comboBoxPickPlaylist.setSelectedIndex(-1);
					//
				}
			}
		});
		comboBoxPickSong.setBounds(706, 225, 134, 27);
		frame.getContentPane().add(comboBoxPickSong);
		
		
		comboBoxSetLocation = new JComboBox<String>();
		comboBoxSetLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt2) {
				JComboBox<String> cb = (JComboBox<String>) evt2.getSource();
					selectedLocation =  (String)cb.getSelectedItem();
			}
		});
		comboBoxSetLocation.setBounds(706, 140, 134, 27);
		frame.getContentPane().add(comboBoxSetLocation);
		
		
		
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(6, 6, 834, 16);
		frame.getContentPane().add(lblError);
		
		JLabel lblAddPlaylistTo = new JLabel("ADD PLAYLIST TO LIBRARY");
		lblAddPlaylistTo.setBounds(443, 413, 209, 16);
		frame.getContentPane().add(lblAddPlaylistTo);
		
		JLabel lblPlaylistName = new JLabel("Playlist Name");
		lblPlaylistName.setBounds(443, 441, 117, 16);
		frame.getContentPane().add(lblPlaylistName);
		
		textFieldPlaylistName = new JTextField();
		textFieldPlaylistName.setBounds(706, 435, 134, 28);
		frame.getContentPane().add(textFieldPlaylistName);
		textFieldPlaylistName.setColumns(10);
		
		JLabel lblAddASong = new JLabel("Add Songs to Playlist");
		lblAddASong.setBounds(443, 469, 156, 16);
		frame.getContentPane().add(lblAddASong);
		
		JLabel lblAddLocation = new JLabel("ADD LOCATION");
		lblAddLocation.setBounds(443, 34, 162, 16);
		frame.getContentPane().add(lblAddLocation);
		
		JLabel lblLocationName = new JLabel("Location Name");
		lblLocationName.setBounds(443, 62, 117, 16);
		frame.getContentPane().add(lblLocationName);
		
		textFieldLocationName = new JTextField();
		textFieldLocationName.setBounds(706, 56, 134, 28);
		frame.getContentPane().add(textFieldLocationName);
		textFieldLocationName.setColumns(10);
		
		
		
		JLabel lblPlayAtLocations = new JLabel("PLAY AT LOCATIONS");
		lblPlayAtLocations.setBounds(443, 116, 177, 16);
		frame.getContentPane().add(lblPlayAtLocations);
		
		JLabel lblNewLabel_1 = new JLabel("Set Location");
		lblNewLabel_1.setBounds(443, 144, 145, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		JLabel lblPickAlbum = new JLabel("Pick Album");
		lblPickAlbum.setBounds(443, 172, 108, 16);
		frame.getContentPane().add(lblPickAlbum);
		
		
		
		JLabel lblPickPlaylist = new JLabel("Pick Playlist");
		lblPickPlaylist.setBounds(443, 200, 96, 16);
		frame.getContentPane().add(lblPickPlaylist);
		
		
		
		JLabel lblPickSong = new JLabel("Pick Song");
		lblPickSong.setBounds(443, 229, 61, 16);
		frame.getContentPane().add(lblPickSong);
		
		JLabel lblVolumeControl = new JLabel("VOLUME CONTROL");
		lblVolumeControl.setBounds(443, 291, 145, 16);
		frame.getContentPane().add(lblVolumeControl);
		
		JLabel label = new JLabel("Set Location");
		label.setBounds(443, 323, 145, 16);
		frame.getContentPane().add(label);
		
		comboBoxVolumeLocation = new JComboBox<String>();
		comboBoxVolumeLocation.setBounds(706, 319, 134, 27);
		frame.getContentPane().add(comboBoxVolumeLocation);
		
		JLabel lblVolumeLevel = new JLabel("Volume level");
		lblVolumeLevel.setBounds(443, 351, 117, 16);
		frame.getContentPane().add(lblVolumeLevel);
		
		comboBoxVolumeLevel = new JComboBox<String>();
		comboBoxVolumeLevel.setBounds(706, 347, 134, 27);
		frame.getContentPane().add(comboBoxVolumeLevel);
		for (int i = 1;i<=10;i++) comboBoxVolumeLevel.addItem(String.valueOf(i));
		
		
		
		JLabel lblAddArtist = new JLabel("ADD ARTIST");
		lblAddArtist.setBounds(6, 97, 162, 16);
		frame.getContentPane().add(lblAddArtist);
		
		JLabel lblNewLabel_2 = new JLabel("Artist Name");
		lblNewLabel_2.setBounds(6, 125, 117, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textFieldArtistName = new JTextField();
		textFieldArtistName.setBounds(269, 119, 134, 28);
		frame.getContentPane().add(textFieldArtistName);
		textFieldArtistName.setColumns(10);
		
	
		
		JLabel lblSelectArtist = new JLabel("Select Artist");
		lblSelectArtist.setBounds(6, 443, 117, 16);
		frame.getContentPane().add(lblSelectArtist);
		
		JLabel lblNewLabel_3 = new JLabel("HAS (Home Audio System)");
		lblNewLabel_3.setBounds(6, 34, 171, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
	
		
		
		refreshData();
	}
	

	private void addSongButtonActionPerformed(ActionEvent evt2){
		
        error = null;
		try{
			ctr.addSong(textFieldSongTitle.getText(), textFieldSongDuration.getText(), comboBoxSelectArtist.getSelectedItem().toString());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
        refreshData();
		
	}
	
	private void addArtistButtonActionPerformed(ActionEvent evt){
		
		error = null;
		try{
			ctr.addArtist(textFieldArtistName.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
        refreshData();
		
	}
	

	
	private void addAlbumButtonActionPerformed(ActionEvent evt){
		
		
        error = null;
		try{
			ctr.createAlbum(textFieldAlbumGenre.getText(), textFieldAlbumDate.getText(), textFieldAlbumName.getText(), selectedSongArray, comboBoxSelectArtist_2.getSelectedItem().toString());
		} catch (InvalidInputException e) {
			error = e.getMessage();
			//}
		}
        refreshData();
	}
	
	
	private void addLocationButtonActionPerformed(ActionEvent evt) {
		error = null;
		try{
			ctr.addLocation(textFieldLocationName.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
        refreshData();
		
	}
	

	private void addPlaylistButtonActionPerformed(ActionEvent evt){
        error = null;
		try{
			ctr.createPlaylist(textFieldPlaylistName.getText(), selectedSongArray_2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			//}
		}
        refreshData();
	}
	
	
	
	private void refreshData(){
		Library rm = Library.getInstance();
		// error
		lblError.setText(error);
		if (error == null || error.length() == 0) {
			//Loading the list of songs from the library
			songs = new HashMap<Integer, Song>();
			
			comboBoxAddSongsToAlbum.removeAllItems();
			comboBoxAddSongsToAlbum.addItem("");
			
			songsArrayListMatchesBox.clear(); //We have to clear the arraylist beforehand, or it won't match.
			selectedSongArray.clear(); //We must empty the selected songs as well, so it doesn't save songs from the old session
			songsArrayListMatchesBox.add(null);
			
			comboBoxAddSongsToPlaylist.removeAllItems();
			comboBoxAddSongsToPlaylist.addItem("");
			
			
			comboBoxPickAlbum.removeAllItems();
			comboBoxPickAlbum.addItem("");
		
			comboBoxPickPlaylist.removeAllItems();
			comboBoxPickPlaylist.addItem("");
			
			comboBoxPickSong.removeAllItems();
			comboBoxPickSong.addItem("");
			
			comboBoxSetLocation.removeAllItems();
			comboBoxSetLocation.addItem("");
			

			Iterator<Song> pIt = rm.getSongs().iterator();
			Integer index = 1;
			
			//Loading the list of artists from the xml
			artists = new HashMap<Integer, Artist>();
			
			comboBoxSelectArtist.removeAllItems();
			comboBoxSelectArtist_2.removeAllItems();
			comboBoxSelectArtist.addItem("");
			comboBoxSelectArtist_2.addItem("");
			Iterator<Artist> kIt = rm.getArtists().iterator();
			index = 1;
			while (kIt.hasNext()) {
				Artist a = kIt.next();
				artists.put(index, a);
				comboBoxSelectArtist.addItem(a.getArtistName());
				comboBoxSelectArtist_2.addItem(a.getArtistName());
				index++;
			}
			comboBoxAddSongsToPlaylist.removeAllItems();
			comboBoxAddSongsToPlaylist.addItem("");
			
			comboBoxVolumeLocation.removeAllItems();
			comboBoxVolumeLocation.addItem("");
			// ADDED
			Iterator<Song> sIt = rm.getSongs().iterator();
			while (sIt.hasNext()){
				Song s = sIt.next();
				comboBoxAddSongsToPlaylist.addItem(s.getSongTitle());
				songsArrayListMatchesBox_2.add(s); // Add songs to text area!!!
				comboBoxPickSong.addItem(s.getSongTitle());
			}
			// ADDED
			Iterator<Album> oIt = rm.getAlbums().iterator();
			while (oIt.hasNext()){
				Album s = oIt.next();
				comboBoxPickAlbum.addItem(s.getNameAlbum());
				
				
			}
			
			Iterator<Playlist> tIt = rm.getPlaylists().iterator();
			while (tIt.hasNext()){
				Playlist s = tIt.next();
				comboBoxPickPlaylist.addItem(s.getPlaylistName());
				
				System.out.println("Getting playlist names");
				
			}
			
			Iterator<Location> lIt = rm.getLocations().iterator();
			while (lIt.hasNext()){
				Location s = lIt.next();
				comboBoxSetLocation.addItem(s.getLocationName());
				comboBoxVolumeLocation.addItem(s.getLocationName());
			}
			
			
			comboBoxSelectArtist.setSelectedIndex(-1); //Un-selects everything "-1" is the code for none of the selections.
			comboBoxSelectArtist_2.setSelectedIndex(-1);
			
			
		
		}
	
	}
	
	
	

	private void refreshSongData(){
		System.out.println("refreshSongData");
		songs = new HashMap<Integer, Song>();
		
		comboBoxAddSongsToAlbum.removeAllItems();
		comboBoxAddSongsToAlbum.addItem("");
		selectedSongArray.clear(); //We must empty the selected songs as well, so it doesn't save songs from the old session
		songsArrayListMatchesBox.clear();
		songsArrayListMatchesBox.add(null);
		
		
		if (comboBoxSelectArtist_2.getSelectedIndex() != 0 && comboBoxSelectArtist_2.getSelectedIndex() != 0){
			Iterator<Song> pIt = Library.getInstance().getSongs().iterator();
			Integer index = 1;
			while (pIt.hasNext()) {
				Song p = pIt.next();
				if (comboBoxSelectArtist_2.getSelectedIndex() != -1 && comboBoxSelectArtist_2.getSelectedIndex() != 0){
					if (p.getArtist() == comboBoxSelectArtist_2.getSelectedItem().toString()){
						System.out.println("p.artist: " + p.getArtist() + " combo.artist " + comboBoxSelectArtist_2.getSelectedItem().toString());
						songs.put(index, p);
						comboBoxAddSongsToAlbum.addItem(p.getSongTitle());
						songsArrayListMatchesBox.add(p); //Matches combobox perfectly
						index++;	
						
					}
				}
			}
		}
	}
}

