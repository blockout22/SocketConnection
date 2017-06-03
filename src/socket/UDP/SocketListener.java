package socket.UDP;

import java.net.DatagramPacket;

public interface SocketListener {
	
	//called when the Socket has been created
	public void onCreate();
	
	//called when the loop has been started
	public void onStart();
	
	//called when a packet have been recieved
	public void onRecieve(DatagramPacket packet);
	
	//called when a packet has been sent
	public void onSend(DatagramPacket packet);
	
	//called when a request to stop the Socket has been called
	public void onStopping();
	
	//called when the socket has been stopped successfully
	public void onStop();

}
