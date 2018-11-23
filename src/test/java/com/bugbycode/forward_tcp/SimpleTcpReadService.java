package com.bugbycode.forward_tcp;

import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.read.impl.AbstractTcpReadServiceImpl;
import com.bugbycode.proxy.io.write.TcpWriteService;

public class SimpleTcpReadService extends AbstractTcpReadServiceImpl {

	@Override
	protected void read(TcpWriteService write, Message message) throws Exception {
		write.write(message);
	}

}
