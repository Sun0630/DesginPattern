package tech.sx.day10_factory.sample4;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 11:19
 * @Description
 */

public class MemoryIOFactory implements IOFactory {
    @Override
    public IOHandler createIOFactory() {
        return new MemoryIOHandler();
    }
}
