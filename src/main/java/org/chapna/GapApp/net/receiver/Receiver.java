/*
 * In The Name Of God
 * ======================================
 * [] Project Name : GapApp 
 * 
 * [] Package Name : org.chapna.GapApp.net.receiver
 *
 * [] Creation Date : 21-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package org.chapna.GapApp.net.receiver;

import java.net.Socket;
import org.chapna.GapApp.net.command.Command;

public interface Receiver extends Runnable {
	public Command getCommand();

	public void setSocket(Socket socket);
}
