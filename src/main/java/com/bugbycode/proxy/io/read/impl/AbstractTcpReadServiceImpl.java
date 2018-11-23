package com.bugbycode.proxy.io.read.impl;

import org.json.JSONObject;

import com.bugbycode.module.Authentication;
import com.bugbycode.module.ConnectionInfo;
import com.bugbycode.module.Message;
import com.bugbycode.module.MessageCode;
import com.bugbycode.proxy.io.read.TcpReadService;
import com.bugbycode.proxy.io.write.TcpWriteService;

public abstract class AbstractTcpReadServiceImpl implements TcpReadService{

	
	public AbstractTcpReadServiceImpl() {
		
	}
	
	@Override
	public void readMessage(TcpWriteService write,Message message) throws Exception {
		int type = message.getType();
		Object obj = message.getData();
		byte[] data = (byte[]) obj;
		try {
			if(type == MessageCode.AUTH) {
				String authInfo = new String(data);
				JSONObject json = new JSONObject(authInfo);
				Authentication auth = new Authentication(json.getString("username"), json.getString("password"));
				message.setData(auth);
			}else if(type == MessageCode.CONNECTION) {
				String connInfo = new String(data);
				
				JSONObject json = new JSONObject(connInfo);
				
				ConnectionInfo conn = new ConnectionInfo(json.getString("host"), json.getInt("port"));
				message.setData(conn);
			}else if(type == MessageCode.TRANSFER_DATA) {
				message.setData(data);
			}
			
			read(write,message);
			
		}catch (Exception e) {
			throw new RuntimeException("Message error." + e.getMessage());
		}
	}
	
	protected abstract void read(TcpWriteService write,Message message) throws Exception;
}
