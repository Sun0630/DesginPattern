package tech.sx.day10_factory.sample2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 9:46
 * @Description 构建一个单例的存储类
 */

public class PreferencesUtils {

    private static volatile PreferencesUtils mInstance;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private PreferencesUtils() {
    }

    public static PreferencesUtils getInstance() {
        if (mInstance == null) {
            synchronized (PreferencesUtils.class) {
                if (mInstance == null) {
                    mInstance = new PreferencesUtils();
                }
            }
        }
        return mInstance;
    }


    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mPreferences = context.getApplicationContext().getSharedPreferences("Cache", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }


    /**
     * 保存字符串
     *
     * @param key
     * @param value
     * @return
     */
    public PreferencesUtils putString(String key, String value) {
        mEditor.putString(key, value);
        return this;
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    /**
     * 提交
     */
    public void commit() {
        mEditor.apply();
    }


}
