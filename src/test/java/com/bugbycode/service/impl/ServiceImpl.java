package com.bugbycode.service.impl;

import com.bugbycode.service.Server1;
import com.bugbycode.service.Service2;

public class ServiceImpl implements Server1, Service2 {

	@Override
	public void say2() {
		System.out.println("say2");
	}

	@Override
	public void say() {
		
	}
	
}
