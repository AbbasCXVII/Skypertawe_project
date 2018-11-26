/**
 * @file Message.java
 * @date 01/12/16
 * 
 * Message class to hold message objects, child TextMessage
 */

import java.io.File;

public class Message {

	private User m_recipient;
	private User m_sender;
	private String m_dateTime;
	
	public String getDateTime() {
		return m_dateTime;
	}

	public void setDateTime(String dateTime) {
		this.m_dateTime = dateTime;
	}

	public User getRecipient() {
		return m_recipient;
	}

	public void setRecipient(User recipient) {
		this.m_recipient = recipient;
	}

	public User getSender() {
		return m_sender;
	}

	public void setSender(User sender) {
		this.m_sender = sender;
	}
	
	public String makeMessagePath() {
		//String recipient = getRecipient().getUsername();
		//String sender = getSender().getUsername();
		String path =	"users" + File.separator + 
						m_recipient.getUsername() + File.separator +
						"messages" + File.separator + 
						m_sender.getUsername() + ".txt";
		return path;
	}
	
	public Message(User recipient, User sender, String dateTime) {
		m_recipient = recipient;
		m_sender = sender;
		m_dateTime = dateTime;
	}
	
}
