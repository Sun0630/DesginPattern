package com.sx;

public class Saler implements IWish{

	
	private IWish me;

	public Saler(IWish me){
		this.me = me;
		
	}
	
	@Override
	public void buyAudi() {
		System.out.println("办理一系列买车业务");
		//我自己负责出钱等
		me.buyAudi();
		System.out.println("买车业务办理完毕");
	}

	@Override
	public void buyIMac() {
		System.out.println("办理一系列买Mac业务");
		me.buyIMac();
		System.out.println("买Mac业务办理完毕");
	}

}
