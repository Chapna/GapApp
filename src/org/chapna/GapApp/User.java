package client;

public class User {

	String name;
	String ID;
	
	User(String name, String ID) {
	
		this.name = name;
		this.ID = ID;
	}
	
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getID() {
		return ID;
	}
}