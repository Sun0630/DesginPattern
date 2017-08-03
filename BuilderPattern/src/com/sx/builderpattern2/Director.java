package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.BMWModel;
import com.sx.builderpattern.BenzModel;
import com.sx.builderpattern.CarModel;

/**
 * �����࣬����ָ���������ĸ����ͺ�
 * 
 * @author sunxin
 *
 */
public class Director {
	private ArrayList<String> sequence = new ArrayList<>();

	BenzBuilder benzBuilder = new BenzBuilder();
	BMWBuilder bmwBuilder = new BMWBuilder();

	/**
	 * �������۳�A�ͺţ���start����stop��û�������¡������
	 * @return
	 */
	public BenzModel getBenzAModel(){
		this.sequence.clear();//����һ�£��������ݻ���
		
		this.sequence.add("start");
		this.sequence.add("stop");
		
		benzBuilder.setSequence(sequence);
		CarModel benzCarModel = benzBuilder.getCarModel();
		
		return (BenzModel) benzCarModel;
	}
	
	
	/**
	 * �������۳�B�ͺţ���������start����stop��û������
	 * @return
	 */
	public BenzModel getBenzBModel(){
		this.sequence.clear();//����һ�£��������ݻ���
		
		this.sequence.add("engineBoom");
		this.sequence.add("start");
		this.sequence.add("stop");
		
		benzBuilder.setSequence(sequence);
		CarModel benzCarModel = benzBuilder.getCarModel();
		
		return (BenzModel) benzCarModel;
	}
	
	
	/**
	 * ��������A�ͺţ��Ȱ����ȣ�������ֹͣ	
	 * @return
	 */
	public BMWModel getBMWAModel(){
		this.sequence.clear();//����һ�£��������ݻ���
		
		this.sequence.add("alarm");
		this.sequence.add("start");
		this.sequence.add("stop");
		
		bmwBuilder.setSequence(sequence);
		CarModel bmwCarModel = bmwBuilder.getCarModel();
		
		return (BMWModel) bmwCarModel;
	}
	
	/**
	 * ��������B�ͺţ���ȥ����
	 * @return
	 */
	public BMWModel getBMWBModel(){
		this.sequence.clear();//����һ�£��������ݻ���
		
		this.sequence.add("start");
		
		bmwBuilder.setSequence(sequence);
		CarModel bmwCarModel = bmwBuilder.getCarModel();
		
		return (BMWModel) bmwCarModel;
	}
	
}
