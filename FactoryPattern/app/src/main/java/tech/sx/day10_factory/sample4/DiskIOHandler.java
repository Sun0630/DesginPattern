package tech.sx.day10_factory.sample4;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 11:15
 * @Description 磁盘存储
 */

public class DiskIOHandler implements IOHandler {

    @Override
    public void save(String key, String value) {

    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, long value) {

    }

    @Override
    public void save(String key, boolean value) {

    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public int getInt(String key, int defalutValue) {
        return 0;
    }

    @Override
    public long getLong(String key, long defalutValue) {
        return 0;
    }

    @Override
    public double getDouble(String key, double defalutValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defalutValue) {
        return false;
    }

    @Override
    public Object getObject(String key) {
        return null;
    }
}
