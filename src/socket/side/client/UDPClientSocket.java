package socket.side.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import socket.UDP.SocketListener;

public class UDPClientSocket {
	
	private ClientArguments args;
	private SocketListener listener;
	private DatagramSocket socket;
	private boolean running = false;
	private int localPort = -1;
	
	public UDPClientSocket(ClientArguments clientArgs, SocketListener listener) throws SocketException{
		socket = new DatagramSocket();
		localPort = socket.getLocalPort();
		this.args = clientArgs;
		this.listener = listener;
		running = true;
		
		listener.onCreate();
	}
	
	public void start() throws IOException
	{
		listener.onStart();
		
		while(running){
			byte[] buffer = new byte[args.getBufferSize()];
			DatagramPacket dataPacket = new DatagramPacket(buffer, buffer.length);
			
			socket.receive(dataPacket);
			
			listener.onRecieve(dataPacket);
		}
		
		socket.close();
		listener.onStop();
	}
	
	public void send(byte[] data) throws IOException{
		DatagramPacket packet = new DatagramPacket(data, data.length, args.getIp(), args.getPort());
		socket.send(packet);
		listener.onSend(packet);
	}
	
	public void stop() throws IOException
	{
		listener.onStopping();
		running = false;
		byte[] buffer = "".getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(""), localPort);
		socket.send(packet);
	}

}
