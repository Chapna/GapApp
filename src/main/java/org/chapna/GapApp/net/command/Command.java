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

public class Command {
	private String verb;
	private String destID;
	private String srcID;
	private int size;
	private String data;

	public Command(String verb, String destID, String srcID, String data){
		this.verb = verb;
		this.destID = destID;
		this.srcID = srcID;
		this.data = data;

		if (data == null)
			size = 0;
		else
			size = data.length();
	}

	public String getVerb(){
		return verb;
	}

	public String getDestID(){
		return destID;
	}

	public String getSrcID(){
		return srcID;
	}


	public int getSize(){
		return size;
	}

	public String getData(){
		return data;
	}
}
