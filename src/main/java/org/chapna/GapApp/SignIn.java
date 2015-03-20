package org.chapna.GapApp;

import java.io.IOException;

public class SignIn extends Command {

	private String pass;

	public SignIn(String pass, Client client) throws IOException{
		if (client.getSignIn()) {
			setVerb("signin");
		} else {
			setVerb("signout");
		}
		this.pass = pass;
		setSender(client);
	}

	public String getPass(){
		String pass = this.pass;
		this.pass = null;
		return pass;
	}
}
