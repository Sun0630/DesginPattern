package tech.sx.day10_factory.sample6;


import tech.sx.day10_factory.sample5.DiskIOHandler;
import tech.sx.day10_factory.sample5.IOFactory;
import tech.sx.day10_factory.sample5.IOHandler;
import tech.sx.day10_factory.sample5.MemoryIOHandler;
import tech.sx.day10_factory.sample5.PreferencesIOHandler;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 10:04
 * @Description (设计成单例设计模式) 抽象工厂模式 -- 避免简单工厂建造太多工厂，造成代码的冗余
 */

public class IOHandlerFactory implements IOFactory {

    private static volatile IOHandlerFactory mInstance;

    private IOHandler mMemoryIOHandler, mPreferencesIOHandler, mDiskIOHandler;

    private IOHandlerFactory() {
    }


    public static IOHandlerFactory getInstance() {
        if (mInstance == null) {
            synchronized (IOHandlerFactory.class) {
                if (mInstance == null) {
                    mInstance = new IOHandlerFactory();
                }
            }
        }
        return mInstance;
    }

    public IOHandler createIOHandler(Class<? extends IOHandler> ioHanlderClass) {
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
    @Override
    public IOHandler getMemroyIOHandler() {
        if (mMemoryIOHandler == null) {
            mMemoryIOHandler = createIOHandler(MemoryIOHandler.class);
        }
        return mMemoryIOHandler;
    }

    /**
     * 获取 sp 存储
     *
     * @return
     */
    @Override
    public IOHandler getPreferencesIOHandler() {
        if (mPreferencesIOHandler == null) {
            mPreferencesIOHandler = createIOHandler(PreferencesIOHandler.class);
        }
        return mPreferencesIOHandler;
    }

    /**
     * 获取磁盘存储
     *
     * @return
     */
    @Override
    public IOHandler getDiskIOHandler() {
        if (mDiskIOHandler == null) {
            mDiskIOHandler = createIOHandler(DiskIOHandler.class);
        }
        return mDiskIOHandler;
    }

    /**
     * 获取默认的存储方式
     *
     * @return
     */
    public IOHandler getDefaultIOHandler() {
        return getPreferencesIOHandler();
    }

}
