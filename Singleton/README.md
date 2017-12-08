[TOC]
# 单例模式
> **核心原理：** 将构造函数私有化，并且通过静态方法获取一个唯一的实例，在这个过程中必须保证线程安全、防止反序列化导致重新生成实例对象等问题。

## 单例模式实现的几种方式
### 饿汉式
```
/**
 * 饿汉式
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance() {
        return instance;
    }
}
```
### 懒汉式
```
/**
 * 懒汉式
 */
public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```
### Double Check Lock（DCL）实现单例模式
```
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
```
### 静态内部类单例模式
```
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
```
### 枚举单例
```
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

```
### 容器实现单例模式
```
/**
 * 使用容器实现单例模式
 */
public class SingletonManager {
    private static Map<String,Object> objMap = new HashMap<String,Object>();

    private SingletonManager(){}

    public static void registerService(String key,Object instance){
        if (!objMap.containsKey(key)){
            objMap.put(key,instance);
        }
    }

    public static Object getInstance(String key){
        return objMap.get(key);
    }

}
```







