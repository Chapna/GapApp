package org.chapna.GapApp.instructions;

import java.io.IOException;
import org.chapna.GapApp.Chat;
import org.chapna.GapApp.Client;

public class MessageCmd extends Command {

	private String data;
	
	public MessageCmd(Client sender, String data,Chat dest) throws IOException {
		setReceiver(dest);
		setSender(sender);
		this.data = data;
		setVerb("send");
		sender.sendToServer(this);
	}

	String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}