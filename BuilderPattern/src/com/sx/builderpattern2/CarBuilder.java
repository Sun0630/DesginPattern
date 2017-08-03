package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.CarModel;

/**
 * 汽车的建造者规范
 * 
 * @author sunxin
 *
 */
public interface CarBuilder {
	/**
	 * 建造一个模型，需要一个建造顺序
	 * @param sequence
	 */
	void setSequence(ArrayList<String> sequence);
	
	/**
	 * 顺序设置完毕，直接返回一个汽车模型
	 * @return
	 */
	CarModel getCarModel();
}	
