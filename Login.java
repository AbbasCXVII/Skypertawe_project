/**
 *@file Login.java
 *@date 10 Dec '16
 *\ Login class deals with checking if the user is valid and exists in the system before logging in.
 */
public class Login {
	String m_user;
	String m_pass;
	UserList userList = Main.getUserList();
	public Login(String username, String password){
		setUser(username);
		setPass(password); // converts the input of char[] to array.
	}
	
	// checks if the user exists.
	public boolean checkUserExists(){
		
		if(checkUserExists(getUser())){
			return true;
		}else{
			return false;
		}
		
	}
	
	// checks the password for the user.
	public boolean checkPassword(){
		
		if(getSearchedUserObject(getUser()).getPassword().equals(m_pass)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void setUser(String user){
		m_user = user;
	}
	
	public void setPass(String password){
		m_pass = password;
	}
	
	public String getUser(){
		return m_user;
	}
	
	public String getPassword(){
		return m_pass;
	}
	
	public boolean checkUserExists(String username){
		System.out.println(Main.getUserList().size());
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(username + " " + userList.get(i).getUsername());
			if (userList.get(i).getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;	
	}
	
	public User getSearchedUserObject(String username){
		
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(username)) {
				return userList.get(i);
			}
		}
		return null; 
	}
}
