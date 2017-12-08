package com.sx.singleton.demo4;

/**
 * 静态内部类单例模式(推荐使用)
 * 能够保证线程安全，对象的唯一性，延迟了单例的实例化。
 */
public class Singleton {

    private Singleton(){}

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

}
