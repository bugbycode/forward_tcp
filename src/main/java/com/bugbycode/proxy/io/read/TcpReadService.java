package com.bugbycode.proxy.io.read;


import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.write.TcpWriteService;

public interface TcpReadService {
	
	public void readMessage(TcpWriteService write,Message message) throws Exception;
}
