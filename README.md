# SocketConnection [![Build Status](https://travis-ci.org/blockout22/SocketConnection.svg?branch=master)](https://travis-ci.org/blockout22/SocketConnection)
# SocketConnection 

simple Socket setup to start communicating with sockets

# example [UDP Server]

```
  ServerArguments serverArgs = new ServerArguments(8080, 1024);
  SocketListener listener = new SocketListener(){};
  
  UDPServerSocket socket = new UDPServerSocket(serverArgs, listener);
  socket.start(); // listeners for packets and loops forever until stop() is called
  
  ...
  
  socket.stop();
```
