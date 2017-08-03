package com.sx.builderpattern;

public class BMWModel extends CarModel{

	@Override
	protected void start() {
		System.out.println("宝马车跑了...");
	}

	@Override
	protected void stop() {
		System.out.println("宝马车停了...");
	}

	@Override
	protected void alarm() {
		System.out.println("宝马车鸣笛了...");
	}

	@Override
	protected void engineBoom() {
		System.out.println("宝马车引擎轰隆了...");
	}

}
