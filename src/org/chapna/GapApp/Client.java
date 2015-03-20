package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Client extends User {

	private FrontFrame frame;
	static int count = 0;
	Socket serverSocket;
	private boolean signIn;
	ReceiveThread rt;

	HashMap<String, Chat> chatMap = new HashMap<>();

	Client(String name, String ID, Socket serverSocket) {
		super(name, ID);
		if (count == 0) {
			this.serverSocket = serverSocket;
			rt = new ReceiveThread(this);
			rt.start();
			signIn = false;
			count++;
		}
	}

	void signIn(String pass) throws IOException {
		signIn = true;

		SignIn si = new SignIn(pass, this);
		sendToServer(si);
	}

	void signOut() {
		signIn = false;
		// TODO
	}

	boolean getSignIn() {
		return signIn;
	}

	public FrontFrame getFrame() {
		return frame;
	}

	public void setFrame(FrontFrame frame) {
		this.frame = frame;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	void addToChat(Chat chat) {
		chatMap.put(chat.getID(), chat);
	}

	HashMap<String, Chat> getChats() {
		return chatMap;
	}

	ArrayList<Chat> getChatList() {
		return new ArrayList<Chat>(chatMap.values());
	}

	public void openFrame(Chat chat) throws IOException {
		chat.frame = new ChatFrame(this, chat);
		chat.frame.setVisible(true);
		chat.setFrameIsOpen(true);
	}

	public void openFront() {
		frame = new FrontFrame(this);
		frame.setVisible(true);
	}

	public void sendToServer(Command cmd) throws IOException {

		String msg = "";

		if (cmd.getVerb().equals("send")) {
			msg = cmd.getVerb() + " " + cmd.getReceiver().getID() + " " + ID
					+ " " + ((MessageCmd) cmd).getData().getBytes().length
					+ "\n" + ((MessageCmd) cmd).getData();
		} else {
			if (cmd.getVerb().equals("signin")) {
				msg = cmd.getVerb() + " " + cmd.getSender().getID() + " "
						+ ((SignIn) cmd).getPass() + " 0\n";
			} else {
				msg = cmd.getVerb() + " " + cmd.getReceiver().getID() + " "
						+ ((LeJIn) cmd).getUser().getID() + " 0"; // TODO send
																	// senderID
																	// to
																	// server
			}
		}

		PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
		out.println(msg);
		out.flush();
	}

	void listener(String firstLine, String body) throws IOException { // TODO
																		// handle
																		// close
																		// verb

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
}