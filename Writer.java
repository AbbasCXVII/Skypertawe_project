/**
 * @file Writer.java
 * @date 10 Dec 2016
 * 
 * Utility class that writes various text files used
 * in Skypertawe
 */
import java.io.*;

class Writer {
	/**
	 * Writes a string to a file
	 * @param line The text to be written to the file
	 * @param path The relative path to the file
	 */
	public static void writeToFile(String line, String path) {
		File file = new File(path);
		// try to create a PrintWriter on the file.
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.print(line);
			writer.close();
		// If the file doesn't exist create it (and all parent directories)
		} catch (FileNotFoundException e) {
			file.getParentFile().mkdirs(); // create the parent directories
			try {
				file.createNewFile(); // create the file itself
			} catch (IOException d) {
				System.out.println(e);
			}
			writeToFile(line, path); // try again now that the file DOES exist
		}
	}
	/**
	 * Appends the contents of a file with a string
	 * @param line The text to be appended to the file
	 * @param path The relative path to the file 
	 */
	public static void appendToFile(String line, String path) {
		File file = new File(path);
		// try to create a PrintWriter on the file.
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(file,true));
			writer.append(line);
			writer.close();
		// If the file doesn't exist create it (and all parent directories)
		} catch (FileNotFoundException e) {
			file.getParentFile().mkdirs(); // create the parent directories
			try {
				file.createNewFile(); // create the file itself
			} catch (IOException d) {
				System.out.println(e);
			}
			appendToFile(line, path); // try gain now that the file DOES exist
		}
	}
	/**
	 * Writes to a file the necessary information 
	 * for creating a User object
	 * @param user The user to be written.
	 * @see Reader.readUser()
	 */
	public static void writeUser(User user) {
		// Add all the user information to a string
		String line =	
				user.getUsername() + "," + user.getPassword() + "," + user.getFname()
			+ "," + user.getLname() + "," + user.getProfileImg() + "," + user.getAddress()
			+ "," + user.getBirthday() + "," + user.getTelephone()+",";
		if (user.getFriends().isEmpty()) { 	// If the user has no friends write null.
			line += "null,";				// This is used as a marker for readUser()
		} else {
			for (String u : user.getFriends()) {
				if (user.getFriends().indexOf(u) == user.getFriends().size()-1) {
					line += u+","; // If writing the last user in friends add a comma
				} else {
					line += u+" "; // Otherwise add a space
				}
			}
		}
		if (user.getRequests().isEmpty()) { // If the user has no friend requests write
			line += "null,";				// null. This is used as a marker for readUser()
		} else {
			for (String u : user.getRequests()) {
				if (user.getRequests().indexOf(u) == user.getRequests().size()-1) {
					line += u+","; // If writing the last user in requests add nothing
				} else {
					line += u+" "; // Otherwise add a space
				}
			}
		}
		String path = "users" + File.separator + user.getUsername() + 
						File.separator + "profile.txt"; // Path follows standard form
		writeToFile(line, path);						// for profile.txt locations
	}
	
	/**
	 * Rewrites network.txt with the contents of userList
	 * @param userList
	 */
	public static void writeUserList(UserList userList) {
		String line = userList.getAllUsers();
		String path = "network.txt";
		writeToFile(line, path);
	}
	/**
	 * Appends a textMessage to the appropriate file in a user's messages folder
	 * @param msg The message object to be written to file
	 */
	public static void writeTextMsg(TextMessage msg) {
		String sender = msg.getSender().getUsername();// Get the sender's username
		String recipient = msg.getRecipient().getUsername();// Get the recipient's username
		String line ="\r\n"+recipient+","+sender+","+		// Get all the needed to create
					 msg.getDateTime()+","+msg.getMessage();// a textMessage object
		appendToFile(line, msg.makeMessagePath()); // Append line to the appropriate txt file
		appendToFile(line, "users"+File.separator+sender+ // Append the line to the user
					 File.separator+"messages"+File.separator+
					 recipient+".txt");
	}
}
