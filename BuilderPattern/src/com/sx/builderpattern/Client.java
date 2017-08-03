package com.sx.builderpattern;

import java.util.ArrayList;

import com.sx.builderpattern2.BMWBuilder;
import com.sx.builderpattern2.BenzBuilder;
import com.sx.builderpattern2.Director;

/**
 * �ͻ���
 * 
 * @author sunxin
 *
 */
public class Client {
	public static void main(String[] args) {
		/**
		 * ����
		 */
//		BenzModel benzModel = new BenzModel();
//		//����run��˳��
//		ArrayList<String> sequence = new ArrayList<>();
//		sequence.add("engineBoom");
//		sequence.add("start");
//		sequence.add("stop");
//		benzModel.setSequence(sequence);
//		benzModel.run();
		
		
		/**
		 * ����1,����Builder��
		 */
		//����˳��
//		ArrayList<String> sequence = new ArrayList<>();
//		sequence.add("engineBoom");
//		sequence.add("start");
//		sequence.add("stop");
//		
//		//��һ�����۳�
//		BenzBuilder benzBuilder = new BenzBuilder();
//		benzBuilder.setSequence(sequence);
//		CarModel benzModel = benzBuilder.getCarModel();
//		benzModel.run();
//		
//		//��һ������
//		BMWBuilder bmwBuilder = new BMWBuilder();
//		bmwBuilder.setSequence(sequence);
//		CarModel bmwModel = bmwBuilder.getCarModel();
//		bmwModel.run();
		
		
		/**
		 * ����2�����뵼����Director������˳����������
		 */
		
		Director director = new Director();
		
		//����10��A�ͱ���
		for (int i = 0; i < 3; i++) {
			BMWModel bmwaModel = director.getBMWAModel();
			bmwaModel.run();
			System.out.println("---");
		}
			System.out.println("------------");
			
		//����20��B�ͱ��۳�
		for (int i = 0; i < 2; i++) {
			BenzModel benzBModel = director.getBenzBModel();
			benzBModel.run();
			System.out.println("---");
		}
		
	}
}
