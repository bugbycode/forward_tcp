package com.bugbycode.forward.socket.client.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.bugbycode.forward.socket.client.initializer.ForwardClientTcpServerInitializer;
import com.bugbycode.forward.socket.client.io.write.ForwardClientTcpWriteService;
import com.bugbycode.forward.socket.client.thread.ForwardTcpThread;
import com.bugbycode.proxy.socket.thread.pool.ThreadPool;

public class ForwardTcpClient {
	
	private Socket socket;
	
	private ThreadPool pool;
	
	private ForwardClientTcpServerInitializer initializer;
	
	private ForwardClientTcpWriteService tcpWriteService;
	
	private boolean isConnected;
	
	public ForwardTcpClient(){
		this.socket = new Socket();
		this.isConnected = false;
	}
	
	public void init(ForwardClientTcpServerInitializer initializer) throws IOException {
		if(isConnected) {
			throw new IOException("Tcp connected.");
		}
		this.initializer = initializer;
	}
	
	public void connection(String host,int port,int timeout) throws IOException, InterruptedException {
		if(isConnected) {
			throw new IOException("Tcp connected.");
		}
		if(initializer == null) {
			throw new IOException("TcpServerInitializer is null.");
		}
		try {
			this.socket.connect(new InetSocketAddress(host,port), timeout);
			this.initializer.initializer();
			this.initializer.onSuccess();
		}catch (IOException e) {
			this.initializer.onFailed();
			throw e;
		}
		
		this.pool = new ThreadPool(1, 1);
		this.pool.start();
		this.pool.waitStart();
		this.tcpWriteService = initializer.getTcpWriteService();
		this.pool.addTask(new ForwardTcpThread(socket, initializer));
		this.isConnected = true;
	}
	
	public void disConnection() throws IOException {
		if(this.socket != null) {
			this.socket.close();
		}
		if(this.pool != null) {
			this.pool.closePool();
		}
	}
	
	public void send(byte[] buff,int len) throws IOException {
		if(isConnected) {
			tcpWriteService.write(buff,len);
		}
	}
}
