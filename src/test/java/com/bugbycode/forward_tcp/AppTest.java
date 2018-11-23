package com.bugbycode.forward_tcp;

import java.io.IOException;

import org.junit.Test;

import com.bugbycode.proxy.socket.server.TcpServer;

/**
 * Unit test for simple App.
 */
public class AppTest{
    
	@Test
	public void testServer() throws IOException {
		TcpServer server = new TcpServer(10,10);
		server.init(new SimpleTcpServerInitializer());
		server.bind(4000, 100);
	}
	
}
