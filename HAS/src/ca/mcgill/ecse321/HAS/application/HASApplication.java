package ca.mcgill.ecse321.HAS.application;

import ca.mcgill.ecse321.HAS.persistence.PersistenceHAS;
import ca.mcgill.ecse321.HAS.view.View;

public class HASApplication {
	
	public static void main(String[] args) {
		
		// load model
		PersistenceHAS.loadLibraryModel();
		
		//Start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				View window = new View();
				window.frame.setVisible(true);
			}
		});
		
	}
}
