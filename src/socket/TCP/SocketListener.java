package socket.TCP;

import java.net.DatagramPacket;
import java.net.Socket;

public interface SocketListener {
	// called when the Socket has been created
	public void onCreate();

	// called when the loop has been started
	public void onStart();
	
	//called when a connection is established 
	public void onConnect(Socket socket);

	// called when a packet has been sent
	public void onSend(DatagramPacket packet);

	// called when a request to stop the Socket has been called
	public void onStopping();

	// called when the socket has been stopped successfully
	public void onStop();
}
