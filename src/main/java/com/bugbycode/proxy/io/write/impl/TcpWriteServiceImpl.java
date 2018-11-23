package com.bugbycode.proxy.io.write.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.write.TcpWriteService;
import com.util.TransferUtil;

public class TcpWriteServiceImpl implements TcpWriteService {

	private OutputStream out;
	
	private TransferUtil util;
	
	public TcpWriteServiceImpl() {
		this.util = new TransferUtil();
	}
	
	@Override
	final public void write(Message message) throws IOException {
		int type = message.getType();
		String token = message.getToken();
		Object obj = message.getData();
		String body = obj == null ? "" : obj.toString();
		byte[] data = body.getBytes();
		out.write(util.toHH(data.length));
		out.write(type);
		out.write(token.getBytes());
		out.write(data);
		out.flush();
	}

	final public void setOut(OutputStream out) {
		this.out = out;
	}
	
	final public void close() {
		try {
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
