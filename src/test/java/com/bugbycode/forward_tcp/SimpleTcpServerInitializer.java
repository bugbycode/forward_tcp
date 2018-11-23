package com.bugbycode.forward_tcp;

import com.bugbycode.initializer.AbstractTcpServerInitializer;
import com.bugbycode.proxy.io.write.impl.TcpWriteServiceImpl;

public class SimpleTcpServerInitializer extends AbstractTcpServerInitializer {

	@Override
	public void initializer() {
		this.setTcpReadService(new SimpleTcpReadService());
		this.setTcpWriteService(new TcpWriteServiceImpl());
	}

}
