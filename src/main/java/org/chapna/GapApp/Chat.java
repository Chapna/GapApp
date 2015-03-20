package org.chapna.GapApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chat {

	private boolean frameIsOpen = false;
	int isThereNew = 0;
	ChatFrame frame;
	private String iD;
	private String name;
	private boolean isGroup;
	private HashMap<String, User> membMap;
	private ArrayList<Message> messages;

	public Chat(String ID, String name) {
		this.name = name;
		this.iD = ID;
		membMap = new HashMap<>();
	}

	public boolean isFrameIsOpen() {
		return frameIsOpen;
	}

	public void setFrameIsOpen(boolean frameIsOpen) {
		this.frameIsOpen = frameIsOpen;
	}

	public HashMap<String, User> getMembers() {
		return membMap;
	}

	public ArrayList<User> getMembersList() {
		return new ArrayList<User>(membMap.values());
	}

	public String getID() {
		return iD;
	}

	public void setID(String iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	void addToMembers(User user) {

		membMap.put(user.getID(), user);
	}

	void removeFromMembers(User user) {

		membMap.replace(user.getID(), user);
	}

	boolean isMember(User user) {

		if (membMap.containsValue(user))
			return true;
		return false;
	}

	void setIsGroup() {

		if (membMap.size() > 2)
			isGroup = true;
		else {
			if (membMap.size() == 2)
				isGroup = false;
			else {

			}
		}
	}

	boolean getIsGroup() {
		return isGroup;
	}

	public int getIsThereNew() {
		return isThereNew;
	}

	public void setIsThereNew(int isThereNew) {
		this.isThereNew = isThereNew;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	void writeMessageInFile(String senderID, String msg) throws IOException {
		File file = new File(iD);

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				file, true)));

		String newMsg = "{\n" + senderID + "\n" + msg + "\n}";

		pw.println(newMsg);
		pw.flush();
		pw.close();
		messages.add(new Message(senderID, msg));

		if (frameIsOpen) {
			frame.refresh(senderID, msg, this);
		} else {
			isThereNew++;
		}

	}

	void readFile() throws IOException {

		File file = new File(iD);
		if (!file.exists()) {
			file.createNewFile();
		}
		Scanner sc = new Scanner(file);
		messages = new ArrayList<Message>();

		while (sc.hasNext()) {

			if (sc.nextLine().equals("{")) {
				String senderID = sc.nextLine();
				String msg = "";
				String s = sc.nextLine();
				while (!s.equals("}")) {
					msg += s + "\n";
					s = sc.nextLine();
				}
				messages.add(new Message(senderID, msg));
			}
		}
		sc.close();
	}
}