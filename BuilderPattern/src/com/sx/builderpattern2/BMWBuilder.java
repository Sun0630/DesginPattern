package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.BMWModel;
import com.sx.builderpattern.CarModel;

/**
 * 宝马车建造者
 * @author sunxin
 *
 */
public class BMWBuilder implements CarBuilder{

	private BMWModel bmwModer = new BMWModel();
	@Override
	public void setSequence(ArrayList<String> sequence) {
		this.bmwModer.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		return bmwModer;
	}

}
