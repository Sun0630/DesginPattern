package com.sx.singleton.demo5;

/**
 * 枚举单例
 *  写法简单，默认枚举实例的创建是线程安全的。
 *  在上述的几种单例模式的实现中，在反序列化的情况下会出现重新创建对象的情况。
 */
public enum SingletonEnum {
    INSTANCE;
    public void doSomething(){
        System.out.println("do sth");
    }
}
