package com.bugbycode.forward_tcp;

import java.net.InetAddress;

import com.bugbycode.module.Message;
import com.bugbycode.proxy.io.read.impl.AbstractTcpReadServiceImpl;
import com.bugbycode.proxy.io.write.TcpWriteService;

public class SimpleTcpReadService extends AbstractTcpReadServiceImpl {

	@Override
	protected void read(TcpWriteService write, Message message) throws Exception {
		byte[] buff = (byte[]) message.getData();
		System.out.println("客户端消息：" + new String(buff));
		message.setData("我是服务端");
		write.write(message);
	}

	@Override
	public void onConnection(InetAddress address) {
		System.out.println("来自" + address.getHostAddress() + "的连接............");
	}

	@Override
	public void onClose(InetAddress address) {
		System.out.println(address.getHostAddress() + "的连接已断开............");
	}

}
