package client;

import java.util.ArrayList;

public class Chat { 
	
	private String ID;
	private String name;
	String isGroup;
	ArrayList<Client> members;

	private ChatFrame frame;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
