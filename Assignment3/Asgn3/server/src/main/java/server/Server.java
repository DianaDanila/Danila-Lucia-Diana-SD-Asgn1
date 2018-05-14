package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private List<Handler> h;
	private ServerSocket listener;

	public Server() throws IOException {
		h = new ArrayList<Handler>();
		listener = new ServerSocket(9090);
	}

	public void serve() throws IOException {
		while (true) {
			Socket s = listener.accept();
			Handler newh = new Handler(s);
			h.add(newh);
			newh.start();
		}
	}

}
