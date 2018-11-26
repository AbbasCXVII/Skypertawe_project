/**
 * User.java
 * This class contains all the information of the user that are stored in the system.
 * @version 1.0.0
 * CS-230
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
	
	private String m_username;
	private String m_userPass;
	private String m_fname;
	private String m_lname;
	private String m_profile_IMG;
	private String m_address;
	private String m_birthday;
	private String m_telephone;
	private ArrayList<String> m_friends = new ArrayList<String>();
	private ArrayList<String> m_requestsList = new ArrayList<String>();
	
	public User(String username, String userPass, String fname, 
			String lname, String profile_IMG, String address,
			String birthday, String telephone, ArrayList<String> friends,
			ArrayList<String> requestsList) {
		m_username = username;
		m_userPass = userPass;
		m_fname = fname;
		m_lname = lname;
		m_profile_IMG = profile_IMG;
		m_address = address;
		m_birthday = birthday;
		m_telephone = telephone;
		m_friends = friends;
		m_requestsList = requestsList;
	}
	
	public String getUsername() {
		return m_username;
	}
	public void setUserName(String username) {
		m_username = username;
	}
	public String getPassword() {
		return m_userPass;
	}
	public void setPassword(String userPass) {
		m_userPass = userPass;
	}
	public String getFname() {
		return m_fname;
	}
	public void setFname(String fname) {
		m_fname = fname;
	}
	public String getLname() {
		return m_lname;
	}
	public void setLname(String lname) {
		m_lname = lname;
	}
	public String getBirthday() {
		return m_birthday;
	}
	public void setBirthday(int day, int month, int year) {
		m_birthday = day + "/" + month + "/" + year;
	}

	public String getAddress() {
		return m_address;
	}
	public void setAddress(String street, String town, String county, String postcode) {
		m_address = street + " " + town +" "+ county +" "+ postcode;
	}
	public String getProfileImg() {
		return m_profile_IMG;
	}
	public void setProfileImg(String profile_IMG) {
		m_profile_IMG = profile_IMG;
	}
	public String getTelephone() {
		return m_telephone;
	}
	public void setTelephone(String telephone) {
		m_telephone = telephone;
	}
	public ArrayList<String> getFriends() {
		return m_friends;
	}
	public void setFriends(ArrayList<String> friends) {
		m_friends = friends;
	}
	public void addFriend(String user)
	{   
		m_friends.add(user);
		Writer.writeUser(this);
	}
	public ArrayList<String> getRequests() {
		return m_requestsList;
	}
	public void addRequest(String user) {
		m_requestsList.add(user);
		Writer.writeUser(this);
	}
	public void removeRequest(String user) {
		m_requestsList.remove(user);
		Writer.writeUser(this);
	}				
	public String[] getFriendsNames() {
		return m_friends.toArray(new String[m_friends.size()]);
	}
	
	public String[] getGroups() {
		ArrayList<String> groups = new ArrayList<String>();
		File directory = new File("users"+File.separator+m_username+
								  File.separator+"messages"+
								  File.separator+"groups");
		File[] directoryListing = directory.listFiles();
		if (directoryListing != null) {
			for (File group : directoryListing) {
				groups.add(group.getName().substring(0, group.getName().length()-4));
				String[] groupsArray = new String[groups.size()];
				groupsArray = groups.toArray(groupsArray);
				return groupsArray;
			}
		}
		System.out.println("Returning null");
		return null;
	}
	
	public static String makeUserPath(String username) {
		return "users"+File.separator+username+File.separator+"profile.txt";
	}
	public void removeFriend(String user)
	{   
		m_friends.remove(user);
		Writer.writeUser(this);
		File file = new File("users"+File.separator+this.m_username+File.separator+"messages"+File.separator+user+".txt");
		file.delete();
	}
}
