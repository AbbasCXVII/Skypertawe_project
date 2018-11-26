/**
*@file ContactsFrame.java
*@date 10 Dec '16
*
*This class just displays the panel
*/

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class ContactsFrame extends JFrame {
	private ContactsPanel contacts;

	public ContactsFrame(String title) {
		super(title);

		setLayout(new BorderLayout());

		contacts = new ContactsPanel();
		Container c = getContentPane();
		c.add(contacts, BorderLayout.CENTER);
	}
}
