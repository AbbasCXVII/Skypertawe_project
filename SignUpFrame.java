/**
 * @file SignUpFrame.java
 * @date 06/12/16
 * 
 * SignUp Frame, swing frame holding sign up details panel.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SignUpFrame extends JFrame {
	private DetailsPanel details;
	
	public SignUpFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		details = new DetailsPanel();
		
		Container c = getContentPane();
		c.add(details, BorderLayout.CENTER);
		
	}
}
