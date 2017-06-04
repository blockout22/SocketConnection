package socket.side.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.UUID;

import socket.UDP.SocketListener;

public class UDPClientSocket {

	// used to end the while loop without triggering onRecieve();
	private UUID closePacket;

	private ClientArguments args;
	private SocketListener listener;
	private DatagramSocket socket;
	private boolean running = false;
	private int localPort = -1;

	public UDPClientSocket(ClientArguments clientArgs, SocketListener listener) throws SocketException {
		socket = new DatagramSocket();
		localPort = socket.getLocalPort();
		this.args = clientArgs;
		this.listener = listener;
		running = true;

		listener.onCreate();
		
		closePacket = UUID.randomUUID();
	}

	public void start() throws IOException {
		listener.onStart();

		while (running) {
			byte[] buffer = new byte[args.getBufferSize()];
			DatagramPacket dataPacket = new DatagramPacket(buffer, buffer.length);

			socket.receive(dataPacket);
			
			if(new String(dataPacket.getData()).trim().equals(closePacket.toString())){
				break;
			}
			
			listener.onRecieve(dataPacket);
		}

		socket.close();
		listener.onStop();
	}

	public void send(byte[] data) throws IOException {
		DatagramPacket packet = new DatagramPacket(data, data.length, args.getIp(), args.getPort());
		socket.send(packet);
		listener.onSend(packet);
	}

	public void stop() throws IOException {
		listener.onStopping();
		running = false;
		byte[] buffer = closePacket.toString().getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(""), localPort);
		socket.send(packet);
	}

}
