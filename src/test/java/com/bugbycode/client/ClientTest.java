package com.bugbycode.client;

import java.io.IOException;

import com.bugbycode.forward.socket.client.tcp.ForwardTcpClient;

public class ClientTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		ForwardTcpClient client = new ForwardTcpClient();
		client.init(new SimpleForwardClientTcpServerInitializer());
		client.connection("192.168.1.38", 60000, 5000);
		client.disConnection();
	}

}

