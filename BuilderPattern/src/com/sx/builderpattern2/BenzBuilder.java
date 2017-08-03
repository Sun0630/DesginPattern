package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.BenzModel;
import com.sx.builderpattern.CarModel;

/**
 * 奔驰车建造者
 * 
 * @author sunxin
 *
 */
public class BenzBuilder implements CarBuilder {
	private BenzModel benzModel = new BenzModel();
	//设置建造顺序
	@Override
	public void setSequence(ArrayList<String> sequence) {
		this.benzModel.setSequence(sequence);
	}
	//直接返回模型
	@Override
	public CarModel getCarModel() {
		return benzModel;
	}

}
