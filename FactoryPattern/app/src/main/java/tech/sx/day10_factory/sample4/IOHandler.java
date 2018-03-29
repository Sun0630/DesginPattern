package tech.sx.day10_factory.sample4;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 10:04
 * @Description 定义数据存储的一些规范
 */

public interface IOHandler {

    /**
     * 保存String
     *
     * @param key
     * @param value
     */
    void save(String key, String value);

    /**
     * 保存int
     *
     * @param key
     * @param value
     */
    void save(String key, int value);

    /**
     * 保存long
     *
     * @param key
     * @param value
     */
    void save(String key, long value);

    /**
     * 保存String
     *
     * @param key
     * @param value
     */
    void save(String key, boolean value);

    /**
     * 保存double
     *
     * @param key
     * @param value
     */
    void save(String key, double value);

    /**
     * 保存Object
     *
     * @param key
     * @param value
     */
    void save(String key, Object value);


    /**
     * 获取
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 获取
     *
     * @param key
     * @param defalutValue
     * @return
     */
    int getInt(String key, int defalutValue);

    /**
     * 获取
     *
     * @param key
     * @param defalutValue
     * @return
     */
    long getLong(String key, long defalutValue);

    /**
     * 获取
     *
     * @param key
     * @param defalutValue
     * @return
     */
    double getDouble(String key, double defalutValue);

    /**
     * 获取
     *
     * @param key
     * @param defalutValue
     * @return
     */
    boolean getBoolean(String key, boolean defalutValue);

    /**
     * @param key
     * @return
     */
    Object getObject(String key);
}
