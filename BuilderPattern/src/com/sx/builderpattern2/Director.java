package com.sx.builderpattern2;

import java.util.ArrayList;

import com.sx.builderpattern.BMWModel;
import com.sx.builderpattern.BenzModel;
import com.sx.builderpattern.CarModel;

/**
 * 导演类，可以指定生产车的各种型号
 * 
 * @author sunxin
 *
 */
public class Director {
	private ArrayList<String> sequence = new ArrayList<>();

	BenzBuilder benzBuilder = new BenzBuilder();
	BMWBuilder bmwBuilder = new BMWBuilder();

	/**
	 * 生产奔驰车A型号，先start，后stop，没有引擎轰隆和喇叭
	 * @return
	 */
	public BenzModel getBenzAModel(){
		this.sequence.clear();//清理一下，避免数据混淆
		
		this.sequence.add("start");
		this.sequence.add("stop");
		
		benzBuilder.setSequence(sequence);
		CarModel benzCarModel = benzBuilder.getCarModel();
		
		return (BenzModel) benzCarModel;
	}
	
	
	/**
	 * 生产奔驰车B型号，引擎声，start，后stop，没有喇叭
	 * @return
	 */
	public BenzModel getBenzBModel(){
		this.sequence.clear();//清理一下，避免数据混淆
		
		this.sequence.add("engineBoom");
		this.sequence.add("start");
		this.sequence.add("stop");
		
		benzBuilder.setSequence(sequence);
		CarModel benzCarModel = benzBuilder.getCarModel();
		
		return (BenzModel) benzCarModel;
	}
	
	
	/**
	 * 生产宝马车A型号，先按喇叭，启动，停止	
	 * @return
	 */
	public BMWModel getBMWAModel(){
		this.sequence.clear();//清理一下，避免数据混淆
		
		this.sequence.add("alarm");
		this.sequence.add("start");
		this.sequence.add("stop");
		
		bmwBuilder.setSequence(sequence);
		CarModel bmwCarModel = bmwBuilder.getCarModel();
		
		return (BMWModel) bmwCarModel;
	}
	
	/**
	 * 生产宝马车B型号，上去就跑
	 * @return
	 */
	public BMWModel getBMWBModel(){
		this.sequence.clear();//清理一下，避免数据混淆
		
		this.sequence.add("start");
		
		bmwBuilder.setSequence(sequence);
		CarModel bmwCarModel = bmwBuilder.getCarModel();
		
		return (BMWModel) bmwCarModel;
	}
	
}
