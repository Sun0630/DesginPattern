package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.BenzModel;
import com.sx.builderpattern.CarModel;

/**
 * ���۳�������
 * 
 * @author sunxin
 *
 */
public class BenzBuilder implements CarBuilder {
	private BenzModel benzModel = new BenzModel();
	//���ý���˳��
	@Override
	public void setSequence(ArrayList<String> sequence) {
		this.benzModel.setSequence(sequence);
	}
	//ֱ�ӷ���ģ��
	@Override
	public CarModel getCarModel() {
		return benzModel;
	}

}
