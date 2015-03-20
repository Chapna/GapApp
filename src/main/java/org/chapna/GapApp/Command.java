package org.chapna.GapApp;

public abstract class Command {

	private Client sender;
	private String verb;
	private Chat receiver;

	public Chat getReceiver(){
		return receiver;
	}

	public void setReceiver(Chat receiver){
		this.receiver = receiver;
	}

	Client getSender(){
		return sender;
	}

	public void setSender(Client sender){
		this.sender = sender;
	}

	String getVerb(){
		return verb;
	}

	public void setVerb(String verb){
		this.verb = verb;
	}
}
