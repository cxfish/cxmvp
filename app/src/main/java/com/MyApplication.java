package com;


import android.app.Activity;

import com.hcx.framework.cxmvp.dagger.ActivityComponent;
import com.hcx.framework.cxmvp.dagger.ActivityModule;
import com.hcx.framework.cxmvp.dagger.AppModule;
import com.hcx.framework.cxmvp.dagger.ApplicationComponent;
import com.hcx.framework.cxmvp.dagger.DaggerApplicationComponent;
import com.hcx.framework.cxmvplibrary.CxApplication;

/**
 * Created by Chenxi Hu on 2018/7/17 0017.
 * vx : xidi9666
 */

public class MyApplication extends CxApplication {


    //Dagger2配置相关
    public static ApplicationComponent applicationComponent;
    public ActivityComponent activityComponent;
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        if (app == null) {
            app = this;
        }
        //初始化Dagger2
        applicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }

    public void createActivityComponent(Activity activity) {
        activityComponent = applicationComponent.bind(new ActivityModule(activity));
    }
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void releaseActivityComponent() {
        activityComponent = null;
    }
}
