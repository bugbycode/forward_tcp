package com.bugbycode.initializer;

import com.bugbycode.proxy.io.read.TcpReadService;
import com.bugbycode.proxy.io.write.TcpWriteService;

public abstract class AbstractTcpServerInitializer implements TcpServerInitializer {

	private TcpReadService tcpReadService;
	
	private TcpWriteService tcpWriteService;
	
	@Override
	public abstract void initializer();

	@Override
	final public TcpReadService getTcpReadService() {
		return this.tcpReadService;
	}

	@Override
	final public TcpWriteService getTcpWriteService() {
		return this.tcpWriteService;
	}

	final public void setTcpReadService(TcpReadService tcpReadService) {
		this.tcpReadService = tcpReadService;
	}

	final public void setTcpWriteService(TcpWriteService tcpWriteService) {
		this.tcpWriteService = tcpWriteService;
	}

}
