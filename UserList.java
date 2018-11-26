import java.util.ArrayList;

public class UserList {
	private final ArrayList<User> m_userList;


	public UserList() {
		m_userList = new ArrayList<User>();
	}
	
	public UserList(ArrayList<User> userList) {
		m_userList = userList;
	}
	
	public ArrayList<User> getUserList() {
		return m_userList;
	}

	public boolean add(User user) {
		for (User u : m_userList) {
			if (u.getUsername().equals(user.getUsername()))
				return false;
		}
		boolean result = m_userList.add(user);
		Writer.writeUserList(this);
		return result;
	}

	public boolean remove(User user) {
		for (int i = 1; i < m_userList.size(); i++) {
			if (m_userList.get(i).getUsername().equals(user.getUsername())) {
				m_userList.remove(i);
				return true;
			}
		}
		return false;
	}

	public int size() {
		return m_userList.size();
	}


	public User getLocalUser() {
		return Main.getLoggedInUser();
	}

	public User get(int index) {
		return m_userList.get(index);
	}

	public String getAllUsers() {
		String m_usernames = "";

		for (User u : m_userList)
			if (m_userList.indexOf(u) == m_userList.size()) {
				m_usernames += u.getUsername();
			} else {
				m_usernames += u.getUsername() + "\r\n";
			}

		return m_usernames;
	}
	
	public String[] getAllUsersArray() {
		String[] usersArray = new String[m_userList.size()];
		int i = 0;
		for (User u : m_userList) {
			usersArray[i] = u.getUsername();
			i++;
		}
		return usersArray;
	}
	
	public String[] getAllUsersExceptArray(String username) {
		String[] usersArray = new String[m_userList.size()];
		int i = 0;
		for (User u : m_userList) {
			if (u.getUsername() != username) {
				usersArray[i] = u.getUsername();
				i++;
			}
		}
		return usersArray;
	}
	
	public User getSearchedUserObject(String username){
		for (int i = 0; i < m_userList.size(); i++) {
			if (m_userList.get(i).getUsername().equalsIgnoreCase(username)) {
				return m_userList.get(i);
			}
		}
		return null; 
	}

}
