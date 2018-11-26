/**
 * @file TextMessage.java
 * @date 01/12/16
 * 
 * Text Message class for storing text messages.
 */

public class TextMessage extends Message {
	
	//Needs user class to fully implement.
	
	private String m_message;

	public String getMessage() {
		return m_message;
	}

	public void setMessage(String m_message) {
		this.m_message = m_message;
	}
	
	@Override
	public String toString() {
		String time = getDateTime().substring(11);
		return "<i> ~"+time+"~</i> <b>"+getSender().getFname()+":</b> "+m_message;
	}
	
	public TextMessage(User recipient, User sender, String dateTime, String message) {
		super(recipient, sender, dateTime);
		m_message = message;
	}
	
}
