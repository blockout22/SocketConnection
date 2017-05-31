package socket.side.server;

public class ServerArguments {
	private int port;
	private int bufferSize;

	public ServerArguments(int port, int bufferSize) {
		this.port = port;
		this.bufferSize = bufferSize;
	}

	public void setBufferSize(int size) {
		this.bufferSize = size;
	}

	public int getPort() {
		return port;
	}

	public int getBufferSize() {
		return bufferSize;
	}
}
