/**
 * @file MainUI.java
 * @date 06/12/16
 * 
 * Main UI handles all frames in system.
 */

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainUI {
	public static JFrame mainFrame;
	public static JFrame signupFrame;
	public static JFrame loginFrame;
	public static JFrame contactsFrame;
	public static JFrame profileFrame;
	public static JFrame drawingFrame;
	
	private static final Color backgroundColor = new Color(201, 235, 255);
	
	private static int IMG_SIZE = 50;
	
	public static void OpenGUI() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				if (Main.getLoggedInUser() != null) {
					//Main Frame
					mainFrame = new UI_Frame("Skypertawe");
					mainFrame.setSize(750, 500);
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setVisible(true);
					mainFrame.setBackground(getBackgroundColor());
					mainFrame.setResizable(false);
					
					//Contacts Frame
					contactsFrame = new ContactsFrame("Add new contact");
					contactsFrame.setSize(400, 400);
					contactsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//contactsFrame.setVisible(true);
					contactsFrame.setBackground(getBackgroundColor());
					contactsFrame.setResizable(false);
					//Profile Frame
					profileFrame = new ProfileFrame("Profile");
					profileFrame.setSize(400, 400);
					profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//profileFrame.setVisible(true);
					profileFrame.setBackground(getBackgroundColor());
					profileFrame.setResizable(false);
					//Drawing Frame
					drawingFrame = new DrawingFrame("Draw");
					drawingFrame.setSize(500, 523);
					drawingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//drawingFrame.setVisible(true);
					drawingFrame.setBackground(getBackgroundColor());
					drawingFrame.setResizable(false);
				} else {
					//Login Frame
					loginFrame = new LoginFrame("Login");
					loginFrame.setSize(750, 500);
					loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					loginFrame.setVisible(true);
					loginFrame.setResizable(false);
					//Sign Up Frame
					signupFrame = new SignUpFrame("Sign Up");
					signupFrame.setSize(750, 500);
					signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//signupFrame.setVisible(true);
					signupFrame.setResizable(false);
				}
			
			
			}
			
		});
	}
	
	/**
	 * Closes all windows for when a user
	 * signs out and re runs OpenGUI for new login
	 */
	public static void signoutWindows() {
		mainFrame.setVisible(false);
		contactsFrame.setVisible(false);
		profileFrame.setVisible(false);
		drawingFrame.setVisible(false);
		signupFrame.setVisible(false);
		loginFrame.setVisible(false);
		Main.setLoggedInUser(null);
		OpenGUI();
	}
	
	public static Color getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Resizes and ImageIcon to the required size.
	 * @param imageIcon ImageIcon to resize.
	 * @return resized ImageIcon.
	 */
	public static ImageIcon resizeImageIcon(ImageIcon imageIcon) {
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(IMG_SIZE, IMG_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg); 
	}
	
}