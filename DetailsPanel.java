/**
 * @file DetailsPanel.java
 * @date 06/12/16
 * 
 * DetailsPanel swing panel with sign up details
 * for user to sign up.
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DetailsPanel extends JPanel {
	private final int IMG_SIZE = 150;
	private final int FIELD_LENGTH = 10;
	private final int PHONE_NUMBER_LENGTH = 11;
	private String imgUrl = "ProfilePicDefault.png";
	private File imgFile = new File(imgUrl);
	private JLabel imgLabel;
	private JDatePickerImpl datePicker;
	
	JTextField fNameField;
	JTextField lNameField;
	JTextField usernameField;
	JPasswordField passwordField;
	JTextField cityField;
	JTextField phoneField;
	
	public DetailsPanel() {
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Personal Details"));
		setBackground(MainUI.getBackgroundColor());
		
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		JLabel fnameLabel = new JLabel("First Name: ");
		JLabel lnameLabel = new JLabel("Last Name: ");
		JLabel cityLabel = new JLabel("City: ");
		JLabel phoneLabel = new JLabel("Telephone: ");
		JLabel picLabel = new JLabel("Picture: ");
		JLabel profLabel = new JLabel("Profile");
		JLabel birthdayLabel = new JLabel("Date of Birth:");
		
		fNameField = new JTextField(FIELD_LENGTH);
		lNameField = new JTextField(FIELD_LENGTH);
		usernameField = new JTextField(FIELD_LENGTH);
		passwordField = new JPasswordField(FIELD_LENGTH);
		cityField = new JTextField(FIELD_LENGTH);
		phoneField = new JTextField(FIELD_LENGTH);
		
		imgLabel = new JLabel("", JLabel.CENTER);
		setProfileImage(imgUrl, imgLabel); //Setting default profile pic
		
		JButton imgBtn = new JButton("Choose File");
		imgBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    imgButtonPressed();
			  } 
			} );
		
		JButton submitBtn = new JButton("Sign Up");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitButtonPressed();
			}
		});
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		// First Column
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 0;
		add(usernameLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(passwordLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		add(fnameLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		add(lnameLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		add(cityLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		add(phoneLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 6;
		add(birthdayLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 7;
		add(picLabel, gc);
		
		//Second Column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(usernameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(passwordField, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(fNameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(lNameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		add(cityField, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		add(phoneField, gc);
		
		gc.gridx = 1;
		gc.gridy = 7;
		add(imgBtn, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 6;
		add(datePicker, gc);
		
		
		//Third Column
		gc.gridx = 2;
		gc.gridy = 0;
		add(profLabel, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		add(imgLabel, gc);
		
		//Final Row
		gc.gridx = 2;
		gc.gridy = 7;
		add(submitBtn, gc);
	}
	
	/**
	 * Action to be performed when img button pressed.
	 */
	private void imgButtonPressed() {
		JFileChooser fc = new JFileChooser();
		int returnValue = fc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();
			imgUrl = filePath;
			setProfileImage(filePath, imgLabel);
	    }
	}
	
	/**
	 * Action to be performed when submit button pressed.
	 */
	private void submitButtonPressed() {
		String fName = fNameField.getText();
		String lName = lNameField.getText();
		String username = usernameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String city = cityField.getText();
		String phone = phoneField.getText();
		String profImg = imgUrl;
		String birthday = datePicker.getJFormattedTextField().getText();
		long number;
		boolean error = false;
		String errors = "";

		//Validation for registration form
		if(fName.equals("")) {
			errors = "First Name Empty.\n";
			error = true;
		}

		if(lName.equals("")){
			errors = errors + " Last Name Empty.\n";
			error = true;
		}

		if(username.equals("")) {
			errors = errors + "Username Empty.\n";
			error = true;
		}

		if(password.equals("")) {
			errors = errors + "Password Empty.\n";
			error = true;
		}

		if(city.equals("")) {
			errors = errors + "City Empty.\n";
			error = true;
		}

		if(phone.equals("")){
			errors = errors + "Phone Number Empty.\n";
			error = true;
		}

		if(birthday.equals("")) {
			errors = errors + "Birthday Empty.\n";
			error = true;
		}

		if(fName.contains(",")) {
			errors = errors + "First Name contains invalid characters.\n";
			error = true;
		}

		if(lName.contains(",")) {
			errors = errors + "Last Name contains invalid characters.\n";
			error = true;
		}
		if(username.contains(",") || username.contains(" ")) {
			errors = errors + "Username contains invalid characters.\n";
			error = true;
		}

		if(password.contains(",")) {
			errors = errors + "Password contains invalid characters.\n";
			error = true;
		}
		if(city.contains(",")) {
			errors = errors + "City contains invalid characters.\n";
			error = true;
		}
		if(phone.contains(",")) {
			errors = errors + "Phone Number contains invalid characters.\n";
			error = true;
		}
		if(birthday.contains(",")) {
			errors = errors + "Birthday contains invalid characters.\n";
			error = true;
		}
		if(phone.length() != PHONE_NUMBER_LENGTH) {
			errors = errors + "Incorrect Phone Number length.\n";
			error = true;
		}

		try{
			number = Long.parseLong(phone);
			}catch(NumberFormatException e){
				errors = errors + "Please only enter digits for phone number.\n";
				error = true;
		}


		if(!error){
			User user = new User(username, password, fName, lName, profImg,
					city, birthday, phone, new ArrayList<String>(), new ArrayList<String>());
			Main.getUserList().add(user);
			Writer.writeUser(user);
			Main.getUserList().add(user);
			MainUI.signupFrame.dispose();
			// Display message and then close window.
			JOptionPane.showMessageDialog(null, "You Are Now registered. Please log in.", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, errors, "Error!", JOptionPane.ERROR_MESSAGE);
		}


	}
	
	/**
	 * 
	 * @param url path of image to set as profile picture.
	 * @param label JLabel to change icon of (to picture).
	 * @see 
	 */
	private void setProfileImage(String url, JLabel label) {
		ImageIcon imageIcon = new ImageIcon(url); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(IMG_SIZE, IMG_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		imgLabel.setIcon(imageIcon);
		imgLabel.revalidate();
		imgLabel.repaint();
	}
	
}
