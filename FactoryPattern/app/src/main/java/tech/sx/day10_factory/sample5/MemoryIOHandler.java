package tech.sx.day10_factory.sample5;

import android.util.LruCache;

/**
 * @author sunxin
 * @Date 2018/3/29 0029 上午 10:17
 * @Description
 */
public class MemoryIOHandler implements IOHandler {

    private static final String TAG = "MemoryIOHandler";
    /**
     * 内存维护着一个LinkedHashMap
     */
    private static LruCache<String, Object> mLruCache = new LruCache<>(10 * 1024 * 1024);

    /************************** save data start ****************************/
    @Override
    public void save(String key, String value) {
        mLruCache.put(key, value);
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

    /************************** save data end ****************************/


    /************************** get data start ****************************/

    @Override
    public String getString(String key) {
        return (String) mLruCache.get(key);
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

    /************************** get data end ****************************/

}
