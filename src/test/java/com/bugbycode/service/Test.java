package com.bugbycode.service;

import com.bugbycode.service.impl.ServiceImpl;

public class Test {

	public static void main(String[] args) {
		Server1 s = new ServiceImpl();
		if(s instanceof Service2) {
			Service2 s2 = (Service2) s;
			s2.say2();
		}
	}

}
