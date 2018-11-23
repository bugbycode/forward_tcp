package com.bugbycode.forward.socket.client.io.read.impl;

import java.net.InetAddress;

import com.bugbycode.forward.socket.client.io.read.ForwardClientTcpReadService;

public abstract class AbstractForwardClientTcpRead implements ForwardClientTcpReadService {

	@Override
	public void onConnection(InetAddress address) {
		
	}

	@Override
	public abstract void readMessage(byte[] buff, int len) throws Exception;

	@Override
	public void onClose(InetAddress address) {
		
	}

}
