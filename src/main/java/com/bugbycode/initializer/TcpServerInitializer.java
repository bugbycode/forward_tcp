package com.bugbycode.initializer;

import com.bugbycode.proxy.io.read.TcpReadService;
import com.bugbycode.proxy.io.write.TcpWriteService;

public interface TcpServerInitializer {
	
	public void initializer();
	
	public TcpReadService getTcpReadService();
	
	public TcpWriteService getTcpWriteService();
}
