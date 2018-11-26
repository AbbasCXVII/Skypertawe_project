import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class UI_Frame extends JFrame {
	private UI_DetailsPanel details;
	private MessagePanel messages;
	private static User selectedUser;
	
	public UI_Frame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		details = new UI_DetailsPanel();
		messages = new MessagePanel();
		
		Container c = getContentPane();
		c.add(details, BorderLayout.WEST);
		c.add(messages, BorderLayout.EAST);
		
	}
	
	public static User getSelectedUser() {
		return selectedUser;
	}
	
	public static void setSelectedUser(User user) {
		selectedUser = user;
	}
}