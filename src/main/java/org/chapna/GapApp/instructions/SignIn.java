package org.chapna.GapApp.instructions;

public class SignIn implements Instruction {

	private String username;
	private String password;

	public SignIn(String pass, String user){
		password = pass;
		username = user;
	}

	@Override
	public void run(){
	}
}
