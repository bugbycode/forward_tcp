package com.bugbycode.proxy.socket.thread.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.bugbycode.initializer.ListenerService;
import com.bugbycode.initializer.TcpServerInitializer;
import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.read.TcpReadService;
import com.bugbycode.proxy.io.write.TcpWriteService;
import com.util.TransferUtil;

public class TcpThread implements Runnable {

	private TcpReadService tcpReadService;
	
	private TcpWriteService tcpWriteService;
	
	private TcpServerInitializer initializer;
	
	private TransferUtil util;
	
	private Socket client;
	
	private InputStream in = null;
	
	private OutputStream out = null;
	
	public TcpThread(Socket client,TcpServerInitializer initializer) {
		this.client = client;
		this.tcpReadService = initializer.getTcpReadService();
		this.tcpWriteService = initializer.getTcpWriteService();
		this.initializer = initializer;
	}

	@Override
	public void run() {
		int len = -1;
		int offset = 0;
		int type = -1;
		int body_len = -1;
		byte[] buff = null;
		byte[] token = new byte[32];
		int token_len = 0;
		util = new TransferUtil();
		
		InetAddress address = client.getInetAddress();
		
		try {
			
			this.tcpReadService.onConnection(address);
			
			in = client.getInputStream();
			out = client.getOutputStream();
			
			this.tcpWriteService.setOut(this.out);
			if(initializer instanceof ListenerService) {
				((ListenerService)initializer).onFinish();
			}
			while(!client.isClosed()) {
				if(body_len == -1) {
					if(offset == 0) {
						len = 4;
						buff = new byte[len];
					}
					offset += in.read(buff, offset, len);
					if(offset == -1) {
						break;
					}
					if(offset == len) {
						body_len = util.toHH(buff);
						offset = 0;
					}
				}else if(type == -1){
					type = in.read();
				}else if(token_len == 0) {
					if(offset == 0) {
						len = 32;
					}
					offset += in.read(token, offset, len);
					if(offset == -1) {
						break;
					}
					if(offset == len) {
						token_len = offset;
						offset = 0;
					}
				}else{
					if(offset == 0) {
						buff = new byte[body_len];
					}
					if(body_len > 0) {
						offset += in.read(buff, offset, body_len);
						if(offset == -1) {
							break;
						}
					}
					if(offset == body_len) {
						Message message = new Message();
						message.setData(buff);
						message.setToken(new String(token));
						message.setData(buff);
						tcpReadService.readMessage(tcpWriteService,message);
						len = -1;
						offset = 0;
						type = -1;
						body_len = -1;
						token_len = 0;
					}
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			this.tcpReadService.onClose(address);
			try {
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
				if(client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
