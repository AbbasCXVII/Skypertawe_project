/**
 * @file RequestsPanel.java
 * @date 09/12/16
 * 
 * JPanel that displays user friend requests in profile frame.
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RequestsPanel extends JPanel {
	
	private final int INTERVAL = 1000;

	private User currentUser;
	private ArrayList<String> requestsList = new ArrayList<String>();
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	private GridBagConstraints gc = new GridBagConstraints();
	
	public RequestsPanel() {
		Dimension size = getPreferredSize();
		size.width = 150;
		setPreferredSize(size);
		currentUser = Main.getLoggedInUser();
		
		setBorder(BorderFactory.createTitledBorder("Friend Requests"));
		setBackground(MainUI.getBackgroundColor());
		
		setLayout(new GridBagLayout());
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		updateRequests();
		
		//When this panel is open requests are updated every X seconds.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (MainUI.profileFrame.isVisible()) {
					updateRequests(); 
				}
			}    
		});
		timer.start();
		
	}
	
	//Updates requests buttons
	private void updateRequests() {
		for (String u : currentUser.getRequests()) {
			//If the users request is not already displayed, display their request.
			if (!requestsList.contains(u)) {
				JButton requestBtn = new JButton(u + " (Accept)");
				requestBtn.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						    acceptButtonPressed(requestBtn, u);
						  } 
						} );
				add(requestBtn, gc);
				gc.gridy++;
				requestsList.add(u);
				buttonList.add(requestBtn);
			}
		}
		revalidate();
	}
	
	private void acceptButtonPressed(JButton button, String requestUser) {
		String btnText = button.getText();
		String username = btnText.split(" ")[0]; //Gets first word from btnText (which is the username).
		User sender = Main.getUserList().getSearchedUserObject(username);
		
		sender.addFriend(currentUser.getUsername());
		currentUser.addFriend(requestUser);
		sender.getFriends().trimToSize();
		currentUser.getFriends().trimToSize();
		
		currentUser.removeRequest(requestUser);
		currentUser.getRequests().trimToSize();
		
		System.out.println(username + " is now friends with " + Main.getLoggedInUser().getUsername());
		button.setVisible(false);
		Writer.writeUser(currentUser);
		Writer.writeUser(sender);
		
		requestsList.remove(requestUser);
		requestsList.trimToSize();
		gc.gridy--;
	}
}
