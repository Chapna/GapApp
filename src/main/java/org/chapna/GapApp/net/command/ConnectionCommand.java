/*
 * In The Name Of God
 * ======================================
 * [] Project Name : GapApp 
 * 
 * [] Package Name : org.chapna.GapApp.net.command
 *
 * [] Creation Date : 30-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package org.chapna.GapApp.net.command;

public class ConnectionCommand extends Command {
	private int size;
	private String data;

	public ConnectionCommand(String verb, String destID, String srcID, String data){
		super(verb, destID, srcID);
		this.data = data;

		if (data == null)
			size = 0;
		else
			size = data.length();
	}

	@Override
	public String toString(){
		return getVerb() + " " + getDestID() + " " + getSrcID() + " " + size + "\n" + data;
	}

	public int getSize(){
		return size;
	}

	public String getData(){
		return data;
	}

}
