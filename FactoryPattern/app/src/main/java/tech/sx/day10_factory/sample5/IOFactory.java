package tech.sx.day10_factory.sample5;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 10:04
 * @Description 工厂方法模式 ---  修复简单工厂模式的复杂，如果需要新增工厂就需要修改很多代码。一个Factory对应一个Handler
 */

public interface IOFactory {
    /**
     * 获取内存数据
     *
     * @return
     */
    IOHandler getMemroyIOHandler();

    /**
     * 获取sp数据
     *
     * @return
     */
    IOHandler getPreferencesIOHandler();

    /**
     * 获取硬盘数据
     *
     * @return
     */
    IOHandler getDiskIOHandler();
}
