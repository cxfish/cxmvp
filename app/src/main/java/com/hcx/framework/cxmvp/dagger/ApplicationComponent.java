package com.hcx.framework.cxmvp.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App提供的变量连接器
 * Created by ChenxiHu on 2016/12/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface ApplicationComponent {
    ActivityComponent bind(ActivityModule activityModule);
}
