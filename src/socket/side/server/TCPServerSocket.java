package socket.side.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import socket.TCP.SocketListener;

public class TCPServerSocket {

	private ServerSocket socket;
	private ServerArguments args;
	private SocketListener listener;
	private boolean running = false;

	public TCPServerSocket(ServerArguments serverArgs, SocketListener listener) throws IOException {
		this.args = serverArgs;
		socket = new ServerSocket(args.getPort());
		this.listener = listener;
		running = true;

		listener.onCreate();
	}

	public void start() throws IOException {
		listener.onStart();
		while (running) {
			Socket clientSocket = socket.accept();
			listener.onConnect(clientSocket);
		}
	}
	
	public void stop() throws IOException
	{
		listener.onStopping();
		socket.close();
		listener.onStop();
	}

}
