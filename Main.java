/**
 * @file Main.java
 * @date 07/12/16
 * Main method to execute program and run frames in order.
 * Also holds gobal system variables.
 */

import java.util.Timer;

public class Main {
	//User that is logged in to Skypertawe
	private static User loggedUser;
	private static UserList userList;
	private static int INTERVAL = 500;

	public static void main(String[] args) {
		userList = Reader.readUserList();
		//Error above here
		
		MainUI mainUI = new MainUI();
		mainUI.OpenGUI();
		Timer timer = new Timer();
		timer.schedule(new UpdateNetwork(), 0, INTERVAL);
	}
	
	public static User getLoggedInUser() {
		return loggedUser;
	}
	
	public static void setLoggedInUser(User user) {
		loggedUser = user;
	}
	
	public static UserList getUserList() {
		return userList;
	}
	
	public static void setUserList(UserList list) {
		userList = list;
	}
	
}
