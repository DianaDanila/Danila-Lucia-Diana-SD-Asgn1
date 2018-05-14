package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.Command;

public class Handler extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			while (true) {
				Command c = new ObjectMapper().readValue(in.readLine(), Command.class);
				out.println(new ObjectMapper().writeValueAsString(c.execute()));
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {

		}
	}
}
