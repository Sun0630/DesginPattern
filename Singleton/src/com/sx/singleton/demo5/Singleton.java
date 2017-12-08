package com.sx.singleton.demo5;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 防止单例对象被反序列化，重写反序列化的一个钩子函数readResolve()
 */
public final class Singleton implements Serializable{

    public static final Singleton INSTANCE = new Singleton();

    public static Singleton getInstance() {
        return INSTANCE;
    }

    /**
     * 反序列化操作中可以让那个开发人员控制对象的函数
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException{
        return INSTANCE;
    }

}
