package com.bugbycode.forward.socket.client.initializer;

import com.bugbycode.forward.socket.client.io.read.ForwardClientTcpReadService;
import com.bugbycode.forward.socket.client.io.write.ForwardClientTcpWriteService;

public abstract class AbstractForwardClientTcpServerInitializer implements ForwardClientTcpServerInitializer {

	private ForwardClientTcpReadService tcpReadService;
	
	private ForwardClientTcpWriteService tcpWriteService;
	
	@Override
	public abstract void initializer();

	@Override
	public abstract void onSuccess();

	@Override
	public abstract void onFailed();

	@Override
	public abstract void onFinish();

	final public void setTcpReadService(ForwardClientTcpReadService tcpReadService) {
		this.tcpReadService = tcpReadService;
	}

	final public void setTcpWriteService(ForwardClientTcpWriteService tcpWriteService) {
		this.tcpWriteService = tcpWriteService;
	}

	@Override
	final public ForwardClientTcpReadService getTcpReadService() {
		return this.tcpReadService;
	}

	@Override
	final public ForwardClientTcpWriteService getTcpWriteService() {
		return this.tcpWriteService;
	}

}
