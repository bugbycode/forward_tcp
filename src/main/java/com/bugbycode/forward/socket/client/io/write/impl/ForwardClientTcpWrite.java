package com.bugbycode.forward.socket.client.io.write.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.bugbycode.forward.socket.client.io.write.ForwardClientTcpWriteService;

public class ForwardClientTcpWrite implements ForwardClientTcpWriteService {

	private OutputStream out;
	
	@Override
	final public void setOut(OutputStream out) {
		this.out = out;
	}

	@Override
	final public void write(byte[] buff, int len) throws IOException {
		out.write(buff, 0, len);
		out.flush();
	}

}
