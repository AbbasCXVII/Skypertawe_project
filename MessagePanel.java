/**
 * @file MessagePanel.java
 * @date 10/12/16
 * 
 * Message Panel holds Editor Pane with users messages in
 * and buttons to send different types of messages + to enter text.
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class MessagePanel extends JPanel {
	private final int INTERVAL = 500;

	private JTextArea msgField;

	JEditorPane editorPane = new JEditorPane();

	public MessagePanel() {
		Dimension size = getPreferredSize();
		size.width = 440;
		setPreferredSize(size);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		setBackground(MainUI.getBackgroundColor());

		refreshMessagePane();

		//Refreshes page every X seconds
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				refreshMessagePane();
			}    
		});
		timer.start();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		
		//When user clicks hyperlink opens the URL of the hyperlink on desktop
		editorPane.addHyperlinkListener(new HyperlinkListener() {
		    public void hyperlinkUpdate(HyperlinkEvent e) {
		        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		        	if(Desktop.isDesktopSupported()) {
		        	    try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
		        }
		    }
		});
		
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(312, 340));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));

		msgField = new JTextArea(3, 3);
		msgField.setLineWrap(true);
		msgField.setWrapStyleWord(true);
		JScrollPane msgScrollPane = new JScrollPane(msgField);
		
		JButton urlBtn = new JButton("URL");
		urlBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String url = JOptionPane.showInputDialog("Enter a URL (Enter text in the normal message field)");
				  if (!url.isEmpty()) {
					  urlButtonPressed(url);
				  }
			  } 
		} );
		
		JButton fileBtn = new JButton("File/Image/Audio");
		fileBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  fileButtonPressed();
			  } 
		} );
		
		JButton	drawBtn = new JButton("Draw");
		drawBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Painter.main(null);
			}
		});

		JButton sendBtn = new JButton("Send");
		sendBtn.setBackground(Color.yellow);
		sendBtn.setOpaque(true);
		sendBtn.addActionListener(new ActionListener() { 

		public void actionPerformed(ActionEvent e) { 
		    sendButtonPressed();
		  } 
		} );
			
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Box messages = Box.createVerticalBox();
		messages.add(editorScrollPane);
		messages.add(msgScrollPane);
		Box btnBox = Box.createHorizontalBox();
		btnBox.add(urlBtn);
		btnBox.add(fileBtn);
		btnBox.add(drawBtn);
		btnBox.add(sendBtn);
		add(messages);
		add(btnBox);
	}
	
	private void fileButtonPressed() {
		JFileChooser fc = new JFileChooser();
		int returnValue = fc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();
			User currentUser = Main.getLoggedInUser();
			User selectedUser = null;
			if (UI_Frame.getSelectedUser() != null) {
				selectedUser = UI_Frame.getSelectedUser();
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(new Date());
				TextMessage msg = new TextMessage(selectedUser, currentUser, timeStamp, 
						"<a href='file:///" + filePath + "'>" + msgField.getText() + "</a>");
				msgField.setText("");
				Writer.writeTextMsg(msg);
			} 
	    }
	}
	
	private void urlButtonPressed(String url) {
		User currentUser = Main.getLoggedInUser();
		User selectedUser = null;
		if (UI_Frame.getSelectedUser() != null) {
			selectedUser = UI_Frame.getSelectedUser();
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(new Date());
			TextMessage msg = new TextMessage(selectedUser, currentUser, timeStamp, 
					"<a href='" + url + "'>" + msgField.getText() + "</a>");
			msgField.setText("");
			Writer.writeTextMsg(msg);
		} 

	}

	private void sendButtonPressed() {
		User currentUser = Main.getLoggedInUser();
		User selectedUser = null;
		if (UI_Frame.getSelectedUser() != null) {
			selectedUser = UI_Frame.getSelectedUser();
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(new Date());
			TextMessage msg = new TextMessage(selectedUser, currentUser, timeStamp, 
					msgField.getText());
			msgField.setText("");
			Writer.writeTextMsg(msg);
		}
	}

	private void refreshMessagePane() {
		User selectedUser = null;
		if (UI_Frame.getSelectedUser() != null) {
			selectedUser = UI_Frame.getSelectedUser();
		}
		if (selectedUser != null) {
			String selectedUsername = selectedUser.getUsername();
			User currentUser = Main.getLoggedInUser();
			String currentUsername = currentUser.getUsername();
			editorPane.setText("");
			File msgFile = new File("users"+File.separator+currentUsername+File.separator+"messages"+File.separator+selectedUsername+".txt");
			if (msgFile.exists()) {
				ArrayList<TextMessage> messageList = Reader.readMessages(msgFile.getPath());
				String message = "<html>";
				for (TextMessage msg : messageList) {
					String msgString = (msg.toString());
					message += (msgString + "<br>");
				}
				message += "</html>";
				editorPane.setText(message);
			}
		} else {
			editorPane.setText("");
			editorPane.revalidate();
			editorPane.repaint();
		}
	}
}