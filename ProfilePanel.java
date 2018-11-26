/**
 * @file ProfilePanel.java
 * @date 09/12/16
 * 
 * JPanel that displays the user profile information in the profile frame.
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfilePanel extends JPanel {
	
	private User currentUser;
	public ProfilePanel() {
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		currentUser = Main.getLoggedInUser();
		
		setBorder(BorderFactory.createTitledBorder("Profile"));
		setBackground(MainUI.getBackgroundColor());
		
		//Components
		File profileImage = new File(currentUser.getProfileImg());
		ImageIcon icon;
		if (profileImage.exists()) {
			icon = new ImageIcon(currentUser.getProfileImg());
		} else {
			icon = new ImageIcon("ProfilePicDefault.png");
		}
		JLabel profileLabel = new JLabel("");
		profileLabel.setIcon(MainUI.resizeImageIcon(icon));
		JLabel usernameLabel = new JLabel(currentUser.getUsername());
		JLabel nameLabel = new JLabel(currentUser.getFname() + " " + currentUser.getLname());
		JLabel birthdayLabel = new JLabel("DOB: " + currentUser.getBirthday());
		JLabel addressLabel = new JLabel("Address: " + currentUser.getAddress());
		
		JButton signoutBtn = new JButton("Sign Out");
		signoutBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				MainUI.signoutWindows();
				Main.setLoggedInUser(null);
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		//First Column
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(profileLabel, gc);
		gc.gridy = 1;
		add(nameLabel, gc);
		gc.gridy = 2;
		add(birthdayLabel, gc);
		gc.gridy = 3;
		add(addressLabel, gc);
		gc.gridy = 4;
		add(signoutBtn, gc);
		
	}
}
