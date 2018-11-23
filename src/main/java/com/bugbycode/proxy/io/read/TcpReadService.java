package com.bugbycode.proxy.io.read;


import java.net.InetAddress;

import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.write.TcpWriteService;

public interface TcpReadService {
	
	public void onConnection(InetAddress address);
	
	public void readMessage(TcpWriteService write,Message message) throws Exception;
	
	public void onClose(InetAddress address);
}
