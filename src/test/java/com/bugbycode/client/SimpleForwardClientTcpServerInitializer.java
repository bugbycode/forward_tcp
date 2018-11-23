package com.bugbycode.client;

import com.bugbycode.forward.socket.client.initializer.AbstractForwardClientTcpServerInitializer;
import com.bugbycode.forward.socket.client.io.write.impl.ForwardClientTcpWrite;

public class SimpleForwardClientTcpServerInitializer extends AbstractForwardClientTcpServerInitializer {

	@Override
	public void initializer() {
		this.setTcpReadService(new SimpleForwardClientTcpRead());
		this.setTcpWriteService(new ForwardClientTcpWrite());
	}

	@Override
	public void onSuccess() {
		System.out.println("onSuccess()");
	}

	@Override
	public void onFailed() {
		System.out.println("onFailed()");
	}

	@Override
	public void onFinish() {
		System.out.println("onFinish()");
	}

}
