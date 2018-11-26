/**
 * @file UI_DetailsPanel.java
 * @date 09/12/16
 * 
 * Displays the main page details.
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

public class UI_DetailsPanel extends JPanel {
	
	private DefaultListModel listModel;
	
	private final int IMG_SIZE = 50;
	private final int INTERVAL = 1000;
	
	private JList<String> myContactsList;
	private static User messageUser;
	private User currentUser;
	private JButton profileBtn;
	private JScrollPane listScrollPane;
	private DefaultListModel<String> model;

	public UI_DetailsPanel() {
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		setBackground(MainUI.getBackgroundColor());
		currentUser = Main.getLoggedInUser();

		setBorder(BorderFactory.createTitledBorder("Skypertawe"));

		listModel = new DefaultListModel<String>();
		myContactsList = new JList<String>(listModel);
		updateListModel();
		myContactsList.setModel(listModel);
		myContactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myContactsList.setLayoutOrientation(JList.VERTICAL);
		myContactsList.setSelectedIndex(0);
		myContactsList.setVisibleRowCount(3);
		listScrollPane = new JScrollPane(myContactsList);
		listScrollPane.setViewportView(myContactsList);
		listScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScrollPane.setPreferredSize(new Dimension(130, 250));
		listScrollPane.setMinimumSize(new Dimension(10, 10));

		//Refreshes page every X seconds
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				refreshPage(); 
			}    
		});
		timer.start();

		JLabel contactsLabel = new JLabel("My Friends:  ");

		File profileImage = new File(currentUser.getProfileImg());
		ImageIcon icon;
		if (profileImage.exists()) {
			icon = new ImageIcon(currentUser.getProfileImg());
		} else {
			icon = new ImageIcon("ProfilePicDefault.png");
		}
		profileBtn = new JButton("My Profile (" + currentUser.getRequests().size() + " requests)");
		profileBtn.setIcon(resizeImage(icon));
		profileBtn.setBackground(MainUI.getBackgroundColor());
		profileBtn.setBorder(null);

		profileBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				profileButtonPressed();
			} 
		} );

		JButton addBtn = new JButton("Add Friends");
		addBtn.setBackground(MainUI.getBackgroundColor());
		addBtn.setOpaque(true);
		addBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				addBtnPressed();
			} 
		} );

		JButton removeBtn = new JButton("Remove Friend");
		removeBtn.setBackground(MainUI.getBackgroundColor());
		removeBtn.setOpaque(true);
		removeBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int index = myContactsList.getSelectedIndex();
				if(index != -1) {
					String removeUsername = myContactsList.getSelectedValue();
					User removeUser = Main.getUserList().getSearchedUserObject(removeUsername);
					
					currentUser.removeFriend(removeUsername);
					removeUser.removeFriend(currentUser.getUsername());
					currentUser.getFriends().trimToSize();
					removeUser.getFriends().trimToSize();
					Writer.writeUser(currentUser);
					Writer.writeUser(removeUser);
					
					listModel.removeAllElements();
					updateListModel();
					myContactsList.revalidate();
					myContactsList.repaint();
					listScrollPane.revalidate();
					listScrollPane.repaint();
					UI_Frame.setSelectedUser(null);
				}
			} 
		} );

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.5;

		// First Column
		gc.anchor = GridBagConstraints.LINE_END;

		//Second Column
		gc.anchor = GridBagConstraints.LINE_START;

		gc.gridx = 1;
		gc.gridy = 1;
		add(profileBtn, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(contactsLabel, gc);

		gc.gridx = 1;
		gc.gridy = 3;
		add(listScrollPane, gc);

		gc.ipady = 30;
		gc.weighty = 1.0;
		gc.anchor = GridBagConstraints.LAST_LINE_START;

		gc.gridx = 2;
		gc.gridy = 4;
		add(addBtn, gc);

		gc.gridx = 1;
		gc.gridy = 4;
		add(removeBtn, gc);
	}

	private void profileButtonPressed() {
		//Open profile window
		MainUI.profileFrame.revalidate();
		MainUI.profileFrame.setVisible(true);
	}


	//Displays the list of contacts that aren't added as friends 
	private void addBtnPressed() {
		MainUI.contactsFrame.setVisible(true);
		refreshPage();
	}
	
	//Removes friend with username "username" from users friend list.
	private User findUserFriend(User user, String username) {
		//Loops through logged in users friend list for username "username" and deletes them from the list.
		for (String u : Main.getLoggedInUser().getFriends()) {
			if (u == username) {
				return Main.getUserList().getSearchedUserObject(u);
			}
		}
		return null;
	}


	private ImageIcon resizeImage(ImageIcon imageIcon) {
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(IMG_SIZE, IMG_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg); 
	}

	public static User getMessageUser() {
		return messageUser;
	}
	
	private void updateListModel() {
		String[] namesArray = Main.getLoggedInUser().getFriendsNames();
		for (int i = 0; i < namesArray.length; i++) {
			if (!listModel.contains(namesArray[i])) {
				listModel.addElement(namesArray[i].toString());
			}
		}
	}
	
	//Refreshes required components on JPanel
	private void refreshPage() {
		profileBtn.setText("My Profile (" + currentUser.getRequests().size() + " requests)");
		profileBtn.revalidate();
		profileBtn.repaint();
		
		updateListModel();
		myContactsList.revalidate();
		myContactsList.repaint();
		listScrollPane.revalidate();
		listScrollPane.repaint();
		
		if (myContactsList.getSelectedIndex() != -1) {
			String selectedUsername = myContactsList.getSelectedValue();
			User selectedUser = Main.getUserList().getSearchedUserObject(selectedUsername);
			UI_Frame.setSelectedUser(selectedUser);
		}
	}

}
