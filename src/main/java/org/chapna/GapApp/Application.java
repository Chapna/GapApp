package org.chapna.GapApp;

import java.io.IOException;
import java.net.Socket;

public class Application {

	public static void main(String[] args) throws IOException{

		Client me = new Client("mrma95", "chapal", new Socket("192.168.169.199", 1373));
		me.signIn("pass");
		User user = new User("navid", "msk");
		Chat chat = new Chat(user.getID(), user.getName());
		me.addToChat(chat);
		chat.addToMembers(user);
		chat.addToMembers(me);
		me.openFront();
	}
}