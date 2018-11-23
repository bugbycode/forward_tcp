package com.bugbycode.proxy.socket.thread.pool;

import java.util.LinkedList;

import com.util.RandomUtil;

public class ThreadPool extends ThreadGroup {

	private final int MAX_THREAD_NUMBER;
	
	private final int WAIT_CONNECT_NUMBER;
	
	private boolean isClosed = true;
	
	private LinkedList<Runnable> queue;
	
	public ThreadPool(int max_thread,int wait_number) {
		super(RandomUtil.GetGuid32());
		this.MAX_THREAD_NUMBER = max_thread;
		this.WAIT_CONNECT_NUMBER = wait_number;
		this.queue = new LinkedList<Runnable>();
	}

	public void addTask(Runnable run) {
		if(isClosed || queue.size() == WAIT_CONNECT_NUMBER) {
			return;
		}
		queue.addLast(run);
		this.notifyTask();
	}
	
	private synchronized Runnable getTask() throws InterruptedException {
		while(queue.isEmpty()) {
			wait();
			if(isClosed) {
				throw new InterruptedException("Thread pool closed.");
			}
		}
		return queue.removeFirst();
	}
	
	private synchronized void notifyTask() {
		this.notifyAll();
	}
	
	public void start() {
		for(int number = 0;number < MAX_THREAD_NUMBER;number++) {
			new WorkThread().start();
		}
		isClosed = false;
		this.notifyTask();
	}
	
	public synchronized void waitStart() throws InterruptedException {
		while(isClosed) {
			wait();
		}
	}
	
	public void closePool() {
		this.isClosed = true;
		this.notifyTask();
	}
	
	private class WorkThread extends Thread{

		@Override
		public void run() {
			while(!isClosed) {
				try {
					Runnable run = getTask();
					if(run == null) {
						return;
					}
					run.run();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
