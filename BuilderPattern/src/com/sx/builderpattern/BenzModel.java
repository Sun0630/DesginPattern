package com.sx.builderpattern;

/**
 * 奔驰车模型
 * @author sunxin
 *
 */
public class BenzModel extends CarModel{

	@Override
	protected void start() {
		System.out.println("奔驰车跑了...");
	}

	@Override
	protected void stop() {
		System.out.println("奔驰车停了...");
	}

	@Override
	protected void alarm() {
		System.out.println("奔驰车鸣笛了...");
	}

	@Override
	protected void engineBoom() {
		System.out.println("奔驰车引擎轰隆了...");
	}

}
