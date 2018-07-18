package com.hcx.framework.cxmvp.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * App提供的全局变量
 * Created by ChenxiHu on 2016/12/15.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    //提供Application全局单例变量
    @Singleton
    @Provides
    public Application providesApplication() {
        return application;
    }

}
