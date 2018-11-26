/**
*@file LoginFrame
*@date 10 Dec '16
*
*This class just displays the panel
*/
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class LoginFrame extends JFrame {
	private LoginPanel login;

	public LoginFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		login = new LoginPanel();
		Container c = getContentPane();
		c.add(login, BorderLayout.CENTER);
	}
}
