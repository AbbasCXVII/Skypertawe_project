/**
 * @file ProfileFrame.java
 * @date 07/12/16
 * 
 * JFrame that holds the user profile information panels.
 */

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class ProfileFrame extends JFrame {
	private ProfilePanel profile;
	private RequestsPanel requests;

	public ProfileFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		profile = new ProfilePanel();
		requests = new RequestsPanel();
		Container c = getContentPane();
		c.add(profile, BorderLayout.WEST);
		c.add(requests, BorderLayout.EAST);
	}
}
