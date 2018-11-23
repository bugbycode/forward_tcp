package com.bugbycode.proxy.io.write;

import java.io.IOException;
import java.io.OutputStream;

import com.bugbycode.module.Message;

public interface TcpWriteService {
	
	public void write(Message message) throws IOException;
	
	public void setOut(OutputStream out);
}
