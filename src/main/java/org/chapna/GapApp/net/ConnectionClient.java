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
import java.io.PrintWriter;
import java.net.Socket;
import org.chapna.GapApp.net.command.Command;
import org.chapna.GapApp.net.receiver.ConnectionReceiver;
import org.chapna.GapApp.net.receiver.Receiver;

public final class ConnectionClient {

	private static Socket socket;

	public static void init(String ip, int port){
		try {
			socket = new Socket(ip, port);
			Receiver receiver = new ConnectionReceiver();
			receiver.setSocket(socket);
			new Thread(receiver);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void send(Command cmd){
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.print(cmd.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
