package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import command.Command;
import command.Response;

public class Client extends Thread {
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;

	private static Queue<Command> inCommand;
	private static Queue<Response> outResponse;

	private static Semaphore sem = new Semaphore(0);

	public Client(Socket s) {
		this.s = s;
		try {
			this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.out = new PrintWriter(s.getOutputStream());
			inCommand = new LinkedBlockingQueue<Command>();
			outResponse = new LinkedBlockingQueue<Response>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addMessage(Command input) {
		inCommand.add(input);
	}

	public static Response getResponse() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Response r = outResponse.poll();
		return r;

	}

	@Override
	public void run() {
		while (true) {
			if (!inCommand.isEmpty()) {

				Command c = inCommand.poll();
				try {
					out.println(new ObjectMapper().writeValueAsString(c));
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}

				out.flush();

				String readnow;
				try {

					readnow = in.readLine();
					Response r = new ObjectMapper().readValue(readnow, Response.class);

					outResponse.add(r);
					sem.release();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
