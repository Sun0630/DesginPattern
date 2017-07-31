package com.sx.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sx.IWish;
import com.sx.Me;

/**
 * ��̬����
 * 
 * @author sunxin
 *
 */
public class DynamicProxyClient {
	public static void main(String[] args) {
		IWish me = new Me();
		/**
		 * ����һ������������󣬼��ĸ��������������������ൽjvm�ķ�����
		 * ���������ӿڣ������Լ��Ĵ�������Ҫʵ���ĸ��ӿ�
		 * ��������ָ���������ж���Ҫ��ʲô
		 */
		IWish proxy = (IWish) Proxy.newProxyInstance(IWish.class.getClassLoader(), // �������
				new Class<?>[] { IWish.class }, // �ӿ�������
				new WishInvocationHandler(me));// �ص��ӿ�
		
		proxy.buyAudi();
		System.out.println("=================");
		proxy.buyIMac();
		
	}

	
	/**
	 * ָ������ʱ�����ɵĴ�����Ҫ��ɵľ������񣬴���������κη������ᾭ������
	 * @author sunxin
	 *
	 */
	public static class WishInvocationHandler implements InvocationHandler {
		
		
		private IWish me;


		public WishInvocationHandler(IWish me){
			this.me = me;
			
		}
		

		/**
		 * @param proxy �������
		 * @param method 
		 * @param args
		 * @return
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("����ǰһЩ��Ҫ��������");
			//ִ�з���
			method.invoke(me, args);
			System.out.println("�������");
			return null;
		}

	}
}
