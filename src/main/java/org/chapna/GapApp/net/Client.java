/*
 * In The Name Of God
 * ======================================
 * [] Project Name : GapApp 
 * 
 * [] Package Name : org.chapna.GapApp.net
 *
 * [] Creation Date : 21-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package org.chapna.GapApp.net;

import java.io.IOException;
import java.net.Socket;
import org.chapna.GapApp.net.receiver.DefaultReceiver;
import org.chapna.GapApp.net.receiver.Receiver;

public final class Client {

	public static void init(String ip, int port){
		try {
			Socket socket = new Socket(ip, port);
			Receiver receiver = new DefaultReceiver();
			receiver.setSocket(socket);
			new Thread(receiver);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
