package org.chapna.GapApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrontFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public FrontFrame(Client user){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle(user.name);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.out.println(user.getName() + " front frame closed!!");
				user.signOut();
			}
		});

		textField = new JTextField();
		textField.setBounds(10, 11, 313, 36);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSrch = new JButton("search");
		btnSrch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
			}
		});
		btnSrch.setBounds(335, 11, 89, 36);
		contentPane.add(btnSrch);

		Vector<String> listV = new Vector<String>();
		for (int i = 0; i < user.getChatList().size(); i++) {
			listV.add(user.getChatList().get(i).getName());
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 313, 192);
		contentPane.add(scrollPane);

		JList<String> list = new JList<String>(listV);
		scrollPane.setViewportView(list);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				int index = list.getSelectedIndex();
				try {
					user.openFrame(user.getChatList().get(index));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSelect.setBounds(335, 58, 89, 192);
		contentPane.add(btnSelect);


	}
}
