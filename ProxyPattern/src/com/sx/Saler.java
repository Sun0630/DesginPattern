package com.sx;

public class Saler implements IWish{

	
	private IWish me;

	public Saler(IWish me){
		this.me = me;
		
	}
	
	@Override
	public void buyAudi() {
		System.out.println("����һϵ����ҵ��");
		//���Լ������Ǯ��
		me.buyAudi();
		System.out.println("��ҵ��������");
	}

	@Override
	public void buyIMac() {
		System.out.println("����һϵ����Macҵ��");
		me.buyIMac();
		System.out.println("��Macҵ��������");
	}

}
