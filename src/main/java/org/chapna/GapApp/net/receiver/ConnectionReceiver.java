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

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.chapna.GapApp.net.command.Command;
import org.chapna.GapApp.net.command.ConnectionCommand;

public class ConnectionReceiver implements Receiver {
	private Socket socket;
	private List<ConnectionCommand> commands;

	public ConnectionReceiver(){
		commands = new ArrayList<ConnectionCommand>();
	}

	@Override
	public Command getCommand(){
		return commands.remove(0);
	}

	@Override
	public void setSocket(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run(){
		Scanner scanner;
		while (true) {
			try {
				scanner = new Scanner(socket.getInputStream());
				String verb = scanner.next();
				String destID = scanner.next();
				String srcID = scanner.next();
				int size = scanner.nextInt();

				scanner.nextByte();

				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < size; i++) {
					buffer.append(scanner.nextByte());
				}

				commands.add(new ConnectionCommand(verb, destID, srcID, buffer.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
