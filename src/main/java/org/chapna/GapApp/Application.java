package org.chapna.GapApp;

import java.io.IOException;
import java.net.Socket;
import org.chapna.GapApp.domain.Me;
import org.chapna.GapApp.domain.User;
import org.chapna.GapApp.net.Client;

public class Application {


	public static void main(String[] args) throws IOException{
		Client.init("127.0.0.1", 1373);
		Me me = new Me("mrma95", "chapal", new Socket("127.0.0.1", 1373));
		me.signIn("pass");
		User user = new User("navid", "msk");
		Chat chat = new Chat(user.getID(), user.getName());
		me.addToChat(chat);
		chat.addToMembers(user);
		chat.addToMembers(me);
		me.openFront();
	}
}