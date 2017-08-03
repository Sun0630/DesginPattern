package com.sx.builderpattern;

import java.util.ArrayList;

/**
 * 车辆模型的抽象类
 * 
 * @author sunxin
 *
 */
public abstract class CarModel {
	// 各个方法执行的顺序
	private ArrayList<String> sequence = new ArrayList<>();

	// 汽车启动
	protected abstract void start();

	// 汽车停止
	protected abstract void stop();

	// 汽车鸣笛
	protected abstract void alarm();

	// 汽车引擎轰轰响
	protected abstract void engineBoom();

	// 汽车跑动
	public void run() {
		// 循环，谁在前，谁就先run
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
	 * 允许用户自己设置一个顺序
	 * @param sequence
	 */
	public void setSequence(ArrayList<String> sequence) {
		this.sequence = sequence;
	}

}
