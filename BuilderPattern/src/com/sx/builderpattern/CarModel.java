package com.sx.builderpattern;

import java.util.ArrayList;

/**
 * ����ģ�͵ĳ�����
 * 
 * @author sunxin
 *
 */
public abstract class CarModel {
	// ��������ִ�е�˳��
	private ArrayList<String> sequence = new ArrayList<>();

	// ��������
	protected abstract void start();

	// ����ֹͣ
	protected abstract void stop();

	// ��������
	protected abstract void alarm();

	// ������������
	protected abstract void engineBoom();

	// �����ܶ�
	public void run() {
		// ѭ����˭��ǰ��˭����run
		for (int i = 0; i < this.sequence.size(); i++) {
			String actionName = this.sequence.get(i);
			if (actionName.equalsIgnoreCase("start")) {
				this.start();
			} else if (actionName.equalsIgnoreCase("stop")) {
				this.stop();
			} else if (actionName.equalsIgnoreCase("alarm")) {
				this.alarm();
			} else if (actionName.equalsIgnoreCase("engineBoom")) {
				this.engineBoom();
			}
		}
	}
	
	/**
	 * �����û��Լ�����һ��˳��
	 * @param sequence
	 */
	public void setSequence(ArrayList<String> sequence) {
		this.sequence = sequence;
	}

}
