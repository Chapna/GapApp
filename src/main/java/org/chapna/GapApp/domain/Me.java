package org.chapna.GapApp.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.chapna.GapApp.Chat;
import org.chapna.GapApp.ChatFrame;
import org.chapna.GapApp.FrontFrame;

public class Me {

	private static Me instance;
	private HashMap<String, Chat> chatMap = new HashMap<String, Chat>();
	private String password;
	private User me;

	private Me(){

	}

	public static Me getInstance(){
		if (instance == null)
			instance = new Me();
		return instance;
	}

	void signIn(String pass) throws IOException{
	}

	public void signOut(){
	}

	void addToChat(Chat chat){
		chatMap.put(chat.getID(), chat);
	}

	HashMap<String, Chat> getChats(){
		return chatMap;
	}

	public ArrayList<Chat> getChatList(){
		return new ArrayList<Chat>(chatMap.values());
	}

	public void openFrame(Chat chat) throws IOException{
		chat.frame = new ChatFrame(this, chat);
		chat.frame.setVisible(true);
		chat.setFrameIsOpen(true);
	}

	public void openFront(){
		frame = new FrontFrame(this);
		frame.setVisible(true);
	}

	public void listener(String firstLine, String body) throws IOException{ // TODO

		String[] fLH = firstLine.split(" ");

		if (fLH[0].equals("send")) {

			String chatID = fLH[1];
			String senderID = fLH[2];

			if (chatMap.get(chatID) == null) {
				if (!(chatMap.get(senderID) == null))
					chatMap.get(senderID).writeMessageInFile(senderID, body);
			} else
				chatMap.get(chatID).writeMessageInFile(senderID, body);

		} else {
			if (fLH[0].equals("invite")) {
				String chatID = fLH[1];
				String senderID = fLH[2];

				// TODO
			} else if (fLH[0].equals("deliver")) {

			}
		}
	}

	public User getMe(){
		return me;
	}
}