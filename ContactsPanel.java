import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ContactsPanel extends JPanel {

	private int IMG_HEIGHT = 60;
	private int IMG_WIDTH = 200;
	public static JList contactsList;
	private User currentUser;

	public ContactsPanel() {
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		currentUser = Main.getLoggedInUser();

		setBorder(BorderFactory.createTitledBorder("Add new contact -"));
		setBackground(MainUI.getBackgroundColor());

		JLabel contactsLabel = new JLabel("Other contacts: ");
		
		JList<String> contactsList = new JList<String>(Main.getUserList().getAllUsersExceptArray(currentUser.getUsername()));
		contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contactsList.setLayoutOrientation(JList.VERTICAL);
		contactsList.setSelectedIndex(0);
		contactsList.setVisibleRowCount(3);
		JScrollPane listScroller = new JScrollPane(contactsList);
		listScroller.setViewportView(contactsList);
		listScroller.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(130, 250));
		listScroller.setMinimumSize(new Dimension(10, 10));
		
		JButton sendRequestBtn = new JButton("Send Request");
		sendRequestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Send button pressed");
				SendButtonPressed(contactsList.getSelectedValue());
			}
		});

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.5;

		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 0;
		add(contactsLabel, gc);

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 2;
		add(contactsList, gc);

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridy = 6;
		add(sendRequestBtn, gc);
	}
	
	private void SendButtonPressed(String username) {
		System.out.println(Main.getUserList().size());
		System.out.println(username);
		for (User u : Main.getUserList().getUserList()) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				u.addRequest(currentUser.getUsername());
				Writer.writeUser(u);	
				//Main.setUserList(Reader.readUserList());
				System.out.println(currentUser.getUsername() + " sent a request to " + u.getUsername());
			}
		}
	}


}
