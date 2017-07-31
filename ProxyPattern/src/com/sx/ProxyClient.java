package com.sx;

public class ProxyClient {
	public static void main(String[] args) {
		Me me = new Me();
		IWish saler = new Saler(me);
		saler.buyAudi();
		System.out.println("--------");
		saler.buyIMac();
	}
}
