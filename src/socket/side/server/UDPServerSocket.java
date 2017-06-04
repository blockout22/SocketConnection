package socket.side.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.UUID;

import socket.UDP.SocketListener;

public class UDPServerSocket {
	
	//used to end the while loop without triggering onRecieve();
	private UUID closePacket;
	
	private ServerArguments args;
	private SocketListener listener;
	private DatagramSocket socket;
	private boolean running = false;
	
	public UDPServerSocket(ServerArguments serverArgs, SocketListener listener) throws SocketException{
		this.args = serverArgs;
		socket = new DatagramSocket(args.getPort());
		this.listener = listener;
		running = true;
		
		listener.onCreate();
		
		closePacket = UUID.randomUUID();
	}
	
	public void start() throws IOException
	{
		listener.onStart();
		while(running){
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
	
	public void send(byte[] data, InetAddress ip, int port) throws IOException{
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		socket.send(packet);
		listener.onSend(packet);
	}
	
	public void stop() throws IOException
	{
		listener.onStopping();
		running = false;
		byte[] buffer = closePacket.toString().getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(""), args.getPort());
		socket.send(packet);
	}

}
