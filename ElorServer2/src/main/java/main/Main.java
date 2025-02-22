package main;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import socketIO.SocketIOModule;

public class Main {
	private static final String HOST_NAME = "0.0.0.0";
	private static final int PORT = 3000;
	public static void main(String[] args) {
	
				Configuration config = new Configuration();
				config.setHostname(HOST_NAME);
				config.setPort(PORT);

				// Arrancamos el servidor
				SocketIOServer server = new SocketIOServer(config);
				SocketIOModule module = new SocketIOModule(server);
				module.start();
	}

}
