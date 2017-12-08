package com.sx.singleton.demo3;

/**
 * Double Check Lock(DCL) 双重锁校验
 * 优点：资源利用率高，但是由于Java内存模型的问题偶尔会出现DCL失效问题
 */
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //避免不必要的同步
        if (instance == null) {
            synchronized (Singleton.class) {
                //在null的情况下创建实例
                if (instance == null) {
                    /**
                     * 1.给Singleton的实例分配内存
                     * 2.调用Singleton()的构造函数
                     * 3.将instance对象指向分配的空间
                     *
                     * 因为JVM编译器对象允许处理器乱序执行，所以这三步顺序不一定
                     */
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
