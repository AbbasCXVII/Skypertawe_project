import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private int IMG_HEIGHT = 60;
	private int IMG_WIDTH = 200;
	private JPasswordField passwordField;
	private JTextField usernameField;	
	private GridBagConstraints gc = new GridBagConstraints();
	private JLabel incorrectLabel;
	
	public LoginPanel() {
		Dimension size = getPreferredSize();
		setPreferredSize(size);
		
		setBackground(MainUI.getBackgroundColor());
		
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    loginButtonPressed();
			  } 
			} );
		
		
		JButton signupBtn = new JButton("Sign Up");
		signupBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    signupButtonPressed();
			  } 
			} );
		
		ImageIcon logoIcon = new ImageIcon("Logo.png");
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(resizeImage(logoIcon));
		
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		incorrectLabel = new JLabel("");
		
		setLayout(new GridBagLayout());
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		//Top Logo
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 0;
		add(logoLabel, gc);
		
		//Centre Mid Components
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		add(usernameLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 2;
		add(usernameField, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		add(passwordLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 4;
		add(passwordField, gc);
		
		//Bottom Buttons
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 1;
		gc.gridy = 6;
		add(loginBtn, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridy = 7;
		add(signupBtn, gc);
		gc.gridy = 5;
		add(incorrectLabel, gc);
	}
	
	private ImageIcon resizeImage(ImageIcon icon) {
		Image image = icon.getImage();
		Image newImg = image.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, java.awt.Image.SCALE_SMOOTH);
		return icon = new ImageIcon(newImg);
	}
	
	private void showIncorrectLabel() {
		incorrectLabel.setText("<html><font color='red'>Incorrect username or password.</font></html>");
		incorrectLabel.revalidate();
		incorrectLabel.repaint();
	}
	
	private void signupButtonPressed() {
		MainUI.signupFrame.setVisible(true);
	}
	
	private void loginButtonPressed() {
		//MainUI.mainFrame.setVisible(true);
		//MainUI.loginFrame.setVisible(false);
		boolean usernameEmpty = true;
		boolean passwordEmpty = true;
		String usernameString = usernameField.getText();
		String passwordString = String.valueOf(passwordField.getPassword());
			// validate users entry
			if(usernameString.equals("")){
				showIncorrectLabel();
				JOptionPane.showMessageDialog(null, "Please enter a Username", "Error!", JOptionPane.ERROR_MESSAGE);
				// Popup to state that the userfield is empty
			} else {
				usernameEmpty = false;
			}
			if(passwordString.length() == 0){
				showIncorrectLabel();
				JOptionPane.showMessageDialog(null, "Incorrect password format", "Error!", JOptionPane.ERROR_MESSAGE);
				// Popup to state that the password field is empty
			} else {
				passwordEmpty = false;
			}
			if(!usernameEmpty && !passwordEmpty){
				Login login = new Login(usernameString, passwordString);
				if(login.checkUserExists()){
					if(login.checkPassword()){
						Main.setLoggedInUser(login.getSearchedUserObject(usernameField.getText()));
						System.out.println(Main.getLoggedInUser().getUsername());
						MainUI.OpenGUI();
						MainUI.loginFrame.dispose();
						//Main.setLoggedInUser(user);
					}else{
						showIncorrectLabel();
						//password incorrect
						//Redisplay login page or just open a notification.
					}
				}else{
					JOptionPane.showMessageDialog(null, "No User With Those Details Existed.", "Error!", JOptionPane.ERROR_MESSAGE);
					//Open a prompt stating no user existed with these details.
				}
			
		}
	}

}
