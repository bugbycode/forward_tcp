package com.bugbycode.initializer;

public abstract class AbstractTcpClientInitializer extends AbstractTcpServerInitializer implements ListenerService, TcpServerInitializer {

	@Override
	public void initializer() {

	}

	@Override
	public abstract void onSuccess();

	@Override
	public abstract void onFailed();

	@Override
	public abstract void onFinish();

}
