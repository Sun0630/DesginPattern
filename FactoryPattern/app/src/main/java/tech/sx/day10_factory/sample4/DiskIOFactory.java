package tech.sx.day10_factory.sample4;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 11:21
 * @Description
 */

public class DiskIOFactory implements IOFactory {

    @Override
    public IOHandler createIOFactory() {
        return new DiskIOHandler();
    }

}
