package com.sx.builderpattern;

/**
 * ���۳�ģ��
 * @author sunxin
 *
 */
public class BenzModel extends CarModel{

	@Override
	protected void start() {
		System.out.println("���۳�����...");
	}

	@Override
	protected void stop() {
		System.out.println("���۳�ͣ��...");
	}

	@Override
	protected void alarm() {
		System.out.println("���۳�������...");
	}

	@Override
	protected void engineBoom() {
		System.out.println("���۳������¡��...");
	}

}
