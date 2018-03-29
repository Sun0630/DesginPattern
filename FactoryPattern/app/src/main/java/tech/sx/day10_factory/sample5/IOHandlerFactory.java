package tech.sx.day10_factory.sample5;


/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 10:04
 * @Description (可以设计成单例设计模式) 抽象工厂模式 -- 避免简单工厂建造太多工厂，造成代码的冗余
 */

public class IOHandlerFactory {


    public static IOHandler createIOHandler(Class<? extends IOHandler> ioHanlderClass) {
        try {
            return ioHanlderClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return getPreferencesIOHandler();
    }


    /**
     * 获取内存存储
     *
     * @return
     */
    public static IOHandler getMemroyIOHandler() {
        return createIOHandler(MemoryIOHandler.class);
    }

    /**
     * 获取 sp 存储
     *
     * @return
     */
    public static IOHandler getPreferencesIOHandler() {
        return createIOHandler(PreferencesIOHandler.class);
    }

    /**
     * 获取磁盘存储
     *
     * @return
     */
    public static IOHandler getDiskIOHandler() {
        return createIOHandler(DiskIOHandler.class);
    }

    /**
     * 获取默认的存储方式
     *
     * @return
     */
    public static IOHandler getDefaultIOHandler() {
        return getPreferencesIOHandler();
    }

}
