package org.chapna.GapApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.chapna.GapApp.domain.Me;

public class ReceiveThread extends Thread {

	private Me me;

	public ReceiveThread(Me user) {
		this.me = user;
	}

	public void run() {
		BufferedReader in;

		try {
			in = new BufferedReader(new InputStreamReader(
					me.serverSocket.getInputStream()));
			while (true) {

				int number = 0;
				String firstLine = in.readLine();
				System.out.println(firstLine); // TODO delete it after done with it
				String[] fLH = firstLine.split(" ");
				number = Integer.valueOf(fLH[3]);
				char[] charArr = new char[number];

				in.read(charArr, 0, number);

				String body = new String(charArr);

				me.listener(firstLine, body);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}