package org.chapna.GapApp;

public class User {

	private String name;
	private String ID;

	User(String name, String ID){
		this.name = name;
		this.ID = ID;
	}

	String getName(){
		return name;
	}

	String getID(){
		return ID;
	}
}