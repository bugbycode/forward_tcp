package com.util;

public class TransferUtil {
	
	public int toHH(byte[] buff) {
		return ((buff[0] & 0xFF) << 0x18) | ((buff[1] & 0xFF) << 0x10) | ((buff[2] & 0xFF) << 0x08) | (buff[3] & 0xFF);
	}
	
	public byte[] toHH(int len) {
		byte[] buff = new byte[4];
		buff[0] = (byte)(0xFF & (len >> 0x18));
		buff[1] = (byte)(0xFF & (len >> 0x10));
		buff[2] = (byte)(0xFF & (len >> 0x08));
		buff[3] = (byte)(0xFF & len);
		return buff;
	}
}
