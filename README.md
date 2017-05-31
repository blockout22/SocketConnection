# SocketConnection

simple Socket setup to start communicating with sockets

# example [UDP Server]

```
  ServerArguments serverArgs = new ServerArguments(8080, 1024);
  SocketListener listener = new SocketListener(){};
  
  UDPServerSocket socket = new UDPServerSocket(serverArgs, listener);
```