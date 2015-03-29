/*
 * In The Name Of God
 * ======================================
 * [] Project Name : GapApp 
 * 
 * [] Package Name : org.chapna.GapApp.net.command
 *
 * [] Creation Date : 21-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package org.chapna.GapApp.net.command;

public abstract class Command {
	private String verb;
	private String destID;
	private String srcID;

	public Command(String verb, String destID, String srcID){
		this.verb = verb;
		this.destID = destID;
		this.srcID = srcID;
	}

	abstract public String toString();

	public String getVerb(){
		return verb;
	}

	public String getDestID(){
		return destID;
	}

	public String getSrcID(){
		return srcID;
	}
}
