/**
 * @file UpdateNetwork.java
 * @date 10/12/16
 * 
 * Updates the global userlist held in main on a timer.
 */

import java.util.TimerTask;

public class UpdateNetwork extends TimerTask {
	public void run() {
		Main.setUserList(Reader.readUserList());
	}
}
