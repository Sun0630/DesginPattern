package tech.sx.day10_factory;

import android.app.Application;

import tech.sx.day10_factory.sample2.PreferencesUtils;

/**
 * @Author Administrator
 * @Date 2018/3/29 0029 上午 9:50
 * @Description
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtils.getInstance().init(this);
    }
}
