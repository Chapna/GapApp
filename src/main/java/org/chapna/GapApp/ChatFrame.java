package org.chapna.GapApp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.chapna.GapApp.domain.Me;
import org.chapna.GapApp.domain.User;

public class ChatFrame extends JFrame {


	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private Chat chat;

	/**
	 * Create the frame.
	 *
	 * @throws IOException
	 */
	public ChatFrame(final Me sender, final Chat chat) throws IOException{
		this.chat = chat;
		setTitle(chat.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.out.println(chat.getName() + " frame closed!!");
				chat.setFrameIsOpen(false);
				//e.getWindow().dispose();
			}
		});

		final JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Monaco", Font.PLAIN, 15));
		editorPane.setBounds(10, 299, 488, 85);
		contentPane.add(editorPane);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 595, 277);
		contentPane.add(scrollPane);

		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		// actions after opening:
		chat.readFile();
		String toBeAdded = "";
		String name = "";
		if (chat.isThereNew > 0) {
			textPane.setText("NewMessages:");
			for (int i = chat.getMessages().size() - chat.isThereNew; i < chat.getMessages().size(); i++) {
				if (chat.getMessages().get(i).senderID.equals(sender.getID())) {
					name = "ME";
				} else {
					name = chat.getMembers().get(chat.getMessages().get(i).senderID).getName();
				}
				toBeAdded = toBeAdded + name + ": " + chat.getMessages().get(i).getMsg() + "\n";
			}
			textPane.setText(textPane.getText() + toBeAdded);
		} else if (chat.getMessages().size() >= 20) {// if there is no new message, We'll show last 20 messages:
			for (int i = chat.getMessages().size() - 20; i < chat.getMessages().size(); i++) {
				if (chat.getMessages().get(i).senderID.equals(sender.getID())) {
					name = "ME";
				} else {
					name = chat.getMembers().get(chat.getMessages().get(i).senderID).getName();
				}
				toBeAdded = toBeAdded + name + ": " + chat.getMessages().get(i).getMsg() + "\n";
			}
			textPane.setText(textPane.getText() + toBeAdded);
		} else {
			for (int i = 0; i < chat.getMessages().size(); i++) {
				if (chat.getMessages().get(i).senderID.equals(sender.getID())) {
					name = "ME";
				} else {
					name = chat.getMembers().get(chat.getMessages().get(i).senderID).getName();
				}
				toBeAdded = toBeAdded + name + ": " + chat.getMessages().get(i).getMsg() + "\n";
			}
			textPane.setText(textPane.getText() + toBeAdded);

		}
		// end
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String text = editorPane.getText();
				editorPane.setText("");
				//textPane.setText(textPane.getText() + "ME: " + text + "\n");
				// writes the sent message to message list of its chat:
				try {
					chat.writeMessageInFile(sender.getID(), text);
					chat.getMessages().add(new Message(sender.getID(), text));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					new MessageCmd(sender, text, chat);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		sendButton.setBounds(508, 348, 97, 36);
		contentPane.add(sendButton);

		JButton btnNewButton = new JButton("More");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
			}
		});
		btnNewButton.setBounds(508, 317, 97, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Invite");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
			}
		});
		btnNewButton_1.setBounds(508, 293, 97, 23);
		contentPane.add(btnNewButton_1);

		getRootPane().setDefaultButton(sendButton);

	}

	void refresh(String senderId, String msg, Chat chat){
		String toBeShowed = "";
		for (User u : chat.getMembers().values()) {
			if (u.getID().equals(senderId)) {
				toBeShowed = toBeShowed.concat(u.getName()).concat(": ").concat(msg).concat("\n");
				textPane.setText(textPane.getText() + toBeShowed);
				break;
			}
		}
	}
}
