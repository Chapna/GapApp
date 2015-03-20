package org.chapna.GapApp.instructions;

import java.io.IOException;
import org.chapna.GapApp.Chat;
import org.chapna.GapApp.Client;
import org.chapna.GapApp.User;

public class LeJIn extends Command {

	private User toBeAdded;

	public LeJIn(User toBeAdded, Client sender, Chat dest) throws IOException { // here
																				// receiver
																				// is
																				// destination
																				// group
		setReceiver(dest);
		setSender(sender);
		this.toBeAdded = toBeAdded;

		if (sender.getID().equals(toBeAdded.getID())) {
			if (dest.isMember(toBeAdded))
				setVerb("leave");
			else
				setVerb("join");

			sender.sendToServer(this);
		} else {
			if (!dest.isMember(toBeAdded)) {
				setVerb("add");
				sender.sendToServer(this);
			}
		}
	}

	protected User getUser() {
		return toBeAdded;
	}

	protected void setUser(User user) {
		this.toBeAdded = user;
	}

}
