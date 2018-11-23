package com.bugbycode.forward.socket.client.io.read;

import java.net.InetAddress;

public interface ForwardClientTcpReadService {

	public void onConnection(InetAddress address);
	
	public void readMessage(byte[] buff,int len) throws Exception;
	
	public void onClose(InetAddress address);
}
