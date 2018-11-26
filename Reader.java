import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

class Reader {

	/**
	 * Reads user info from a text file and returns a User object
	 * @param path The path to the file where the user info is stored
	 * @return A User object
	 */
	public static User readUser(String path) {
		// Open file and scanner
		File file = null;
		Scanner in = null;
		try {
			file = new File(path);
			in = new Scanner(file);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		in.useDelimiter(",");	// Info is separated by commas
		// Get info
		String username = in.next();
		String password = in.next();
		String fname = in.next();
		String lname = in.next();
		String profileImg = in.next();
		String address = in.next();
		String birthday = in.next();
		String phone = in.next();
		ArrayList<String> friends = new ArrayList<String>();
		String friendLine = in.next();
		Scanner friendScanner = new Scanner(friendLine);
		String token;
		while (friendScanner.hasNext()) {
			token = friendScanner.next();
			if (friendLine.equalsIgnoreCase("null")) { // If the user has no friends
				break;								   // stop scanning
			} else {								   // otherwise
				friends.add(token);					   // add the friend's username
			}
		}
		ArrayList<String> requests = new ArrayList<String>();
		String reqLine = in.next();
		Scanner reqScanner = new Scanner(reqLine);
		while (reqScanner.hasNext()) {
			token = reqScanner.next();
			if (reqLine.equalsIgnoreCase("null")) { // If the user has no requests
				break;								// stop scanning
			} else {								// otherwise
				requests.add(token);				// add the request
			}
		}
		// Close scanners
		friendScanner.close();
		reqScanner.close();
		// return User
		return new User(username, password, fname, lname, 
						profileImg, address, birthday, phone, 
						friends, requests);
	}
	/**
	 * Reads the network.txt file and builds a UserList from it
	 * @return A UserList object
	 */
	public static UserList readUserList() {
		ArrayList<User> userList = new ArrayList<User>();
		// Open file and scanner
		File file = null;
		Scanner in = null;
		try {
			file = new File("network.txt");
			in = new Scanner(file);
		} catch (FileNotFoundException e) { 	// If network.txt file doesn't exist
			try {								// then
				file.createNewFile(); 			// create it
				file = new File("network.txt"); // and reopen file on it
			} catch (Exception d) {
				System.out.println(d);
			}
		}
		while (in.hasNextLine()) {	// While there is another username in network.txt
			File userFile = new File("users"+File.separator+
									 in.nextLine()+File.separator+
									 "profile.txt"); // Open the user's profile.txt
			if (userFile.exists()) {
				userList.add(readUser(userFile.getPath()));
			}
		}
		return new UserList(userList);
	}
	/**
	 * Reads all the messages in a text file
	 * @param path Path to the text file
	 * @return An ArrayList of TextMessages
	 */
	public static ArrayList<TextMessage> readMessages(String path) {
		ArrayList<TextMessage> messages = new ArrayList<TextMessage>();
		// Open file and scanner
		File file = null;
		Scanner in = null;
		String line = "";
		try {
			file = new File(path);
			in = new Scanner(file);
			in.useDelimiter(",");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		// Consume empty line at beginning of file
		in.nextLine();
		while (in.hasNextLine()) {
			line = in.nextLine();
			messages.add(readTextMessage(line));
		}
		return messages;
	}
	/**
	 * Creates a TextMessage object from a Strings
	 * @param line String containing all the information needed to create a TextMessage
	 * @return A TextMessage object
	 */
	public static TextMessage readTextMessage(String line) {
		Scanner in = new Scanner(line);
		in.useDelimiter(",");
		User recipient = readUser("users"+File.separator+in.next()+File.separator+"profile.txt");
		User sender = readUser("users"+File.separator+in.next()+File.separator+"profile.txt");
		String time = in.next();
		String message = in.next();
		in.close();
		return new TextMessage(recipient, sender, time, message);
	}
}