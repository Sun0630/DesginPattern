package com.sx.builderpattern;

import java.util.ArrayList;

import com.sx.builderpattern2.BMWBuilder;
import com.sx.builderpattern2.BenzBuilder;
import com.sx.builderpattern2.Director;

/**
 * 客户端
 * 
 * @author sunxin
 *
 */
public class Client {
	public static void main(String[] args) {
		/**
		 * 基本
		 */
//		BenzModel benzModel = new BenzModel();
//		//设置run的顺序
//		ArrayList<String> sequence = new ArrayList<>();
//		sequence.add("engineBoom");
//		sequence.add("start");
//		sequence.add("stop");
//		benzModel.setSequence(sequence);
//		benzModel.run();
		
		
		/**
		 * 改造1,加入Builder，
		 */
		//设置顺序
//		ArrayList<String> sequence = new ArrayList<>();
//		sequence.add("engineBoom");
//		sequence.add("start");
//		sequence.add("stop");
//		
//		//造一辆奔驰车
//		BenzBuilder benzBuilder = new BenzBuilder();
//		benzBuilder.setSequence(sequence);
//		CarModel benzModel = benzBuilder.getCarModel();
//		benzModel.run();
//		
//		//造一辆宝马车
//		BMWBuilder bmwBuilder = new BMWBuilder();
//		bmwBuilder.setSequence(sequence);
//		CarModel bmwModel = bmwBuilder.getCarModel();
//		bmwModel.run();
		
		
		/**
		 * 改造2，加入导演类Director，控制顺序，批量建造
		 */
		
		Director director = new Director();
		
		//建造10辆A型宝马车
		for (int i = 0; i < 3; i++) {
			BMWModel bmwaModel = director.getBMWAModel();
			bmwaModel.run();
			System.out.println("---");
		}
			System.out.println("------------");
			
		//建造20辆B型奔驰车
		for (int i = 0; i < 2; i++) {
			BenzModel benzBModel = director.getBenzBModel();
			benzBModel.run();
			System.out.println("---");
		}
		
	}
}
