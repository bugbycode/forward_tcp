package com.bugbycode.proxy.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.bugbycode.initializer.TcpServerInitializer;
import com.bugbycode.proxy.socket.thread.pool.ThreadPool;
import com.bugbycode.proxy.socket.thread.task.TcpThread;

public class TcpServer {
	
	private ServerSocket server;
	
	private ThreadPool pool;
	
	private boolean isBind = false;
	
	private boolean isClosed = true;
	
	private TcpServerInitializer initializer;
	
	public TcpServer(int max_thread,int wait_number) throws IOException {
		this.server = new ServerSocket();
		this.pool = new ThreadPool(max_thread, wait_number);
	}
	
	public void bind(int port,int backlog) throws IOException {
		if(isBind) {
			throw new IOException("Server start.");
		}
		
		if(initializer == null) {
			throw new IOException("TcpServerInitializer is null.");
		}
		
		this.pool.start();
		
		this.server.bind(new InetSocketAddress(port), backlog);
		this.isBind = true;
		this.isClosed = false;
		
		while(!isClosed) {
			try {
				Socket client = server.accept();
				
				initializer.initializer();
				
				TcpThread tcpThread = new TcpThread(client,initializer.getTcpReadService(),
						initializer.getTcpWriteService());
				pool.addTask(tcpThread);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void init(TcpServerInitializer initializer) throws IOException {
		if(isBind) {
			throw new IOException("Server start.");
		}
		this.initializer = initializer;
	}
	
}
