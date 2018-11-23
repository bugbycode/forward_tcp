package com.bugbycode.client;

import java.net.InetAddress;

import com.bugbycode.forward.socket.client.io.read.impl.AbstractForwardClientTcpRead;

public class SimpleForwardClientTcpRead extends AbstractForwardClientTcpRead {

	@Override
	public void readMessage(byte[] buff, int len) throws Exception {
		System.out.println(new String(buff,0,len));
	}

	@Override
	public void onClose(InetAddress address) {
		System.out.println("onClose()");
	}

}
