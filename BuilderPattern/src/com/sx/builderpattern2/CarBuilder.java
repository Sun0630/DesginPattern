package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.CarModel;

/**
 * �����Ľ����߹淶
 * 
 * @author sunxin
 *
 */
public interface CarBuilder {
	/**
	 * ����һ��ģ�ͣ���Ҫһ������˳��
	 * @param sequence
	 */
	void setSequence(ArrayList<String> sequence);
	
	/**
	 * ˳��������ϣ�ֱ�ӷ���һ������ģ��
	 * @return
	 */
	CarModel getCarModel();
}	
