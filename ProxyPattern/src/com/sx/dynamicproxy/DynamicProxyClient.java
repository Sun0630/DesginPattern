package com.sx.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sx.IWish;
import com.sx.Me;

/**
 * 动态代理
 * 
 * @author sunxin
 *
 */
public class DynamicProxyClient {
	public static void main(String[] args) {
		IWish me = new Me();
		/**
		 * 参数一：类加载器对象，即哪个类加载器加载这个代理类到jvm的方法区
		 * 参数二：接口，表名自己的代理类需要实现哪个接口
		 * 参数三：指定代理类中都需要做什么
		 */
		IWish proxy = (IWish) Proxy.newProxyInstance(IWish.class.getClassLoader(), // 类加载器
				new Class<?>[] { IWish.class }, // 接口类数组
				new WishInvocationHandler(me));// 回调接口
		
		proxy.buyAudi();
		System.out.println("=================");
		proxy.buyIMac();
		
	}

	
	/**
	 * 指定运行时将生成的代理类要完成的具体任务，代理类调用任何方法都会经过该类
	 * @author sunxin
	 *
	 */
	public static class WishInvocationHandler implements InvocationHandler {
		
		
		private IWish me;


		public WishInvocationHandler(IWish me){
			this.me = me;
			
		}
		

		/**
		 * @param proxy 代理对象
		 * @param method 
		 * @param args
		 * @return
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("代购前一些需要做的事情");
			//执行方法
			method.invoke(me, args);
			System.out.println("代购完毕");
			return null;
		}

	}
}
