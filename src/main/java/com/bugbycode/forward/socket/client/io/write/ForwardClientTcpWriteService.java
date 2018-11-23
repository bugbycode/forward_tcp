package com.bugbycode.forward.socket.client.io.write;

import java.io.IOException;
import java.io.OutputStream;

public interface ForwardClientTcpWriteService {

	public void setOut(OutputStream out);
	
	public void write(byte[] buff,int len) throws IOException;
}
