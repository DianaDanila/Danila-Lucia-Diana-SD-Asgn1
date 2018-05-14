package server;

import java.io.IOException;

public class MainServer {

	public static void main(String[] args) {

		try {
			Server s = new Server();
			s.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
