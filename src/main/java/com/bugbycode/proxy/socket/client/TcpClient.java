package com.bugbycode.proxy.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.bugbycode.initializer.ListenerService;
import com.bugbycode.initializer.TcpServerInitializer;
import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.write.TcpWriteService;
import com.bugbycode.proxy.socket.thread.pool.ThreadPool;
import com.bugbycode.proxy.socket.thread.task.TcpThread;

public class TcpClient {
	
	private Socket socket;
	
	private ThreadPool pool;
	
	private TcpServerInitializer initializer;
	
	private TcpWriteService tcpWriteService;
	
	private boolean isConnected;
	
	public TcpClient(){
		this.socket = new Socket();
		this.isConnected = false;
	}
	
	public void init(TcpServerInitializer initializer) throws IOException {
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
			if(initializer instanceof ListenerService) {
				((ListenerService)initializer).onSuccess();
			}
		}catch (IOException e) {
			if(initializer instanceof ListenerService) {
				((ListenerService)initializer).onFailed();
			}
			throw e;
		}
		this.pool = new ThreadPool(1, 1);
		this.pool.start();
		this.pool.waitStart();
		this.tcpWriteService = initializer.getTcpWriteService();
		this.pool.addTask(new TcpThread(socket, initializer));
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
	
	public void send(Message message) throws IOException {
		if(isConnected) {
			tcpWriteService.write(message);
		}
	}
}
