package com.bugbycode.forward.socket.client.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.bugbycode.forward.socket.client.initializer.ForwardClientTcpServerInitializer;
import com.bugbycode.forward.socket.client.io.read.ForwardClientTcpReadService;
import com.bugbycode.forward.socket.client.io.write.ForwardClientTcpWriteService;

public class ForwardTcpThread extends Thread {

	private ForwardClientTcpReadService tcpReadService;
	
	private ForwardClientTcpWriteService tcpWriteService;
	
	private ForwardClientTcpServerInitializer initializer;
	
	private Socket client;
	
	private InputStream in = null;
	
	private OutputStream out = null;
	
	public ForwardTcpThread(Socket client,ForwardClientTcpServerInitializer initializer) {
		this.client = client;
		this.tcpReadService = initializer.getTcpReadService();
		this.tcpWriteService = initializer.getTcpWriteService();
		this.initializer = initializer;
	}

	@Override
	public void run() {
		int len = -1;
		
		byte[] buff = new byte[4096];
		
		InetAddress address = client.getInetAddress();
		
		try {
			
			this.tcpReadService.onConnection(address);
			
			in = client.getInputStream();
			out = client.getOutputStream();
			
			this.tcpWriteService.setOut(this.out);

			initializer.onFinish();
			
			while(!client.isClosed()) {
				len = in.read(buff);
				tcpReadService.readMessage(buff, len);
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
