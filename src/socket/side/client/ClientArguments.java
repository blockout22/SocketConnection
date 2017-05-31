package socket.side.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientArguments {

	private InetAddress ip;
	private int port;
	private int bufferSize;

	public ClientArguments(String ip, int port, int bufferSize) throws UnknownHostException {
		this(InetAddress.getByName(ip), port, bufferSize);
	}
	
	public ClientArguments(InetAddress ip, int port, int bufferSize) {
		this.ip = ip;
		this.port = port;
		this.bufferSize = bufferSize;
	}
	
	public void setBufferSize(int size) {
		this.bufferSize = size;
	}

	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public int getBufferSize() {
		return bufferSize;
	}

}
