package client;

public class Message {

	String senderID;
	String msg;
	
	Message(String senderID, String msg) {
		
		this.senderID = senderID;
		this.msg = msg;
	}

	protected String getSenderID() {
		return senderID;
	}

	protected String getMsg() {
		return msg;
	}
}