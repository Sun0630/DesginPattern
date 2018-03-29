package tech.sx.day10_factory.sample3;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 10:04
 * @Description 简单工厂模式
 */

public class IOHandlerFactory {

    public enum IOType {
        MEMRORY,
        PREFERENCES,
        DISK
    }


    public static IOHandler createIOHandler(IOType type) {
        switch (type) {
            case MEMRORY:
                return new MemoryIOHandler();
            case PREFERENCES:
                return new PreferencesIOHandler();
            case DISK:
                return new DiskIOHandler();
            default:
                return null;
        }
    }

}
