package com.bugbycode.forward.socket.client.initializer;

import com.bugbycode.forward.socket.client.io.read.ForwardClientTcpReadService;
import com.bugbycode.forward.socket.client.io.write.ForwardClientTcpWriteService;

public interface ForwardClientTcpServerInitializer {
	
	public void initializer();
	
	public void onSuccess();
	
	public void onFailed();
	
	public void onFinish();
	
	public ForwardClientTcpReadService getTcpReadService();
	
	public ForwardClientTcpWriteService getTcpWriteService();
}
